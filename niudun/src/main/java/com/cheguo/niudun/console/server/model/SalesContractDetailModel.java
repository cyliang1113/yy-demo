package com.cheguo.niudun.console.server.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 表名 sales_contract_detail
 */
public class SalesContractDetailModel implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 销售单号
     */
    private String orderNumber;

    /**
     * 出库状态
     */
    private String storageStatus;

    /**
     * 开票状态
     */
    private String ticketStatus;

    /**
     * 车架号
     */
    private String vin;

    /**
     * 库存id
     */
    private Integer storageId;

    /**
     * 车型Id
     */
    private Integer carTypeId;

    /**
     * 车型
     */
    private String carTypeDesc;

    /**
     * 官方指导价
     */
    private BigDecimal guidingPrice;

    /**
     * 车辆颜色
     */
    private String colour;

    /**
     * 内饰颜色
     */
    private String interiorColour;

    /**
     * 生产日期
     */
    private Date productionDate;

    /**
     * 发动机号码
     */
    private String carEngineNo;

    /**
     * 合格证编号
     */
    private String certificationCode;

    /**
     * 销售价格(含税)
     */
    private BigDecimal sellQuote;

    /**
     * 删除标识 1=删除 0=有效
     */
    private String isDeleted;

    /**
     * 附加字段
     */
    private String extraJson;

    /**
     * 备注
     */
    private String remark;

    /**
     * 新增时间
     */
    private Date createTime;

    /**
     * 创建人姓名
     */
    private String creatorName;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 修改人姓名
     */
    private String modifierName;

    /**
     * sales_contract_detail
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 销售单号
     * @return order_number 销售单号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * 销售单号
     * @param orderNumber 销售单号
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * 出库状态
     * @return storage_status 出库状态
     */
    public String getStorageStatus() {
        return storageStatus;
    }

    /**
     * 出库状态
     * @param storageStatus 出库状态
     */
    public void setStorageStatus(String storageStatus) {
        this.storageStatus = storageStatus;
    }

    /**
     * 开票状态
     * @return ticket_status 开票状态
     */
    public String getTicketStatus() {
        return ticketStatus;
    }

    /**
     * 开票状态
     * @param ticketStatus 开票状态
     */
    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    /**
     * 车架号
     * @return vin 车架号
     */
    public String getVin() {
        return vin;
    }

    /**
     * 车架号
     * @param vin 车架号
     */
    public void setVin(String vin) {
        this.vin = vin;
    }

    /**
     * 库存id
     * @return storage_id 库存id
     */
    public Integer getStorageId() {
        return storageId;
    }

    /**
     * 库存id
     * @param storageId 库存id
     */
    public void setStorageId(Integer storageId) {
        this.storageId = storageId;
    }

    /**
     * 车型Id
     * @return car_type_id 车型Id
     */
    public Integer getCarTypeId() {
        return carTypeId;
    }

    /**
     * 车型Id
     * @param carTypeId 车型Id
     */
    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    /**
     * 车型
     * @return car_type_desc 车型
     */
    public String getCarTypeDesc() {
        return carTypeDesc;
    }

    /**
     * 车型
     * @param carTypeDesc 车型
     */
    public void setCarTypeDesc(String carTypeDesc) {
        this.carTypeDesc = carTypeDesc;
    }

    /**
     * 官方指导价
     * @return guiding_price 官方指导价
     */
    public BigDecimal getGuidingPrice() {
        return guidingPrice;
    }

    /**
     * 官方指导价
     * @param guidingPrice 官方指导价
     */
    public void setGuidingPrice(BigDecimal guidingPrice) {
        this.guidingPrice = guidingPrice;
    }

    /**
     * 车辆颜色
     * @return colour 车辆颜色
     */
    public String getColour() {
        return colour;
    }

    /**
     * 车辆颜色
     * @param colour 车辆颜色
     */
    public void setColour(String colour) {
        this.colour = colour;
    }

    /**
     * 内饰颜色
     * @return interior_colour 内饰颜色
     */
    public String getInteriorColour() {
        return interiorColour;
    }

    /**
     * 内饰颜色
     * @param interiorColour 内饰颜色
     */
    public void setInteriorColour(String interiorColour) {
        this.interiorColour = interiorColour;
    }

    /**
     * 生产日期
     * @return production_date 生产日期
     */
    public Date getProductionDate() {
        return productionDate;
    }

    /**
     * 生产日期
     * @param productionDate 生产日期
     */
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    /**
     * 发动机号码
     * @return car_engine_no 发动机号码
     */
    public String getCarEngineNo() {
        return carEngineNo;
    }

    /**
     * 发动机号码
     * @param carEngineNo 发动机号码
     */
    public void setCarEngineNo(String carEngineNo) {
        this.carEngineNo = carEngineNo;
    }

    /**
     * 合格证编号
     * @return certification_code 合格证编号
     */
    public String getCertificationCode() {
        return certificationCode;
    }

    /**
     * 合格证编号
     * @param certificationCode 合格证编号
     */
    public void setCertificationCode(String certificationCode) {
        this.certificationCode = certificationCode;
    }

    /**
     * 销售价格(含税)
     * @return sell_quote 销售价格(含税)
     */
    public BigDecimal getSellQuote() {
        return sellQuote;
    }

    /**
     * 销售价格(含税)
     * @param sellQuote 销售价格(含税)
     */
    public void setSellQuote(BigDecimal sellQuote) {
        this.sellQuote = sellQuote;
    }

    /**
     * 删除标识 1=删除 0=有效
     * @return is_deleted 删除标识 1=删除 0=有效
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * 删除标识 1=删除 0=有效
     * @param isDeleted 删除标识 1=删除 0=有效
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 附加字段
     * @return extra_json 附加字段
     */
    public String getExtraJson() {
        return extraJson;
    }

    /**
     * 附加字段
     * @param extraJson 附加字段
     */
    public void setExtraJson(String extraJson) {
        this.extraJson = extraJson;
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 新增时间
     * @return create_time 新增时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 新增时间
     * @param createTime 新增时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人姓名
     * @return creator_name 创建人姓名
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * 创建人姓名
     * @param creatorName 创建人姓名
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * 修改时间
     * @return modify_time 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 修改人姓名
     * @return modifier_name 修改人姓名
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 修改人姓名
     * @param modifierName 修改人姓名
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", storageStatus=").append(storageStatus);
        sb.append(", ticketStatus=").append(ticketStatus);
        sb.append(", vin=").append(vin);
        sb.append(", storageId=").append(storageId);
        sb.append(", carTypeId=").append(carTypeId);
        sb.append(", carTypeDesc=").append(carTypeDesc);
        sb.append(", guidingPrice=").append(guidingPrice);
        sb.append(", colour=").append(colour);
        sb.append(", interiorColour=").append(interiorColour);
        sb.append(", productionDate=").append(productionDate);
        sb.append(", carEngineNo=").append(carEngineNo);
        sb.append(", certificationCode=").append(certificationCode);
        sb.append(", sellQuote=").append(sellQuote);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", extraJson=").append(extraJson);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", creatorName=").append(creatorName);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", modifierName=").append(modifierName);
        sb.append("]");
        return sb.toString();
    }
}