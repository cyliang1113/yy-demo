package com.cheguo.niudun.console.server.enums;

/**
 * 库存状态枚举类
 * Created by wukuijun on 2018/9/11
 */
public enum StorageStatusEnum {
    INSTORAGE("instorage","入库"),
    OUTSTORAGE("outstorage","出库"),
    UNSTORAGE("unstorage","待入库"),
    TOSTORAGE("tostorage","待出库");

    StorageStatusEnum(String code, String value){
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

    public static StorageStatusEnum getByCode(String code){
        for (StorageStatusEnum storageStatusEnum : StorageStatusEnum.values()){
            if(storageStatusEnum.getCode().equals(code)){
                return storageStatusEnum;
            }
        }
        return null;
    }
}
