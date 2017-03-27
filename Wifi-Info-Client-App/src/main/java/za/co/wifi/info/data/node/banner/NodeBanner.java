package za.co.wifi.info.data.node.banner;

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
import za.co.wifi.info.data.node.NodeData;

/**
 * Copyright (C) 2014 RynMag Management Systems. All rights reserved.
 *
 * This software contains confidential proprietary information belonging to
 * RynMag Management Systems. No part of this information may be used,
 * reproduced, or stored without prior written consent of RynMag Management
 * Systems.
 *
 * @author Zifa Mathebula <zifamathebula@gmail.com>
 *
 * @version 1.0
 */
@Entity(name = "l_node_banner")
public class NodeBanner implements Serializable {

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
    private NodeData nodeData;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "node_link_data_ref", referencedColumnName = "node_data_ref", nullable = true)
    private NodeData nodeLinkData;

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

    public NodeData getNodeData() {
        return nodeData;
    }

    public void setNodeData(NodeData nodeData) {
        this.nodeData = nodeData;
    }

    public NodeData getNodeLinkData() {
        return nodeLinkData;
    }

    public void setNodeLinkData(NodeData nodeLinkData) {
        this.nodeLinkData = nodeLinkData;
    }
}
