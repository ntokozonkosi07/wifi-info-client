
package za.co.wifi.info.client.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import za.co.wifi.info.client.web.model.BannerLink;
import za.co.wifi.info.client.web.model.Category;

@Service
@Scope("singleton")
public class PageGeneratorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageGeneratorService.class.getName());

    public void generatePage(List<BannerLink> bannerLinkAdverts, List<Category> categoryAdverts) {
    }
}
