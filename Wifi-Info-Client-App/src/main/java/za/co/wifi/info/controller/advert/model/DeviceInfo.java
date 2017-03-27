package za.co.wifi.info.controller.advert.model;

import java.util.Objects;
import za.co.wifi.info.util.SystemInfoUtil;

public class DeviceInfo {

    private final String osName;

    private final String osVersion;
    
    private final String osArchitecture;
    
    private final int availableProcessors;
    
    private final long freeMemory;
    
    private final String maxMemory;
    
    private final long availableMemory;

    public DeviceInfo() {
        this.osName = SystemInfoUtil.getOSName();
        this.osVersion = SystemInfoUtil.getOSVersion();
        this.osArchitecture = SystemInfoUtil.getOSArchitecture();
        this.availableProcessors = SystemInfoUtil.getAvailableProcessors();
        this.freeMemory = SystemInfoUtil.getFreeMemory();
        this.maxMemory = SystemInfoUtil.getMaxMemory();
        this.availableMemory = SystemInfoUtil.getAvailableMemory();
    }

    public String getOsName() {
        return osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getOsArchitecture() {
        return osArchitecture;
    }

    public int getAvailableProcessors() {
        return availableProcessors;
    }

    public long getFreeMemory() {
        return freeMemory;
    }

    public String getMaxMemory() {
        return maxMemory;
    }

    public long getAvailableMemory() {
        return availableMemory;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.osName);
        hash = 31 * hash + Objects.hashCode(this.osVersion);
        hash = 31 * hash + Objects.hashCode(this.osArchitecture);
        hash = 31 * hash + this.availableProcessors;
        hash = 31 * hash + (int) (this.freeMemory ^ (this.freeMemory >>> 32));
        hash = 31 * hash + Objects.hashCode(this.maxMemory);
        hash = 31 * hash + (int) (this.availableMemory ^ (this.availableMemory >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DeviceInfo other = (DeviceInfo) obj;
        if (this.availableProcessors != other.availableProcessors) {
            return false;
        }
        if (this.freeMemory != other.freeMemory) {
            return false;
        }
        if (this.availableMemory != other.availableMemory) {
            return false;
        }
        if (!Objects.equals(this.osName, other.osName)) {
            return false;
        }
        if (!Objects.equals(this.osVersion, other.osVersion)) {
            return false;
        }
        if (!Objects.equals(this.osArchitecture, other.osArchitecture)) {
            return false;
        }
        if (!Objects.equals(this.maxMemory, other.maxMemory)) {
            return false;
        }
        return true;
    }
}
