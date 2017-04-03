package za.co.wifi.info.client.service;

import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import za.co.wifi.info.client.web.model.Advert;
import za.co.wifi.info.client.web.model.BannerLink;
import za.co.wifi.info.client.web.model.Category;
import za.co.wifi.info.client.web.model.File;
import za.co.wifi.info.client.domain.DBOperationFailedException;
import za.co.wifi.info.client.domain.advert.AdvertEntity;
import za.co.wifi.info.client.domain.advert.AdvertRepository;
import za.co.wifi.info.client.domain.advert.AdvertType;
import za.co.wifi.info.client.domain.advert.DownloadPageEntity;
import za.co.wifi.info.client.domain.category.CategoryEntity;
import za.co.wifi.info.client.domain.category.CategoryRepository;
import za.co.wifi.info.client.domain.node.banner.NodeBannerEntity;
import za.co.wifi.info.client.domain.node.banner.NodeBannerRepository;

@Service
public class AdvertService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertService.class.getName());

    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NodeBannerRepository nodeBannerRepository;

    private DownloadPageEntity downloadPage;

    private List<Category> categoryAdverts;

    private List<BannerLink> bannerLinkAdverts;

    @PostConstruct
    private void init() {
        downloadPage = this.generateDownloadPage();
        bannerLinkAdverts = this.generateBannerLinkAdverts();
        categoryAdverts = this.generateCategoryAdverts();
    }

    public File getDownloadPage() {
        if (downloadPage != null) {
            File generatedDownloadPage = new File();

            generatedDownloadPage.setAdvertBinaryData(Base64.getEncoder().encodeToString(downloadPage.getDownloadPageData()));
            generatedDownloadPage.setFileSize(downloadPage.getDownloadPageData().length);
            generatedDownloadPage.setFileName("wifiinfo.pdf");
            generatedDownloadPage.setFileType("application/binary");

            return generatedDownloadPage;
        }

        return null;
    }

    public List<BannerLink> getBannerLinkAdverts() {
        return bannerLinkAdverts;
    }

    public void setBannerLinkAdverts(List<BannerLink> bannerLinkAdverts) {
        this.bannerLinkAdverts = bannerLinkAdverts;
    }

    public List<Category> getCategoryAdverts() {
        return categoryAdverts;
    }

    public void setCategoryAdverts(List<Category> categoryAdverts) {
        this.categoryAdverts = categoryAdverts;
    }

    public DownloadPageEntity generateDownloadPage() {
        DownloadPageEntity generatedDownloadPage = null;
        try {
            generatedDownloadPage = advertRepository.findDownloadPage();
        } catch (DBOperationFailedException exception) {
            LOGGER.error("Unable to generate Dowload Page");
        }
        return generatedDownloadPage;
    }

    public List<BannerLink> generateBannerLinkAdverts() {
        List<BannerLink> generatedBannerLinkAdverts = new LinkedList<>();

        NodeBannerEntity tmpNodeBanner = this.lookupNodeBanner();
        if (tmpNodeBanner != null) {
            try {
                BannerLink generatedNodeBanner = new BannerLink();
                generatedNodeBanner.setBannerLinkRefNo(tmpNodeBanner.getNodeRef());

                File bannerAdvert = new File();
                bannerAdvert.setAdvertBinaryData(Base64.getEncoder().encodeToString(tmpNodeBanner.getNodeData().getBinaryData()));
                bannerAdvert.setFileName(tmpNodeBanner.getNodeData().getFileName());
                bannerAdvert.setFileType(tmpNodeBanner.getNodeData().getFileType());
                generatedNodeBanner.setBannerAdvert(bannerAdvert);

                File bannerLinkData = new File();
                bannerLinkData.setAdvertBinaryData(Base64.getEncoder().encodeToString(tmpNodeBanner.getNodeLinkData().getBinaryData()));
                bannerLinkData.setFileName(tmpNodeBanner.getNodeLinkData().getFileName());
                bannerLinkData.setFileType(tmpNodeBanner.getNodeLinkData().getFileType());
                generatedNodeBanner.setBannerLink(bannerLinkData);

                generatedBannerLinkAdverts.add(generatedNodeBanner);
            } catch (Exception exception) {
                LOGGER.error("Unable to generate Node Banner : {0}", tmpNodeBanner.getNodeRef());
            }
        }

        List<AdvertEntity> tmpAdverts = this.lookupBannerLinkAdverts();

        for (AdvertEntity advert : tmpAdverts) {
            try {
                BannerLink generatedBannerLink = new BannerLink();
                generatedBannerLink.setBannerLinkRefNo(advert.getAdvertRefNo());

                File bannerAdvert = new File();
                bannerAdvert.setAdvertBinaryData(Base64.getEncoder().encodeToString(advert.getAdvertData().getBinaryData()));
                bannerAdvert.setFileName(advert.getAdvertData().getFileName());
                bannerAdvert.setFileType(advert.getAdvertData().getFileType());
                generatedBannerLink.setBannerAdvert(bannerAdvert);

                File bannerLinkData = new File();
                bannerLinkData.setAdvertBinaryData(Base64.getEncoder().encodeToString(advert.getAdvertLinkData().getBinaryData()));
                bannerLinkData.setFileName(advert.getAdvertLinkData().getFileName());
                bannerLinkData.setFileType(advert.getAdvertLinkData().getFileType());
                generatedBannerLink.setBannerLink(bannerLinkData);

                generatedBannerLinkAdverts.add(generatedBannerLink);
            } catch (Exception ex) {
                LOGGER.error("Unable to generate Banner Link : {0}", advert.getAdvertRefNo());
            }
        }

        return generatedBannerLinkAdverts;
    }

    public List<Category> generateCategoryAdverts() {
        List<Category> generatedAdverts = new LinkedList<>();

        List<CategoryEntity> tmpCategories = this.lookupCategories();

        for (CategoryEntity category : tmpCategories) {
            List<AdvertEntity> tmpCategoryAdverts = this.lookupAdverts(category);

            if (!tmpCategoryAdverts.isEmpty()) {
                try {
                    Category generatedCategory = new Category();
                    generatedCategory.setCategoryRef(category.getCategoryRef());
                    generatedCategory.setCategoryName(category.getCategoryName());

                    List<Advert> catgeoryAdverts = new LinkedList<>();
                    for (AdvertEntity categoryAdvert : tmpCategoryAdverts) {
                        try {
                            Advert generatedAdvert = new Advert();
                            generatedAdvert.setAdvertRef(categoryAdvert.getAdvertRef());
                            generatedAdvert.setAdvertBinaryData(Base64.getEncoder().encodeToString(categoryAdvert.getAdvertData().getBinaryData()));
                            generatedAdvert.setFileName(categoryAdvert.getAdvertData().getFileName());
                            generatedAdvert.setFileType(categoryAdvert.getAdvertData().getFileType());
                            catgeoryAdverts.add(generatedAdvert);
                        } catch (Exception e) {
                            LOGGER.error("Unable to generate Advert : {0}", categoryAdvert.getAdvertRef());
                        }
                    }
                    generatedCategory.setAdverts(catgeoryAdverts);
                    generatedAdverts.add(generatedCategory);
                } catch (Exception exception) {
                    LOGGER.error("Unable to generate Category : {0}", category.getCategoryRef());
                }
            }
        }
        return generatedAdverts;
    }

    private NodeBannerEntity lookupNodeBanner() {
        try {
            NodeBannerEntity banner = nodeBannerRepository.findActiveNodeBanner();
            return banner;
        } catch (DBOperationFailedException ex) {
            LOGGER.error("No Node Banner Found");
        }
        return null;
    }

    private List<AdvertEntity> lookupBannerLinkAdverts() {
        List<AdvertType> advertTypes = new LinkedList<>();
        advertTypes.add(AdvertType.LINK);

        try {
            List<AdvertEntity> adverts = advertRepository.findAll(advertTypes);
            return adverts;
        } catch (DBOperationFailedException ex) {
            LOGGER.error("No Banner Links Found");
        }
        return new LinkedList<>();
    }

    private List<CategoryEntity> lookupCategories() {
        try {
            List<CategoryEntity> categories = categoryRepository.findAll(new CategoryEntity());
            return categories;
        } catch (DBOperationFailedException ex) {
            LOGGER.error("No Categories Found");
        }
        return new LinkedList<>();
    }

    private List<AdvertEntity> lookupAdverts(CategoryEntity category) {
        List<AdvertType> advertTypes = new LinkedList<>();
        advertTypes.add(AdvertType.STANDARD);
        advertTypes.add(AdvertType.HALF_PAGE);
        advertTypes.add(AdvertType.FULL_PAGE);

        List<AdvertEntity> adverts;
        try {
            adverts = advertRepository.findAll(category, advertTypes);
        } catch (DBOperationFailedException ex) {
            adverts = new LinkedList<>();
        }

        return adverts;
    }
}
