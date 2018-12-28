package com.cheguo.niudun.console.server.response;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 表名 sales_contract
 */
public class NewSalesContractResponse implements Serializable {
    /**
     * 销售单号
     */
    private String orderNumber;

    /**
     * 业务类型
     */
    private String type;

    /**
     * 订单状态
     */
    private String status;

    /**
     * 合同方id
     */
    private Integer contractorId;

    /**
     * 合同方
     */
    private String contractor;

    /**
     * 车商id
     */
    private Integer companyId;

    /**
     * 车商名称
     */
    private String companyName;

    /**
     * 用户id
     */
    private Integer userid;

    /**
     * 客户ID
     */
    private Integer customerId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 客户来源类型:online-线上线索,offline-线下新增
     */
    private String customerFrom;

    /**
     * 客户来源
     */
    private String customerSource;

    /**
     * 地址
     */
    private String address;

    /**
     * 详细地址
     */
    private String detailAddress;

    /**
     * 销售总价格(含税)
     */
    private BigDecimal salesQuote;

    /**
     * 定金金额(元)
     */
    private BigDecimal depositAmount;

    /**
     * 保证金金额(元)
     */
    private BigDecimal firstPayAmount;

    /**
     * 尾款金额(元)
     */
    private BigDecimal tailMoney;

    /**
     * 代收待付款
     */
    private String businessItems;

    /**
     * 代收待付款汇总
     */
    private BigDecimal businessAmount;
    /**
     * 服务费
     */
    private String serviceFee;

    /**
     * 上牌服务费
     */
    private String licensePlateFee;

    /**
     * 精品加装费 
     */
    private String addDecorationFee;

    /**
     * 购置税
     */
    private String purchaseTaxFee;

    /**
     * 出库状态
     */
    private String storageStatus;

    /**
     * 出库数量
     */
    private Integer storageTotal;

    /**
     * 已出库数量
     */
    private Integer storageAlready;

    /**
     * 待出库数量
     */
    private Integer unstorage;

    /**
     * 结算状态
     */
    private String settlementStatus;
    /**
     * 开票方
     */
    private String invoiceType;

    /**
     * 应收总额
     */
    private BigDecimal receivablesTotal;

    /**
     * 已收账款
     */
    private BigDecimal receivablesAlready;

    /**
     * 待结算账款
     */
    private BigDecimal unreceivable;

    /**
     * 开票方
     */
    private String ticketType;

    /**
     * 开票状态
     */
    private String ticketStatus;

    /**
     * 开票总额
     */
    private BigDecimal ticketTotal;

    /**
     * 已开票额
     */
    private BigDecimal ticketAlready;

    /**
     * 待开票额
     */
    private BigDecimal unticket;

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
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
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
     * 开票状态
     */
    private String invoiceStatus;
    /**
     * 开票总金额
     */
    private BigDecimal invoiceTotal;

    /**
     * 已开票金额
     */
    private BigDecimal invoiceAlready;

    /**
     * 待开票额
     */
    private BigDecimal uninvoice;

    /**
     * 可开票额
     */
    private BigDecimal   canInvoice;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCustomerFrom() {
        return customerFrom;
    }

    public void setCustomerFrom(String customerFrom) {
        this.customerFrom = customerFrom;
    }

    public String getCustomerSource() {
        return customerSource;
    }

    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public BigDecimal getSalesQuote() {
        return salesQuote;
    }

