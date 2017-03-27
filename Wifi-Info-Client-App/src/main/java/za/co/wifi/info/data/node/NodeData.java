package za.co.wifi.info.data.node;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import za.co.wifi.info.data.BaseBinaryDataEntity;

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
@Entity(name = "l_node_data")
public class NodeData extends BaseBinaryDataEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "node_data_ref")
    @GeneratedValue(generator = "node_data", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "node_data", sequenceName = "node_data_seq", allocationSize = 1, initialValue = 1)
    private Long advertDataRef;

    public Long getAdvertDataRef() {
        return advertDataRef;
    }

    public void setAdvertDataRef(Long advertDataRef) {
        this.advertDataRef = advertDataRef;
    }
}
