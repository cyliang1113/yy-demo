/*
 * Copyright (c) 2018. 浙江车国网络科技有限公司.  All rights reserved.
 */
package com.cheguo.niudun.console.server.model;

import lombok.Data;

import java.util.Date;

/**
 * 单据附件
 */
@Data
public class AccessoryModel {
    private Long id;

    private String orderNumber;// 订单编号

    private String type;// 订单中图片类型

    private Integer rank;// 顺序号

    private String photoUrl;// 图片地址，必填

    private String remark;

    private String isDeleted;

    private Date createTime;

    private Date modifyTime;

}