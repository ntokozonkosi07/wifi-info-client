package za.co.wifi.info.client.events;

import java.text.MessageFormat;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import za.co.wifi.info.client.domain.advert.AdvertEntity;
import za.co.wifi.info.client.web.model.Advert;
import za.co.wifi.info.client.web.model.Category;

public abstract class AbstractAdvertEvent {

    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public static List<Category> mergeAdverts(List<Category> existingCategoryAdverts, List<Category> newCategoryAdverts) {
        Set<Category> existingAdverts = new LinkedHashSet<>(existingCategoryAdverts);
        existingAdverts.addAll(new LinkedHashSet<>(newCategoryAdverts));

        return new LinkedList<>(existingAdverts);
    }

    protected void shuffleCategoryAdverts(List<Category> categoryAdverts) {
        LOGGER.debug(new MessageFormat("Shuffle category adverts for {0} categories")
                .format(new Object[]{categoryAdverts.size()}));

        if (CollectionUtils.isEmpty(categoryAdverts)) {
            LOGGER.debug("No category adverts");
            return;
        }

        Category tmpCategoryAdvert;
        for (int i = 0; i < categoryAdverts.size() - 1; i++) {
            tmpCategoryAdvert = categoryAdverts.get(i);
            categoryAdverts.set(i, categoryAdverts.get(i + 1));
            categoryAdverts.set(i + 1, tmpCategoryAdvert);
        }

        categoryAdverts.forEach((Category categoryAdvert) -> {
            shuffleAdverts(categoryAdvert.getCategoryName(), new LinkedList<>(categoryAdvert.getAdverts()));
        });

        LOGGER.debug(new MessageFormat("Completed category adverts shuffle for {0} categories")
                .format(new Object[]{categoryAdverts.size()}));
    }

    protected void shuffleAdverts(String categoryName, List<Advert> adverts) {
        LOGGER.debug(new MessageFormat("Shuffle {0} adverts for {1} category")
                .format(new Object[]{adverts, categoryName}));

        if (CollectionUtils.isEmpty(adverts)) {
            LOGGER.debug(new MessageFormat("No adverts for {0} category")
                    .format(new Object[]{categoryName}));
            return;
        }

        Advert tmpAdvert;
        for (int i = 0; i < adverts.size() - 1; i++) {
            tmpAdvert = adverts.get(i);
            adverts.set(i, adverts.get(i + 1));
            adverts.set(i + 1, tmpAdvert);
        }
    }

    protected AdvertEntity findAdvertEntity(List<AdvertEntity> adverts, String advertRefNo) {
        for (AdvertEntity advert : adverts) {
            if (advert.getAdvertRefNo().equals(advertRefNo)) {
                return advert;
            }
        }
        return null;
    }
}
