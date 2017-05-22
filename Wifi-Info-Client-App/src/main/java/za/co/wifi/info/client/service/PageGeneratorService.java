
package za.co.wifi.info.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope("singleton")
public class PageGeneratorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageGeneratorService.class.getName());
}
