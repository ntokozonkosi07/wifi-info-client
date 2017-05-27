package za.co.wifi.info.client.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import za.co.wifi.info.client.web.model.Advert;
import za.co.wifi.info.client.web.model.BannerLink;
import za.co.wifi.info.client.web.model.Category;

@Service
@Scope("singleton")
public class HtmlHtmlPageGeneratorService implements HtmlPageGeneratorServiceConstants {

    private static final Logger LOGGER = LoggerFactory.getLogger(HtmlHtmlPageGeneratorService.class.getName());

    private final String webRootPath;
    private final String advertRootPath;

    private Document templateHtml = null;

    public HtmlHtmlPageGeneratorService(@Value("${app.config.device.web.root.path}") String webRootPath,
                                        @Value("${app.config.device.web.adverts.root.path}") String advertRootPath) throws IOException {
        this.webRootPath = webRootPath;
        this.advertRootPath = advertRootPath;

        File rootHtmlFile = new File(webRootPath + "template.html");
        templateHtml = Jsoup.parse(rootHtmlFile, "UTF-8");
    }

    public void generatePage(List<BannerLink> bannerLinkAdverts, List<Category> categoryAdverts) throws IOException {
        LOGGER.info("Creating html page");

        StringBuilder bannerHtml = new StringBuilder();
        for (BannerLink bannerLink : bannerLinkAdverts) {
            String htmlBanner = HtmlPageGeneratorServiceConstants.BANNER_HTML_TEMPLATE;
            htmlBanner = htmlBanner.replace("$bannerLogo", advertRootPath + bannerLink.getBannerLinkRefNo() + "." + getFileExtension(bannerLink.getBannerAdvert().getFileName()));
            htmlBanner = htmlBanner.replace("$bannerAdvert", advertRootPath + bannerLink.getBannerLinkRefNo() + "." + getFileExtension(bannerLink.getBannerLink().getFileName()));

            bannerHtml.append(htmlBanner);
        }

        StringBuilder categoryHtml = new StringBuilder();
        for (Category category : categoryAdverts) {
            String htmlCategory = HtmlPageGeneratorServiceConstants.CATEGORY_HTML_TEMPLATE;
            htmlCategory = htmlCategory.replace("$categoryName", category.getCategoryName());

            StringBuilder advertsHtml = new StringBuilder();
            for (Advert advert : category.getAdverts()) {
                String htmlAdvert = HtmlPageGeneratorServiceConstants.ADVERT_HTML_TEMPLATE;
                htmlAdvert = htmlAdvert.replace("$advert", advertRootPath + advert.getAdvertRefNo() + "." + getFileExtension(advert.getFileName()));
                advertsHtml.append(htmlAdvert);
            }

            htmlCategory = htmlCategory.replace("$categoryAdverts", advertsHtml);

            categoryHtml.append(htmlCategory);
        }

        templateHtml.getElementById("banner").append(bannerHtml.toString());
        templateHtml.getElementById("category").append(categoryHtml.toString());

        //Generate full site
        final File fullSite = new File(new File(webRootPath), "full.html");
        FileUtils.writeStringToFile(fullSite, templateHtml.outerHtml(), "UTF-8");

        //Generate captive portal site
        templateHtml.getElementById("banners-section").remove();
        templateHtml.getElementById("welcome").append(WELCOME_HTML_TEXT);

        final File captivePortalSite = new File(new File(webRootPath), "captive.html");
        FileUtils.writeStringToFile(captivePortalSite, templateHtml.outerHtml(), "UTF-8");


        LOGGER.info("Created html page");
    }

    public String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }
}
