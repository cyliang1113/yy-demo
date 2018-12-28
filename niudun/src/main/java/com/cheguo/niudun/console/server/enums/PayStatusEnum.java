package com.cheguo.niudun.console.server.enums;
/**
 * 结算状态
 * Created by wukuijun on 2018/9/13
 */
public enum PayStatusEnum {
    UNSETTLEMENTED("unsettlemented","待结算"),
    SETTLEMENTED("settlemented","已结算");

    PayStatusEnum(String code, String value){
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

    public static PayStatusEnum getByCode(String code){
        for (PayStatusEnum payStatusEnum : PayStatusEnum.values()){
            if(payStatusEnum.getCode().equals(code)){
                return payStatusEnum;
            }
        }
        return null;
    }
}
