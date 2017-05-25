package za.co.wifi.info.client.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import za.co.rynmag.wifiinfo.pdf.generator.ReportGenerator;
import za.co.rynmag.wifiinfo.pdf.generator.ReportGeneratorException;
import za.co.wifi.info.client.domain.DBOperationFailedException;
import za.co.wifi.info.client.domain.advert.AdvertCategoryEntity;
import za.co.wifi.info.client.domain.advert.AdvertDataEntity;
import za.co.wifi.info.client.domain.advert.AdvertEntity;
import za.co.wifi.info.client.domain.advert.AdvertRepository;
import za.co.wifi.info.client.domain.advert.AdvertType;
import za.co.wifi.info.client.domain.advert.DownloadPageEntity;
import za.co.wifi.info.client.domain.category.CategoryEntity;
import za.co.wifi.info.client.domain.category.CategoryRepository;
import za.co.wifi.info.client.domain.node.NodeDataEntity;
import za.co.wifi.info.client.domain.node.NodeEntity;
import za.co.wifi.info.client.domain.node.NodeRepository;
import za.co.wifi.info.client.domain.node.banner.NodeBannerEntity;
import za.co.wifi.info.client.domain.node.banner.NodeBannerRepository;
import za.co.wifi.info.client.gsm.GSMConnectionUtil;
import za.co.wifi.info.remote.client.RemoteAdvertClient;
import za.co.wifi.info.remote.client.error.OperationFailedException;
import za.co.wifi.info.remote.client.model.AdvertDTO;
import za.co.wifi.info.remote.client.model.CategoryDTO;
import za.co.wifi.info.remote.client.model.NodeDTO;

