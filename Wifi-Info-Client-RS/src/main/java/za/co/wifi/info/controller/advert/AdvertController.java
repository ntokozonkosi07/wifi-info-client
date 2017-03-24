package za.co.wifi.info.controller.advert;

import za.co.wifi.info.controller.advert.model.DeviceInfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wifi-info/restapi")
public class AdvertController {

    @RequestMapping(path = "/device/info", method = RequestMethod.GET)
    @ResponseBody
    DeviceInfo getDeviceInfo() {
        return new DeviceInfo();
    }
}
