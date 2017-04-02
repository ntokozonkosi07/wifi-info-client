package za.co.wifi.info.client.events;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import za.co.wifi.info.client.service.AdvertService;
import za.co.wifi.info.client.web.model.BannerLink;
import za.co.wifi.info.client.web.model.Category;

@Component
public class AdvertSyncEvent extends AbstractAdvertEvent {

    private final AdvertService advertService;

    @Autowired
    public AdvertSyncEvent(AdvertService advertService) {
        this.advertService = advertService;
    }

    @Scheduled(fixedRate = 5000)
    public void syncCategoryAdverts() {
        LOGGER.info("Syncing category adverts");

        try {
            List<Category> catgeoryAdverts = advertService.generateCategoryAdverts();
            advertService.setCategoryAdverts(catgeoryAdverts);
        } catch (Exception ex) {
            LOGGER.error("Error syncing category adverts");
        }
    }

    @Scheduled(fixedRate = 5000)
    public void syncDeviceBannerLinks() {
        LOGGER.info("Syncing banner links");

        try {
            List<BannerLink> bannerLinkAdverts = advertService.generateBannerLinkAdverts();
            advertService.setBannerLinkAdverts(bannerLinkAdverts);
        } catch (Exception ex) {
            LOGGER.error("Error syncing banner links");
        }
    }
}
