package com.cheguo.niudun.console.server.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.cheguo.toolkit.utils.DateUtils;

import java.math.BigDecimal;
import java.util.Date;

public class PurchaseModel {
    private Integer id;

    private String orderNumber;

    private Integer companyId;

    private String companyName;

    private Integer userid;

    private Integer totalCount;

    private BigDecimal totalAmount;

    private String vin;

    private Integer carTypeId;

    private String carTypeDesc;

    private BigDecimal guidingPrice;

    private String carEngineNo;

    private String colour;

    private String interiorColour;

    private BigDecimal serviceFee;

    private BigDecimal depositAmount;

    private Date depositAmountTime;

    private Date tailAmountTime;

    private String carState;

    private String remark;

    private String isDeleted;

    private Date createTime;

    private Date modifyTime;

    private Date inStockTime; //入库时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCarTypeDesc() {
        return carTypeDesc;
    }

    public void setCarTypeDesc(String carTypeDesc) {
        this.carTypeDesc = carTypeDesc;
    }

    public BigDecimal getGuidingPrice() {
        return guidingPrice;
    }

    public void setGuidingPrice(BigDecimal guidingPrice) {
        this.guidingPrice = guidingPrice;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getInteriorColour() {
        return interiorColour;
    }

    public void setInteriorColour(String interiorColour) {
        this.interiorColour = interiorColour;
    }

    public BigDecimal getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(BigDecimal serviceFee) {
        this.serviceFee = serviceFee;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    @JSONField(format="yyyy-MM-dd")
    public Date getDepositAmountTime() {
        return depositAmountTime;
    }

    public void setDepositAmountTime(Date depositAmountTime) {
        this.depositAmountTime = depositAmountTime;
    }

    @JSONField(format="yyyy-MM-dd")
    public Date getTailAmountTime() {
        return tailAmountTime;
    }

    public void setTailAmountTime(Date tailAmountTime) {
        this.tailAmountTime = tailAmountTime;
    }

    public String getCarState() {
        return carState;
    }

    public void setCarState(String carState) {
        this.carState = carState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getInStockTime() {
        return inStockTime;
    }

    public void setInStockTime(Date inStockTime) {
        this.inStockTime = inStockTime;
    }

    public Integer getInStockDays(){
        if(inStockTime == null){
            return null;
        }
        Date today = new Date();
        Date startOfToday = DateUtils.getStartOfDay(today);
        Date startOfInStockTime = DateUtils.getStartOfDay(inStockTime);

        long m = startOfToday.getTime() - startOfInStockTime.getTime();
        if(m > 0){
            long oneDay = 1000 * 60 * 60 * 24;
            int days = (int) (m/oneDay);
            return days + 1;
        }else if(m == 0){
            return 1;
        }else{
            return null;
        }

    }

    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getCarEngineNo() {
        return carEngineNo;
    }

    public void setCarEngineNo(String carEngineNo) {
        this.carEngineNo = carEngineNo;
    }
}
