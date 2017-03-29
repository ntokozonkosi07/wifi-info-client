package za.co.wifi.info.client.web;

import za.co.wifi.info.client.web.model.DeviceInfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/wifi-info-rs/restapi/device")
public class DeviceController {

    @RequestMapping(path = "/info", method = RequestMethod.GET)
    @ResponseBody
    DeviceInfo getDeviceInfo() {
        return new DeviceInfo();
    }
}
