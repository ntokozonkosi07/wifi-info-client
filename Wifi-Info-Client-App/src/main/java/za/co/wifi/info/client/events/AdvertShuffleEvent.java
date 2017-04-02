package za.co.wifi.info.client.events;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import za.co.wifi.info.client.service.AdvertService;
import za.co.wifi.info.client.web.model.Category;

@Component
public class AdvertShuffleEvent extends AbstractAdvertEvent {

    private final AdvertService advertService;

    @Autowired
    public AdvertShuffleEvent(AdvertService advertService) {
        this.advertService = advertService;
    }

    @Scheduled(fixedRate = 5000)
    public void shuffleCategoryAdverts() {
        LOGGER.info("Shuffling category adverts");
        
        try {
            List<Category> categoryAdverts = advertService.getCategoryAdverts();
            if (CollectionUtils.isEmpty(categoryAdverts)) {
                categoryAdverts = advertService.generateCategoryAdverts();
            } else {
                shuffleCategoryAdverts(categoryAdverts);
            }

            advertService.setCategoryAdverts(categoryAdverts);
        } catch (Exception ex) {
            LOGGER.error("Error shuffling Adverts");
        }
    }
}
