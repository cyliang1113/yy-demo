package com.cheguo.niudun.console.server.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.cheguo.carsaas.facade.response.AttachmentResponse;
import com.cheguo.carsaas.facade.response.PurchaseCarDetailResponse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by wukuijun on 2018/9/29
 */
public class NewContractResponse implements Serializable{

    private static final long serialVersionUID = 4007381629806877340L;
    private Integer id;

    /**
     * 采购审批号 CG开头+时间戳
     */
    private String contractNumber;

    /**
     * 车商Id
     */
    private Integer companyId;

    /**
     * 车商名称
     */
    private String companyName;

    /**
     * directpurchase 直采 thirdpurchase 直采(第三方采购) replacepurchase 代采垫资 headpurchase总部集采
     */
    private String contractType;

    /**
     * headquarter 提供至总部  straightshop 提供至直营店 customer 提供至客户
     */
    private String openTicket;

    /**
     * 总开票价
     */
    private BigDecimal ticketPrice;

    /**
     * 合同方id
     */
    private Integer contractorId;

    /**
     * 合同方
     */
    private String contractor;

    /**
     * 发车地城市邮政编码
     */
    private String fromCode;

    /**
     * 发车地城市名
     */
    private String fromPlace;

    /**
     * 接车地城市编码
     */
    private String toCode;

    /**
     * 接车地
     */
    private String toPlace;

    /**
     * 车辆价格
     */
    private BigDecimal totalAmount;

    /**
     * 采购车辆数量
     */
    private Integer totalCount;

    /**
     * 保证金
     */
    private BigDecimal bond;

    /**
     * 定金
     */
    private BigDecimal firstPay;

    /**
     * 尾款
     */
    private BigDecimal finalPay;

    /**
     * 仓储费
     */
    private BigDecimal storageFee;

    /**
     * 中介费
     */
    private BigDecimal agencyFee;

    /**
     * 运输费
     */
    private BigDecimal freightFee;

    /**
     * 代收代付
     */
    private BigDecimal waitFee;

    /**
     * 合同总价款
     */
    private BigDecimal fullAmount;

    /**
     * 已支付金额
     */
    private BigDecimal payMoney;

    /**
     * 审批中金额
     */
    private BigDecimal approvalMoney;

    /**
     * 回款天数
     */
    private Integer returnDay;

    /**
     * 已定车辆数
     */
    private Integer reserveCar;

    /**
     * 总价(含税)
     */
    private BigDecimal totalPrice;

    /**
     * processing 审批中 success 通过 fail 失败
     */
    private String approvalStatus;

    /**
     * unsettlemented 待结算 settlemented已结算
     */
    private String payStatus;

    /**
     * 入库状态  in 已入库 waitin待入库
     */
    private String storageStatus;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 申请日期
     */

    private Date applyDate;

    /**
     * 修改时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    /**
     * 车辆详情
     */
    private List<PurchaseCarDetailResponse> purchaseCarDetailResponses;

    /**
     * 附件
     */
    private List<AttachmentResponse> attachmentResponses ;

    public List<AttachmentResponse> getAttachmentResponses() {
        return attachmentResponses;
    }

    public void setAttachmentResponses(List<AttachmentResponse> attachmentResponses) {
        this.attachmentResponses = attachmentResponses;
    }

    public String getStorageStatus() {
        return storageStatus;
    }

    public void setStorageStatus(String storageStatus) {
        this.storageStatus = storageStatus;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
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

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getOpenTicket() {
        return openTicket;
    }

    public void setOpenTicket(String openTicket) {
        this.openTicket = openTicket;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getContractorId() {
        return contractorId;
    }

    public void setContractorId(Integer contractorId) {
        this.contractorId = contractorId;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getFromCode() {
        return fromCode;
    }

    public void setFromCode(String fromCode) {
        this.fromCode = fromCode;
    }

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToCode() {
        return toCode;
    }

    public void setToCode(String toCode) {
        this.toCode = toCode;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getBond() {
        return bond;
    }

    public void setBond(BigDecimal bond) {
        this.bond = bond;
    }

    public BigDecimal getFirstPay() {
        return firstPay;
    }

    public void setFirstPay(BigDecimal firstPay) {
        this.firstPay = firstPay;
    }

    public BigDecimal getFinalPay() {
        return finalPay;
    }

    public void setFinalPay(BigDecimal finalPay) {
        this.finalPay = finalPay;
    }

    public BigDecimal getStorageFee() {
        return storageFee;
    }

    public void setStorageFee(BigDecimal storageFee) {
        this.storageFee = storageFee;
    }

    public BigDecimal getAgencyFee() {
        return agencyFee;
    }

    public void setAgencyFee(BigDecimal agencyFee) {
        this.agencyFee = agencyFee;
    }

    public BigDecimal getFreightFee() {
        return freightFee;
    }

    public void setFreightFee(BigDecimal freightFee) {
        this.freightFee = freightFee;
    }

    public BigDecimal getWaitFee() {
        return waitFee;
    }

    public void setWaitFee(BigDecimal waitFee) {
        this.waitFee = waitFee;
    }

    public BigDecimal getFullAmount() {
        return fullAmount;
    }

    public void setFullAmount(BigDecimal fullAmount) {
        this.fullAmount = fullAmount;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public BigDecimal getApprovalMoney() {
        return approvalMoney;
    }

    public void setApprovalMoney(BigDecimal approvalMoney) {
        this.approvalMoney = approvalMoney;
    }

    public Integer getReturnDay() {
        return returnDay;
    }

    public void setReturnDay(Integer returnDay) {
        this.returnDay = returnDay;
    }

    public Integer getReserveCar() {
        return reserveCar;
    }

    public void setReserveCar(Integer reserveCar) {
        this.reserveCar = reserveCar;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
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

    public List<PurchaseCarDetailResponse> getPurchaseCarDetailResponses() {
        return purchaseCarDetailResponses;
    }

    public void setPurchaseCarDetailResponses(List<PurchaseCarDetailResponse> purchaseCarDetailResponses) {
        this.purchaseCarDetailResponses = purchaseCarDetailResponses;
    }

    @Override
    public String toString() {
        return "ContractResponse{" +
                "id=" + id +
                ", contractNumber='" + contractNumber + '\'' +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", contractType='" + contractType + '\'' +
                ", openTicket='" + openTicket + '\'' +
                ", ticketPrice=" + ticketPrice +
                ", contractorId=" + contractorId +
                ", contractor='" + contractor + '\'' +
                ", fromCode='" + fromCode + '\'' +
                ", fromPlace='" + fromPlace + '\'' +
                ", toCode='" + toCode + '\'' +
                ", toPlace='" + toPlace + '\'' +
                ", totalAmount=" + totalAmount +
                ", totalCount=" + totalCount +
                ", bond=" + bond +
                ", firstPay=" + firstPay +
                ", finalPay=" + finalPay +
                ", storageFee=" + storageFee +
                ", agencyFee=" + agencyFee +
                ", freightFee=" + freightFee +
                ", waitFee=" + waitFee +
                ", fullAmount=" + fullAmount +
                ", payMoney=" + payMoney +
                ", approvalMoney=" + approvalMoney +
                ", returnDay=" + returnDay +
                ", reserveCar=" + reserveCar +
                ", totalPrice=" + totalPrice +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", purchaseCarDetailResponses=" + purchaseCarDetailResponses +
                '}';
    }
}
