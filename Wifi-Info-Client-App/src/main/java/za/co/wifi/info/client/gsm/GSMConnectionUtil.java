package za.co.wifi.info.client.gsm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class GSMConnectionUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(GSMConnectionUtil.class.getName());

    public static void main(String[] args) {
        try {
            GSMConnectionUtil connectionUtil = new GSMConnectionUtil();
            int connnectionRetries = 0;
            while (true) {
                connectionUtil.connect();

                if (connectionUtil.getStatus("https://www.google.com")) {
                    break;
                }

                Thread.sleep(30000);

                if (connnectionRetries >= 10) {
                    System.exit(0);
                }

                connnectionRetries++;

                System.out.println("");
                System.out.println("===============================================");
                System.out.println("");
            }

            connectionUtil.disconnect();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void connect() {
        try {
            ProcessBuilder wvdailProcessBuilder = new ProcessBuilder("wvdial");
            final Process wvdailProcess = wvdailProcessBuilder.start();

            final Thread connectionListenerThread = new Thread() {
                @Override
                public void run() {
                    try {
                        try (BufferedReader reader = new BufferedReader(
                                new InputStreamReader(wvdailProcess.getErrorStream()))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                LOGGER.info(line);
                            }
                        }
                    } catch (final IOException e) {
                        LOGGER.error("Unexpected error reading device stream");
                    }
                }
            };

            connectionListenerThread.start();

            Thread.sleep(10000);

        } catch (IOException | InterruptedException ex) {
            LOGGER.error("Unexpected error connecting to Gsm Device");
        }
    }

    public boolean disconnect() {
        ProcessBuilder wvdailProcessBuilder = new ProcessBuilder("pkill", "wvdial");
        try {
            wvdailProcessBuilder.start();
            return true;
        } catch (IOException ex) {
            LOGGER.error("Unexpected error terminating connection", ex);

            return false;
        }
    }

    public boolean getStatus(String url) {
        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int code = connection.getResponseCode();
            return code == 200;
        } catch (IOException ex) {
            LOGGER.error(new MessageFormat("Unexpected error connecting to : {0}")
                    .format(new Object[]{url}));
        }

        return false;
    }
}
