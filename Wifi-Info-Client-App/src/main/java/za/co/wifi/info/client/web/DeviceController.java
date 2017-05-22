package za.co.wifi.info.client.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import za.co.wifi.info.client.web.model.DeviceInfo;

@Controller
@RequestMapping("/wifi-info-rs/restapi/device")
public class DeviceController {

    @RequestMapping(path = "/info", method = RequestMethod.GET)
    @ResponseBody
    DeviceInfo getDeviceInfo() {
        return new DeviceInfo();
    }
}
