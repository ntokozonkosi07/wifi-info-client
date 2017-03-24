package za.co.wifi.info.util;

import java.io.Serializable;

public class SystemInfoUtil implements Serializable {

    public static String getOSName() {
        return System.getProperty("os.name");
    }

    public static String getOSVersion() {
        return System.getProperty("os.version");
    }

    public static String getOSArchitecture() {
        return System.getProperty("os.arch");
    }

    public static int getAvailableProcessors() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    public static String getMaxMemory() {
        long maxMemory = Runtime.getRuntime().maxMemory();
        return maxMemory == Long.MAX_VALUE ? "No limit" : String.valueOf(maxMemory);
    }

    public static long getAvailableMemory() {
        return Runtime.getRuntime().totalMemory();
    }
}
