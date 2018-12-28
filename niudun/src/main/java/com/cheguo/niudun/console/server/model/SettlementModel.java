package com.cheguo.niudun.console.server.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 表名 settlement
 */
public class SettlementModel implements Serializable {
    /**
     *
     */
    private Integer id;

    /**
     * 应收账款单号
     */
    private String orderNumber;

    /**
     * 销售单号
     */
    private String salesNumber;

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
     * 收款金额
     */
    private BigDecimal receivableAmount;

    /**
     * 收款账户
     */
    private String receivableAccount;

    /**
     * 实际收款人
     */
    private String receivableName;

    /**
     * 实际汇款人
     */
    private String paymentName;

    /**
     * 汇款时间
     */
    private Date paymentDate;

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
     * settlement
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
     * 应收账款单号
     * @return order_number 应收账款单号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * 应收账款单号
     * @param orderNumber 应收账款单号
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * 销售单号
     * @return sales_number 销售单号
     */
    public String getSalesNumber() {
        return salesNumber;
    }

    /**
     * 销售单号
     * @param salesNumber 销售单号
     */
    public void setSalesNumber(String salesNumber) {
        this.salesNumber = salesNumber;
    }

    /**
     * 业务类型
     * @return type 业务类型
     */
    public String getType() {
        return type;
    }

    /**
     * 业务类型
     * @param type 业务类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 订单状态
     * @return status 订单状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 订单状态
     * @param status 订单状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 合同方id
     * @return contractor_id 合同方id
     */
    public Integer getContractorId() {
        return contractorId;
    }

    /**
     * 合同方id
     * @param contractorId 合同方id
     */
    public void setContractorId(Integer contractorId) {
        this.contractorId = contractorId;
    }

    /**
     * 合同方
     * @return contractor 合同方
     */
    public String getContractor() {
        return contractor;
    }

    /**
     * 合同方
     * @param contractor 合同方
     */
    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    /**
     * 车商id
     * @return company_id 车商id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 车商id
     * @param companyId 车商id
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 车商名称
     * @return company_name 车商名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 车商名称
     * @param companyName 车商名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 收款金额
     * @return receivable_amount 收款金额
     */
    public BigDecimal getReceivableAmount() {
        return receivableAmount;
    }

    /**
     * 收款金额
     * @param receivableAmount 收款金额
     */
    public void setReceivableAmount(BigDecimal receivableAmount) {
        this.receivableAmount = receivableAmount;
    }

    /**
     * 实际收款人
     * @return receivable_name 实际收款人
     */
    public String getReceivableName() {
        return receivableName;
    }

    /**
     * 实际收款人
     * @param receivableName 实际收款人
     */
    public void setReceivableName(String receivableName) {
        this.receivableName = receivableName;
    }

    /**
     * 实际汇款人
     * @return payment_name 实际汇款人
     */
    public String getPaymentName() {
        return paymentName;
    }

    /**
     * 实际汇款人
     * @param paymentName 实际汇款人
     */
    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    /**
     * 汇款时间
     * @return payment_date 汇款时间
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * 汇款时间
     * @param paymentDate 汇款时间
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
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

    public String getReceivableAccount() {
        return receivableAccount;
    }

    public void setReceivableAccount(String receivableAccount) {
        this.receivableAccount = receivableAccount;
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
        return "SettlementModel{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
                ", salesNumber='" + salesNumber + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", contractorId=" + contractorId +
                ", contractor='" + contractor + '\'' +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", receivableAmount=" + receivableAmount +
                ", receivableAccount='" + receivableAccount + '\'' +
                ", receivableName='" + receivableName + '\'' +
                ", paymentName='" + paymentName + '\'' +
                ", paymentDate=" + paymentDate +
                ", isDeleted='" + isDeleted + '\'' +
                ", extraJson='" + extraJson + '\'' +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                ", creatorName='" + creatorName + '\'' +
                ", modifyTime=" + modifyTime +
                ", modifierName='" + modifierName + '\'' +
                '}';
    }
}