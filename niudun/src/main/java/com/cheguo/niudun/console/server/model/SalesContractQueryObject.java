package com.cheguo.niudun.console.server.model;

import lombok.Data;

/**
 * Created by liuyang on 2018/9/21.
 */
@Data
public class SalesContractQueryObject {
    private String pageSize;
    private String currentPage;
    private String orderNumber;
    private String type;
    private String companyName;
    private String contractor;
    private String status;
    private String settlementStatus;
    private String storageStatus;
    private String applyTimeStart;
    private String applyTimeEnd;
}
