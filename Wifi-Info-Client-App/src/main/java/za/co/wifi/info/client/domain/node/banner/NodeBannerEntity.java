package za.co.wifi.info.client.domain.node.banner;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import za.co.wifi.info.client.domain.node.NodeDataEntity;

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
@Entity(name = "l_node_banner")
public class NodeBannerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "node_ref")
    private String nodeRef;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "effective_from", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "effective_to", nullable = true)
    private Date endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "node_data_ref", referencedColumnName = "node_data_ref", nullable = false)
    private NodeDataEntity nodeData;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "node_link_data_ref", referencedColumnName = "node_data_ref", nullable = true)
    private NodeDataEntity nodeLinkData;

    public String getNodeRef() {
        return nodeRef;
    }

    public void setNodeRef(String nodeRef) {
        this.nodeRef = nodeRef;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public NodeDataEntity getNodeData() {
        return nodeData;
    }

    public void setNodeData(NodeDataEntity nodeData) {
        this.nodeData = nodeData;
    }

    public NodeDataEntity getNodeLinkData() {
        return nodeLinkData;
    }

    public void setNodeLinkData(NodeDataEntity nodeLinkData) {
        this.nodeLinkData = nodeLinkData;
    }
}
