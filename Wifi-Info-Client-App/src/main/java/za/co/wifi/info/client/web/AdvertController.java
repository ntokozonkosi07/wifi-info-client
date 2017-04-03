package za.co.wifi.info.client.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final AdvertService advertService;

    @Autowired
    public AdvertController(AdvertService advertService) {
        this.advertService = advertService;
    }

    @ResponseBody
    @RequestMapping(path = "/page_download", method = RequestMethod.GET)
    public ResponseEntity generateDownloadPage() {
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
    @RequestMapping(path = "/generate/banners", method = RequestMethod.GET)
    public ResponseEntity generateBannerLinkAdverts() {
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
    @RequestMapping(path = "/generate/adverts", method = RequestMethod.GET)
    public ResponseEntity generateCategoryAdverts() {
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
