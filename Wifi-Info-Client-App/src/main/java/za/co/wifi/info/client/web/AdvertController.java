package za.co.wifi.info.client.web;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import za.co.wifi.info.client.service.AdvertService;
import za.co.wifi.info.client.web.model.BannerLink;
import za.co.wifi.info.client.web.model.Category;
import za.co.wifi.info.client.web.model.File;

@Controller
@RequestMapping("/wifi-info-rs/restapi/advert")
public class AdvertController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertController.class.getName());
    
    private final AdvertService advertService;

    @Autowired
    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @ResponseBody
    @Cacheable(value = "adverts",key = "page_download")
    @RequestMapping(path = "/page_download", method = RequestMethod.GET)
    public ResponseEntity generateDownloadPage() {
        LOGGER.info("Calling service - page_download");
        
        try {
            File downloadPage = advertService.getDownloadPage();
            if (downloadPage == null) {
                return ResponseEntity.noContent().build();
            }

            return getCreatedResponse(downloadPage);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseBody
    @Cacheable(value = "adverts",key = "banners")
    @RequestMapping(path = "/generate/banners", method = RequestMethod.GET)
    public ResponseEntity generateBannerLinkAdverts() {
        LOGGER.info("Calling service - /generate/banners");
        
        try {
            List<BannerLink> bannerLinkAdverts = advertService.getBannerLinkAdverts();
            if (CollectionUtils.isEmpty(bannerLinkAdverts)) {
                return ResponseEntity.noContent().build();
            }

            return getCreatedResponse(bannerLinkAdverts);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseBody
    @Cacheable(value = "adverts",key = "adverts")
    @RequestMapping(path = "/generate/adverts", method = RequestMethod.GET)
    public ResponseEntity generateCategoryAdverts() {
        LOGGER.info("Calling service - /generate/adverts");
        
        try {
            List<Category> categoryAdverts = advertService.getCategoryAdverts();
            if (CollectionUtils.isEmpty(categoryAdverts)) {
                return ResponseEntity.noContent().build();
            }

            return getCreatedResponse(categoryAdverts);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private static ResponseEntity getCreatedResponse(final Object entity) {
        return new ResponseEntity(entity, HttpStatus.CREATED);
    }
}
