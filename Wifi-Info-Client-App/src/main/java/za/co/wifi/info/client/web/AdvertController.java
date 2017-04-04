package za.co.wifi.info.client.web;

import java.util.Base64;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
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
    @RequestMapping(path = "/page/download", method = RequestMethod.GET)
    public ResponseEntity generateDownloadPage() {
        LOGGER.info("Calling service - page download");

        try {
            File downloadPage = advertService.getDownloadPage();
            if (downloadPage == null) {
                return ResponseEntity.noContent().build();
            }

            return getCreatedResponse(downloadPage);
        } catch (Exception ex) {
            LOGGER.error("Unexpected error occurred calling service - page_download", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseBody
    @RequestMapping(path = "/page/download/file", method = RequestMethod.GET)
    public ResponseEntity<byte[]> generateDownloadPageFile() {
        LOGGER.info("Calling service - page download file");

        try {
            File downloadPage = advertService.getDownloadPage();
            if (downloadPage == null) {
                return ResponseEntity.noContent().build();
            } else {
                HttpHeaders header = new HttpHeaders();
                header.setContentType(new MediaType("application", "pdf"));
                header.set("Content-Disposition", "inline; filename=" + downloadPage.getFileName());
                header.setContentLength(downloadPage.getFileSize());

                return new ResponseEntity<>(Base64.getDecoder().decode(downloadPage.getAdvertBinaryData()), header, HttpStatus.OK);
            }
        } catch (Exception ex) {
            LOGGER.error("Unexpected error occurred calling service - page_download", ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ResponseBody
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
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Headers", "Origin, Accept, x-auth-token, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers");
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        headers.add("Access-Control-Max-Age", "1209600");

        return new ResponseEntity(entity, headers, HttpStatus.CREATED);
    }
}
