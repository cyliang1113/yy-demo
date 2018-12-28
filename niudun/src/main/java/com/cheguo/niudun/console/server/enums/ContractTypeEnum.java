package com.cheguo.niudun.console.server.enums;

/**
 * Created by wukuijun on 2018/9/6
 */
public enum ContractTypeEnum {
    DIRECTPURCHASE("directpurchase","直采"),
    THIRDPURCHASE("thirdpurchase","直采(第三方采购)"),
    REPLACEPURCHASE("replacepurchase","代采垫资"),
    HEADPURCHASE("headpurchase","总部集采");

    ContractTypeEnum(String code, String value){
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

    public static ContractTypeEnum getByCode(String code){
        for (ContractTypeEnum contractTypeEnum : ContractTypeEnum.values()){
            if(contractTypeEnum.getCode().equals(code)){
                return contractTypeEnum;
            }
        }
        return null;
    }
}
