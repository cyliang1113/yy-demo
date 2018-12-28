package com.cheguo.niudun.console.server.enums;
/**
 * 采购合同--审批状态枚举类
 * Created by liuyang on 2018/9/10.
 */
public enum PurchaseApprovalStatusEnum {
    SUCCESS("success","审批通过"),
    FAIL("fail","审批未通过"),
    PROCESSING("processing","审批中");

    PurchaseApprovalStatusEnum(String code, String value){
        this.code = code;
        this.value = value;
    }

    private String code;
    private String value;
    public String getCode() {
        return code;
    }
    public String getValue() {
        return value;
    }

    public static PurchaseApprovalStatusEnum getByCode(String code){
        for (PurchaseApprovalStatusEnum approveStatusEnum : PurchaseApprovalStatusEnum.values()){
            if(approveStatusEnum.getCode().equals(code)){
                return approveStatusEnum;
            }
        }
        return null;
    }
}
