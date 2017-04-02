package za.co.wifi.info.remote.client;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import za.co.wifi.info.remote.client.error.OperationFailedException;
import za.co.wifi.info.remote.client.model.AdvertDTO;
import za.co.wifi.info.remote.client.model.CategoryDTO;
import za.co.wifi.info.remote.client.model.HOInfoDTO;
import za.co.wifi.info.remote.client.model.NodeDTO;

public class RemoteAdvertClient {

    private final String serviceEndpoint;
    private final String systemHealthEndpoint;
    private final String verifyDeviceEndpoint;
    private final String lookupCategoriesEndpoint;
    private final String lookupBannerEndpoint;
    private final String lookupAdvertsEndpoint;
    private final String lookupAdvertEndpoint;

    private final RestTemplate restTemplate;

    @Autowired
    public RemoteAdvertClient(@Value("${remote.client.url}") String serviceEndpoint,
            @Value("${remote.client.health.url}") String systemHealthEndpoint,
            @Value("${remote.client.verify.device.url}") String verifyDeviceEndpoint,
            @Value("${remote.client.lookup.category.url}") String lookupCategoriesEndpoint,
            @Value("${remote.client.lookup.banner.url}") String lookupBannerEndpoint,
            @Value("${remote.client.lookup.adverts.url}") String lookupAdvertsEndpoint,
            @Value("${remote.client.lookup.advert.url}") String lookupAdvertEndpoint,
            RestTemplate restTemplate) {
        this.serviceEndpoint = serviceEndpoint;
        this.systemHealthEndpoint = systemHealthEndpoint;
        this.verifyDeviceEndpoint = verifyDeviceEndpoint;
        this.lookupCategoriesEndpoint = lookupCategoriesEndpoint;
        this.lookupBannerEndpoint = lookupBannerEndpoint;
        this.lookupAdvertsEndpoint = lookupAdvertsEndpoint;
        this.lookupAdvertEndpoint = lookupAdvertEndpoint;
        this.restTemplate = restTemplate;
    }

    public Date verifyTime(String deviceRef) throws OperationFailedException {
        try {
            MessageFormat serviceUrlFormater = new MessageFormat(serviceEndpoint + systemHealthEndpoint);
            HOInfoDTO hOInfoDTO = restTemplate.getForObject(serviceUrlFormater.format(new Object[]{deviceRef}), HOInfoDTO.class);

            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(hOInfoDTO.getTimestamp());
        } catch (ParseException | RestClientException ex) {
            throw new OperationFailedException("An error occurred while synching system date", ex);
        }
    }

    public NodeDTO verifyDevice(String deviceRef) throws OperationFailedException {
        try {
            MessageFormat serviceUrlFormater = new MessageFormat(serviceEndpoint + verifyDeviceEndpoint);
            NodeDTO nodeDTO = restTemplate.getForObject(serviceUrlFormater.format(new Object[]{deviceRef}), NodeDTO.class);

            return nodeDTO;
        } catch (RestClientException ex) {
            throw new OperationFailedException("An error occurred while synching node info", ex);
        }
    }

    public List<CategoryDTO> lookupCategories(String deviceRef) throws OperationFailedException {
        try {
            MessageFormat serviceUrlFormater = new MessageFormat(serviceEndpoint + lookupCategoriesEndpoint);
            CategoryDTO[] categories = restTemplate.getForObject(serviceUrlFormater.format(new Object[]{deviceRef}), CategoryDTO[].class);

            return Arrays.asList(categories);
        } catch (RestClientException ex) {
            throw new OperationFailedException("An error occurred while synching categories", ex);
        }
    }

    public AdvertDTO lookupDeviceBanner(String deviceRef) throws OperationFailedException {
        try {
            MessageFormat serviceUrlFormater = new MessageFormat(serviceEndpoint + lookupBannerEndpoint);
            AdvertDTO deviceBanner = restTemplate.getForObject(serviceUrlFormater.format(new Object[]{deviceRef}), AdvertDTO.class);

            return deviceBanner;
        } catch (RestClientException ex) {
            throw new OperationFailedException("An error occurred while synching device banner", ex);
        }
    }

    public List<String> lookupAdverts(String deviceRef) throws OperationFailedException {
        try {
            MessageFormat serviceUrlFormater = new MessageFormat(serviceEndpoint + lookupAdvertsEndpoint);
            String[] adverts = restTemplate.getForObject(serviceUrlFormater.format(new Object[]{deviceRef}), String[].class);

            return Arrays.asList(adverts);
        } catch (RestClientException ex) {
            throw new OperationFailedException("An error occurred while synching device adverts", ex);
        }
    }

    public AdvertDTO lookupAdvert(String advertRefNo) throws OperationFailedException {
        try {
            MessageFormat serviceUrlFormater = new MessageFormat(serviceEndpoint + lookupAdvertEndpoint);
            AdvertDTO deviceBanner = restTemplate.getForObject(serviceUrlFormater.format(new Object[]{advertRefNo}), AdvertDTO.class);

            return deviceBanner;
        } catch (RestClientException ex) {
            throw new OperationFailedException("An error occurred while synching device advert", ex);
        }
    }
}
