package za.co.wifi.info.client.service;

public interface HtmlPageGeneratorServiceConstants {

    public final String BANNER_HTML_TEMPLATE = new StringBuilder()
            .append("<div class=\"col-lg-3 col-md-4 col-xs-6 thumb\">\n")
            .append("    <a class=\"thumbnail\" href=\"$bannerAdvert\" target=\"_blank\">\n")
            .append("        <img class=\"img-responsive\" src=\"$bannerLogo\" alt=\"Download this advert\">\n")
            .append("    </a>\n")
            .append("</div>")
            .toString();

    public final String CATEGORY_HTML_TEMPLATE = new StringBuilder()
            .append("<div class=\"row\">\n")
            .append("    <div class=\"col-lg-12\">\n")
            .append("        <h4 style=\"background:#f8f8f8;padding-top: 7px;padding-bottom: 7px;padding-left:7px\">\n")
            .append("            $categoryName\n")
            .append("        </h4>\n")
            .append("    </div>\n")
            .append("\n")
            .append("    <div id=\"adverts\">$categoryAdverts</div>\n")
            .append("</div>")
            .toString();

    public final String ADVERT_HTML_TEMPLATE = new StringBuilder()
            .append("<div class=\"col-lg-12 col-md-12 col-xs-12 thumb\">\n")
            .append("    <a class=\"thumbnail\">\n")
            .append("        <img src=\"$advert\">\n")
            .append("    </a>\n")
            .append("</div>")
            .toString();

    public final String WELCOME_HTML_TEXT = new StringBuilder()
            .append("<div class=\"col-lg-12\">\n")
            .append("                <p style=\"background:#fba59b;padding-top: 7px;padding-bottom: 7px;padding-left:7px\">\n")
            .append("                    Welcome to Wifi-Info, your favorite hot-spot. To see more adverts,\n")
            .append("                    open your web browser and type <strong>wifi-info.com</strong>\n")
            .append("                </p>\n")
            .append("            </div>")
            .toString();
}