    public void setSalesQuote(BigDecimal salesQuote) {
        this.salesQuote = salesQuote;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    public BigDecimal getFirstPayAmount() {
        return firstPayAmount;
    }

    public void setFirstPayAmount(BigDecimal firstPayAmount) {
        this.firstPayAmount = firstPayAmount;
    }

    public BigDecimal getTailMoney() {
        return tailMoney;
    }

    public void setTailMoney(BigDecimal tailMoney) {
        this.tailMoney = tailMoney;
    }

    public String getBusinessItems() {
        return businessItems;
    }

    public void setBusinessItems(String businessItems) {
        this.businessItems = businessItems;
    }

    public BigDecimal getBusinessAmount() {
        return businessAmount;
    }

    public void setBusinessAmount(BigDecimal businessAmount) {
        this.businessAmount = businessAmount;
    }

    public String getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(String serviceFee) {
        this.serviceFee = serviceFee;
    }

    public String getLicensePlateFee() {
        return licensePlateFee;
    }

    public void setLicensePlateFee(String licensePlateFee) {
        this.licensePlateFee = licensePlateFee;
    }

    public String getAddDecorationFee() {
        return addDecorationFee;
    }

    public void setAddDecorationFee(String addDecorationFee) {
        this.addDecorationFee = addDecorationFee;
    }

    public String getPurchaseTaxFee() {
        return purchaseTaxFee;
    }

    public void setPurchaseTaxFee(String purchaseTaxFee) {
        this.purchaseTaxFee = purchaseTaxFee;
    }

    public String getStorageStatus() {
        return storageStatus;
    }

    public void setStorageStatus(String storageStatus) {
        this.storageStatus = storageStatus;
    }

    public Integer getStorageTotal() {
        return storageTotal;
    }

    public void setStorageTotal(Integer storageTotal) {
        this.storageTotal = storageTotal;
    }

    public Integer getStorageAlready() {
        return storageAlready;
    }

    public void setStorageAlready(Integer storageAlready) {
        this.storageAlready = storageAlready;
    }

    public Integer getUnstorage() {
        return unstorage;
    }

    public void setUnstorage(Integer unstorage) {
        this.unstorage = unstorage;
    }

    public String getSettlementStatus() {
        return settlementStatus;
    }

    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public BigDecimal getReceivablesTotal() {
        return receivablesTotal;
    }

    public void setReceivablesTotal(BigDecimal receivablesTotal) {
        this.receivablesTotal = receivablesTotal;
    }

    public BigDecimal getReceivablesAlready() {
        return receivablesAlready;
    }

    public void setReceivablesAlready(BigDecimal receivablesAlready) {
        this.receivablesAlready = receivablesAlready;
    }

    public BigDecimal getUnreceivable() {
        return unreceivable;
    }

    public void setUnreceivable(BigDecimal unreceivable) {
        this.unreceivable = unreceivable;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public BigDecimal getTicketTotal() {
        return ticketTotal;
    }

    public void setTicketTotal(BigDecimal ticketTotal) {
        this.ticketTotal = ticketTotal;
    }

    public BigDecimal getTicketAlready() {
        return ticketAlready;
    }

    public void setTicketAlready(BigDecimal ticketAlready) {
        this.ticketAlready = ticketAlready;
    }

    public BigDecimal getUnticket() {
        return unticket;
    }

    public void setUnticket(BigDecimal unticket) {
        this.unticket = unticket;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getExtraJson() {
        return extraJson;
    }

    public void setExtraJson(String extraJson) {
        this.extraJson = extraJson;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public BigDecimal getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(BigDecimal invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }

    public BigDecimal getInvoiceAlready() {
        return invoiceAlready;
    }

    public void setInvoiceAlready(BigDecimal invoiceAlready) {
        this.invoiceAlready = invoiceAlready;
    }

    public BigDecimal getUninvoice() {
        return uninvoice;
    }

    public void setUninvoice(BigDecimal uninvoice) {
        this.uninvoice = uninvoice;
    }

    public BigDecimal getCanInvoice() {
        return canInvoice;
    }

    public void setCanInvoice(BigDecimal canInvoice) {
        this.canInvoice = canInvoice;
    }

    @Override
    public String toString() {
        return "NewSalesContractResponse{" +
                "orderNumber='" + orderNumber + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", contractorId=" + contractorId +
                ", contractor='" + contractor + '\'' +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", userid=" + userid +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", idCard='" + idCard + '\'' +
                ", tel='" + tel + '\'' +
                ", customerFrom='" + customerFrom + '\'' +
                ", customerSource='" + customerSource + '\'' +
                ", address='" + address + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", salesQuote=" + salesQuote +
                ", depositAmount=" + depositAmount +
                ", firstPayAmount=" + firstPayAmount +
                ", tailMoney=" + tailMoney +
                ", businessItems='" + businessItems + '\'' +
                ", businessAmount=" + businessAmount +
                ", serviceFee='" + serviceFee + '\'' +
                ", licensePlateFee='" + licensePlateFee + '\'' +
                ", addDecorationFee='" + addDecorationFee + '\'' +
                ", purchaseTaxFee='" + purchaseTaxFee + '\'' +
                ", storageStatus='" + storageStatus + '\'' +
                ", storageTotal=" + storageTotal +
                ", storageAlready=" + storageAlready +
                ", unstorage=" + unstorage +
                ", settlementStatus='" + settlementStatus + '\'' +
                ", invoiceType='" + invoiceType + '\'' +
                ", receivablesTotal=" + receivablesTotal +
                ", receivablesAlready=" + receivablesAlready +
                ", unreceivable=" + unreceivable +
                ", ticketType='" + ticketType + '\'' +
                ", ticketStatus='" + ticketStatus + '\'' +
                ", ticketTotal=" + ticketTotal +
                ", ticketAlready=" + ticketAlready +
                ", unticket=" + unticket +
                ", isDeleted='" + isDeleted + '\'' +
                ", extraJson='" + extraJson + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", creatorName='" + creatorName + '\'' +
                ", modifyTime=" + modifyTime +
                ", modifierName='" + modifierName + '\'' +
                ", invoiceStatus='" + invoiceStatus + '\'' +
                ", invoiceTotal=" + invoiceTotal +
                ", invoiceAlready=" + invoiceAlready +
                ", uninvoice=" + uninvoice +
                ", canInvoice=" + canInvoice +
                '}';
    }
}
