package za.co.wifi.info.client.domain.node;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import za.co.wifi.info.client.domain.BaseEntity;

/**
 * Copyright (C) 2014 RynMag Management Systems. All rights reserved.
 * <p>
 * This software contains confidential proprietary information belonging to
 * RynMag Management Systems. No part of this information may be used,
 * reproduced, or stored without prior written consent of RynMag Management
 * Systems.
 *
 * @author Zifa Mathebula <zifamathebula@gmail.com>
 * @version 1.0
 */
@Entity(name = "l_node")
public class NodeEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "node_ref")
    @GeneratedValue(generator = "node", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "node", sequenceName = "node_seq", allocationSize = 1, initialValue = 1)
    private Long nodeRef;

    @Column(name = "mac_address", length = 255, nullable = true)
    private String macAddress;

    @Column(name = "device_ref", length = 255, nullable = true)
    private String deviceRef;

    @Column(name = "node_address", length = 255, nullable = true)
    private String nodeAddress;

    @Column(name = "node_name", length = 255, nullable = true)
    private String nodeName;

    @Column(name = "node_category_ref", nullable = true)
    private Long categoryRef;

    @Column(name = "category_name", length = 80, nullable = true)
    private String categoryName;

    public Long getNodeRef() {
        return nodeRef;
    }

    public void setNodeRef(Long nodeRef) {
        this.nodeRef = nodeRef;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getDeviceRef() {
        return deviceRef;
    }

    public void setDeviceRef(String deviceRef) {
        this.deviceRef = deviceRef;
    }

    public String getNodeAddress() {
        return nodeAddress;
    }

    public void setNodeAddress(String nodeAddress) {
        this.nodeAddress = nodeAddress;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public Long getCategoryRef() {
        return categoryRef;
    }

    public void setCategoryRef(Long categoryRef) {
        this.categoryRef = categoryRef;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
