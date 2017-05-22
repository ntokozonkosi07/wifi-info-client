package za.co.wifi.info.client.events;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import za.co.wifi.info.client.service.AdvertService;
import za.co.wifi.info.client.service.AdvertSyncService;
import za.co.wifi.info.client.service.PageGeneratorService;
import za.co.wifi.info.client.web.model.BannerLink;
import za.co.wifi.info.client.web.model.Category;

@Component
public class AdvertSyncEvent extends AbstractAdvertEvent {

    private static final long SYNC_INTERVAL = 3600000;

    private final AdvertService advertService;
    private final AdvertSyncService advertSyncService;
    private final PageGeneratorService pageGeneratorService;

    @Autowired
    public AdvertSyncEvent(AdvertService advertService,
                           AdvertSyncService advertSyncService,
                           PageGeneratorService pageGeneratorService) {
        this.advertService = advertService;
        this.advertSyncService = advertSyncService;
        this.pageGeneratorService = pageGeneratorService;
    }

    @Scheduled(fixedRate = SYNC_INTERVAL)
    public void syncAdverts() {

        advertSyncService.syncDevice();

        try {
            //Pause for 30 seconds
            Thread.sleep(30000L);
        } catch (InterruptedException ex) {
            LOGGER.error(ex.getMessage());
        }

        List<BannerLink> bannerLinkAdverts = syncDeviceBannerLinks();

        LOGGER.info("Shuffling category adverts");

        try {
            List<Category> categoryAdverts = advertService.getCategoryAdverts();
            if (CollectionUtils.isEmpty(categoryAdverts)) {
                categoryAdverts = advertService.generateCategoryAdverts();
            } else {
                categoryAdverts = mergeAdverts(categoryAdverts, advertService.generateCategoryAdverts());
                shuffleCategoryAdverts(categoryAdverts);
            }

            advertService.setCategoryAdverts(categoryAdverts);

            pageGeneratorService.generatePage(bannerLinkAdverts,categoryAdverts);
        } catch (Exception ex) {
            LOGGER.error("Error shuffling Adverts");
        }
    }

    public List<BannerLink> syncDeviceBannerLinks() {
        LOGGER.info("Syncing banner links");

        try {
            List<BannerLink> bannerLinkAdverts = advertService.generateBannerLinkAdverts();
            advertService.setBannerLinkAdverts(bannerLinkAdverts);
            
            return bannerLinkAdverts;
        } catch (Exception ex) {
            LOGGER.error("Error syncing banner links");
        }

        return new LinkedList<BannerLink>();
    }
}