@Service
@Scope("singleton")
public class AdvertSyncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertSyncService.class.getName());

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    private final NodeRepository nodeRepository;
    private final NodeBannerRepository nodeBannerRepository;
    private final AdvertRepository advertRepository;
    private final CategoryRepository categoryRepository;
    private final RemoteAdvertClient remoteAdvertClient;
    private final ReportGenerator generator;
    private final String defaultDeviceRef;
    private final String webRootPath;
    GSMConnectionUtil connectionUtil;

    @Autowired
    public AdvertSyncService(@Value("${app.config.device.ref}") String defaultDeviceRef,
                             @Value("${app.config.device.web.root.path}") String webRootPath,
                             GSMConnectionUtil connectionUtil, NodeRepository nodeRepository,
                             NodeBannerRepository nodeBannerRepository, AdvertRepository advertRepository,
                             CategoryRepository categoryRepository, RemoteAdvertClient remoteAdvertClient) {
        this.defaultDeviceRef = defaultDeviceRef;
        this.webRootPath = webRootPath;
        this.connectionUtil = connectionUtil;
        this.nodeRepository = nodeRepository;
        this.nodeBannerRepository = nodeBannerRepository;
        this.advertRepository = advertRepository;
        this.categoryRepository = categoryRepository;
        this.remoteAdvertClient = remoteAdvertClient;
        this.generator = new ReportGenerator();
    }

    public void syncDevice() {
        LOGGER.info("Syncing adverts");

        try {
            int connnectionRetries = 0;
            while (true) {
                LOGGER.info("Attempting to connect.....");
//                connectionUtil.connect();

                if (connectionUtil.getStatus("http://jbosswildfly-wifiinfo.rhcloud.com")) {
                    break;
                }

                Thread.sleep(30000);

                if (connnectionRetries >= 10) {
                    LOGGER.error("Failed to connect.....");
                    return;
                }

                connnectionRetries++;

                LOGGER.info("Retrying.....");
            }

            LOGGER.info("Connected.....");

            NodeEntity device = this.lookupDeviceRef();

            boolean successfullyUpdatedConfig = device == null ? syncDeviceConfig(defaultDeviceRef, device) : syncDeviceConfig(device);
            if (!successfullyUpdatedConfig) {
                LOGGER.error("Failed to initialize device.....");
                return;
            }

            Date currentTime = remoteAdvertClient.verifyTime(device.getDeviceRef());
            if (currentTime == null) {
                return;
            }

            this.syncTime(currentTime);
            this.syncDeviceBanner(device.getDeviceRef());
            this.syncAdverts(device.getDeviceRef());

        } catch (IOException | InterruptedException | OperationFailedException ex) {
            LOGGER.info("An unexpected error occurred", ex);
        } finally {
//            connectionUtil.disconnect();
            generateDownloadPage();
        }
    }

    private void syncTime(Date currentTime) throws IOException {
        LOGGER.info(new MessageFormat("Syncing system time : {0}")
                .format(new Object[]{currentTime.toString()}));

        Runtime.getRuntime().exec("sudo date -s " + currentTime.toString());
    }

    private void syncDeviceBanner(String deviceRef) {
        try {
            LOGGER.info("Downloading device banner");

            AdvertDTO downloadeNodeBanner = remoteAdvertClient.lookupDeviceBanner(deviceRef);

            NodeBannerEntity newNodeBanner = new NodeBannerEntity();
            newNodeBanner.setNodeRef(downloadeNodeBanner.getRefNo());
            newNodeBanner.setStartDate(simpleDateFormat.parse(downloadeNodeBanner.getStartDate()));
            newNodeBanner.setEndDate(simpleDateFormat.parse(downloadeNodeBanner.getEndDate()));

            if (downloadeNodeBanner.getAdvertData() != null) {
                NodeDataEntity nodeData = new NodeDataEntity();
                nodeData.setBinaryData(downloadeNodeBanner.getAdvertData().getBinaryData());
                nodeData.setFileType(downloadeNodeBanner.getAdvertData().getFileType());
                nodeData.setFileName(downloadeNodeBanner.getAdvertData().getFileName());
                newNodeBanner.setNodeData(nodeData);
            }

            if (downloadeNodeBanner.getAdvertLinkData() != null) {
                NodeDataEntity nodeLinkData = new NodeDataEntity();
                nodeLinkData.setBinaryData(downloadeNodeBanner.getAdvertLinkData().getBinaryData());
                nodeLinkData.setFileType(downloadeNodeBanner.getAdvertLinkData().getFileType());
                nodeLinkData.setFileName(downloadeNodeBanner.getAdvertLinkData().getFileName());
                newNodeBanner.setNodeLinkData(nodeLinkData);
            }

            nodeBannerRepository.saveNodeBanner(newNodeBanner);

            LOGGER.info("Completed downlaoding device banner");

        } catch (DBOperationFailedException | ParseException | OperationFailedException ex) {
            LOGGER.error("An unexpected error occured downloading device banner");
        }
    }

    private void syncAdverts(String deviceRef) {
        try {
            LOGGER.info("Downloading device adverts");

            List<String> deviceAdverts = remoteAdvertClient.lookupAdverts(deviceRef);

            if (CollectionUtils.isEmpty(deviceAdverts)) {
                LOGGER.info("No new adverts to download");
                return;
            }

            for (String advertRefNo : deviceAdverts) {
                AdvertEntity lookupAdvert = new AdvertEntity();
                lookupAdvert.setAdvertRefNo(advertRefNo);

                AdvertEntity existingAdvert = null;
                existingAdvert = advertRepository.find(lookupAdvert);

                if (existingAdvert == null) {
                    LOGGER.info(new MessageFormat("Downlaoding advert : {0}")
                            .format(new Object[]{advertRefNo}));

                    AdvertDTO downloadedAdvert = remoteAdvertClient.lookupAdvert(advertRefNo);
                    try {
                        AdvertEntity newAdvert = new AdvertEntity();
                        newAdvert.setAdvertRefNo(downloadedAdvert.getRefNo());
                        newAdvert.setStartDate(simpleDateFormat.parse(downloadedAdvert.getStartDate()));
                        newAdvert.setEndDate(simpleDateFormat.parse(downloadedAdvert.getEndDate()));
                        newAdvert.setAdType(AdvertType.getAdvertType(downloadedAdvert.getAdType()));
                        newAdvert.setCreatedDate(new Date());

                        if (downloadedAdvert.getAdvertData() != null) {
                            AdvertDataEntity advertData = new AdvertDataEntity();
                            advertData.setBinaryData(downloadedAdvert.getAdvertData().getBinaryData());
                            advertData.setFileType(downloadedAdvert.getAdvertData().getFileType());
                            advertData.setFileName(downloadedAdvert.getAdvertData().getFileName());
                            newAdvert.setAdvertData(advertData);
                        }

                        if (downloadedAdvert.getAdvertLinkData() != null) {
                            AdvertDataEntity advertLinkData = new AdvertDataEntity();
                            advertLinkData.setBinaryData(downloadedAdvert.getAdvertLinkData().getBinaryData());
                            advertLinkData.setFileType(downloadedAdvert.getAdvertLinkData().getFileType());
                            advertLinkData.setFileName(downloadedAdvert.getAdvertLinkData().getFileName());
                            newAdvert.setAdvertLinkData(advertLinkData);
                        }

                        if (!CollectionUtils.isEmpty(downloadedAdvert.getAdvertCategories())) {
                            List<AdvertCategoryEntity> advertCategories = new LinkedList();
                            for (CategoryDTO selectedCategory : downloadedAdvert.getAdvertCategories()) {
                                CategoryEntity category = new CategoryEntity();
                                category.setCategoryRef(selectedCategory.getCategoryRef());
                                category.setCategoryName(selectedCategory.getCategoryName());

                                AdvertCategoryEntity advertCategory = new AdvertCategoryEntity();
                                advertCategory.setCategoryRef(category);
                                advertCategory.setAdvertRef(newAdvert);

                                advertCategories.add(advertCategory);
                            }
                            newAdvert.setAdvertCategories(advertCategories);
                        }

                        advertRepository.update(newAdvert);

                        LOGGER.info(new MessageFormat("Completed downlaoding advert : {0}")
                                .format(new Object[]{advertRefNo}));

                    } catch (DBOperationFailedException | ParseException ex) {
                        LOGGER.error(new MessageFormat("Error downlaoding advert : {0}")
                                .format(new Object[]{advertRefNo}), ex);
                    }
                }

                LOGGER.info("Completed downloading adverts");

            }
        } catch (OperationFailedException ex) {
            LOGGER.error("An unexpected error occured downloading device adverts");
        }
    }

    private NodeEntity lookupDeviceRef() {
        try {
            //Need to expand this a bit more
            return nodeRepository.find(new NodeEntity());
        } catch (DBOperationFailedException ex) {
            LOGGER.error("Device not configured");
            return null;
        }
    }

    private boolean syncDeviceConfig(String deviceRef, NodeEntity device) {
        LOGGER.info(new MessageFormat("Creating device configuration for  : {0}")
                .format(new Object[]{deviceRef}));

        NodeDTO nodeDTO = verifyDevice(deviceRef);

        if (nodeDTO == null) {
            return false;
        }

        try {
            device = new NodeEntity();
            device.setMacAddress(nodeDTO.getMacAddress());
            device.setDeviceRef(nodeDTO.getDeviceRef());
            device.setNodeAddress(nodeDTO.getAddress());
            device.setNodeName(nodeDTO.getName());
            device.setCategoryRef(new Long(nodeDTO.getNodeCategory()));
            device.setCategoryName(nodeDTO.getNodeCategoryName());
            device.setLastModifiedBy(1L);
            device.setLastModifiedDate(new Date());

            nodeRepository.save(device);

            LOGGER.info("Succesfully created device configuration");

            return true;
        } catch (DBOperationFailedException ex) {
            LOGGER.error("Failed to create device configuration", ex);
        }

        return false;
    }

    private boolean syncDeviceConfig(NodeEntity device) {
        LOGGER.info(new MessageFormat("Updating device configuration for  : {0}")
                .format(new Object[]{device.getDeviceRef()}));

        NodeDTO nodeDTO = verifyDevice(device.getDeviceRef());

        if (nodeDTO == null) {
            return false;
        }

        try {
            device.setLastModifiedBy(1L);
            device.setLastModifiedDate(new Date());
            device.setMacAddress(nodeDTO.getMacAddress());
            device.setDeviceRef(nodeDTO.getDeviceRef());
            device.setNodeAddress(nodeDTO.getAddress());
            device.setNodeName(nodeDTO.getName());
            device.setCategoryRef(new Long(nodeDTO.getNodeCategory()));
            device.setCategoryName(nodeDTO.getNodeCategoryName());

            nodeRepository.update(device);

            LOGGER.info("Succesfully updated device configuration");

            return true;
        } catch (DBOperationFailedException ex) {
            LOGGER.error("Failed to update device configuration", ex);
        }

        return false;
    }

    private NodeDTO verifyDevice(String deviceRef) {
        try {
            LOGGER.info(new MessageFormat("Verifying device : {0}")
                    .format(new Object[]{deviceRef}));

            return remoteAdvertClient.verifyDevice(deviceRef);
        } catch (OperationFailedException ex) {
            LOGGER.error(new MessageFormat("Unable to verify device : {0}")
                    .format(new Object[]{deviceRef}));

            return null;
        }
    }

    private void generateDownloadPage() {
        try {
            LOGGER.info("Creating Download Page");

            List<InputStream> categoryAdvertsPdfFile = new LinkedList<>();
            for (CategoryEntity categoryAdvert : categoryRepository.findAllAdvertCategories()) {
                LOGGER.info(new MessageFormat("Generating pdf for Category : {0}")
                        .format(new Object[]{categoryAdvert.getCategoryName()}));

                generatePageByCategory(categoryAdvert, categoryAdvertsPdfFile);
            }

            if (CollectionUtils.isEmpty(categoryAdvertsPdfFile)) {
                LOGGER.error("Failed to generate pdf page download");
                return;
            }

            ByteArrayOutputStream downloadPageOutStream = generator.mergePdfFiles(categoryAdvertsPdfFile);

            DownloadPageEntity downloadPage = new DownloadPageEntity();
            downloadPage.setDownloadPageRef(1L);
            downloadPage.setDownloadPageData(downloadPageOutStream.toByteArray());

            advertRepository.save(downloadPage);

            LOGGER.info("Completed creating download page");
        } catch (DBOperationFailedException | ReportGeneratorException ex) {
            LOGGER.error("Unexpected error occured creating download page", ex);
        }
    }

    private List<InputStream> generatePageByCategory(CategoryEntity categoryAdvert, List<InputStream> categoryAdvertsPdfFiles) {

        List<AdvertEntity> categoryAdverts = new LinkedList<>();
        try {
            categoryAdverts = advertRepository.findAll(categoryAdvert, AdvertType.STANDARD);
        } catch (DBOperationFailedException ex) {
            LOGGER.error("Unable to retrieve standard category adverts", ex);
        }

        for (int i = 0; i < categoryAdverts.size(); i = i + 3) {
            ByteArrayInputStream image1 = null;
            ByteArrayInputStream image2 = null;
            ByteArrayInputStream image3 = null;

            try {
                image1 = new ByteArrayInputStream(categoryAdverts.get(i).getAdvertData().getBinaryData());
            } catch (Exception ex) {
            }

            try {
                image2 = new ByteArrayInputStream(categoryAdverts.get(i + 1).getAdvertData().getBinaryData());
            } catch (Exception ex) {
            }

            try {
                image3 = new ByteArrayInputStream(categoryAdverts.get(i + 2).getAdvertData().getBinaryData());
            } catch (Exception ex) {
            }

            try {
                ByteArrayOutputStream pdfPage = generator.createStandardPage(categoryAdvert.getCategoryName(), image1, image2, image3);
                categoryAdvertsPdfFiles.add(new ByteArrayInputStream(pdfPage.toByteArray()));
            } catch (ReportGeneratorException ex) {
                LOGGER.error("Unexpected error occured creating standard download page", ex);
            }
        }

        try {
            categoryAdverts = advertRepository.findAll(categoryAdvert, AdvertType.HALF_PAGE);
        } catch (DBOperationFailedException ex) {
            LOGGER.error("Unable to retrieve half page category adverts", ex);
        }

        for (int i = 0; i < categoryAdverts.size(); i = i + 2) {
            ByteArrayInputStream image1 = null;
            ByteArrayInputStream image2 = null;
            ByteArrayInputStream image3 = null;

            try {
                image1 = new ByteArrayInputStream(categoryAdverts.get(i).getAdvertData().getBinaryData());
            } catch (Exception ex) {
            }

            try {
                image2 = new ByteArrayInputStream(categoryAdverts.get(i + 1).getAdvertData().getBinaryData());
            } catch (Exception ex) {
            }

            try {
                ByteArrayOutputStream pdfPage = generator.createHalfPage(categoryAdvert.getCategoryName(), image1, image2);
                categoryAdvertsPdfFiles.add(new ByteArrayInputStream(pdfPage.toByteArray()));
            } catch (ReportGeneratorException ex) {
                LOGGER.error("Unexpected error occured creating half page download page", ex);
            }
        }

        try {
            categoryAdverts = advertRepository.findAll(categoryAdvert, AdvertType.FULL_PAGE);
        } catch (DBOperationFailedException ex) {
            LOGGER.error("Unable to retrieve full page category adverts", ex);
        }

        for (int i = 0; i < categoryAdverts.size(); i++) {
            ByteArrayInputStream image1 = null;

            try {
                image1 = new ByteArrayInputStream(categoryAdverts.get(i).getAdvertData().getBinaryData());
            } catch (Exception ex) {
            }

            try {
                ByteArrayOutputStream pdfPage = generator.createFullPage(categoryAdvert.getCategoryName(), image1);
                categoryAdvertsPdfFiles.add(new ByteArrayInputStream(pdfPage.toByteArray()));
            } catch (ReportGeneratorException ex) {
                LOGGER.error("Unexpected error occured creating full page download page", ex);
            }
        }

        try {
            categoryAdverts = advertRepository.findAll(AdvertType.LINK);
        } catch (DBOperationFailedException ex) {
            LOGGER.error("Unable to retrieve link category adverts", ex);
        }

        for (AdvertEntity bannerLinkAdvert : categoryAdverts) {
            try {
                ByteArrayInputStream pdfPage = new ByteArrayInputStream(bannerLinkAdvert.getAdvertLinkData().getBinaryData());
                categoryAdvertsPdfFiles.add(pdfPage);
            } catch (Exception ex) {
                LOGGER.error("Unexpected error occured processing link advert", ex);
            }
        }

        return categoryAdvertsPdfFiles;
    }
}
