package com.cheguo.niudun.console.server.enums;
/**
 * 进销存付款类型
 * Created by wukuijun on 2018/9/11
 */
public enum PayTypeEnum {
    FIRSTPAY("firstpay","定金"),
    FINALPAY("finalpay","尾款"),
    FULLAMOUNT("fullamount","全款"),
    BOND("bond","保证金"),
    SAMPLEBOND("samplebond","样车保证金"),
    WAITFEE("waitfee","代收代付款");

    PayTypeEnum(String code, String value){
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

    public static PayTypeEnum getByCode(String code){
        for (PayTypeEnum payTypeEnum : PayTypeEnum.values()){
            if(payTypeEnum.getCode().equals(code)){
                return payTypeEnum;
            }
        }
        return null;
    }
}
