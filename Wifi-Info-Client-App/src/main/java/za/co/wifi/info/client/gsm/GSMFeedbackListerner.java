package za.co.wifi.info.client.gsm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GSMFeedbackListerner extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(GSMFeedbackListerner.class.getName());

    private final Process process;

    private int status;

    public GSMFeedbackListerner(Process process) {
        this.process = process;
    }

    @Override
    public void run() {
        try {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line;

            while ((line = reader.readLine()) != null) {
                LOGGER.info(line);

                if (line.contains("local  IP address")) {
                    status = 1;
                }
                if (line.contains("remote IP address")) {
                    status = 2;
                }
                if (line.contains("primary   DNS address")) {
                    status = 3;
                }
                if (line.contains("secondary DNS address")) {
                    status = 4;
                }
            }

            reader.close();
        } catch (final Exception e) {
        }
    }

    public int getStatus() {
        return status;
    }
}
