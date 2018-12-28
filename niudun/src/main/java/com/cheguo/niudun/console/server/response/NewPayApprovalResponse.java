package com.cheguo.niudun.console.server.response;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by wukuijun on 2018/9/29
 */
public class NewPayApprovalResponse implements Serializable{
    private static final long serialVersionUID = 3659259873000929432L;
    private Integer id;

    private Integer companyId;

    private String companyName;

    private String contractor;
    /**
     * 采购审批单号
     */
    private String contractNumber;

    /**
     * 支付审批号 ZF+时间戳+2位序列
     */
    private String payNumber;

    /**
     * 申请付款金额
     */
    private BigDecimal payAmount;

    /**
     * 付款类型：first_pay 定金  final_pay 尾款 full_amount 全款 bond 保证金 sample_bond 样车保证金 wait_fee 代收代付款
     */
    private String payType;

    /**
     * 银行户名
     */
    private String accountName;

    /**
     * 银行账号
     */
    private String cardNumber;

    /**
     * 开户行
     */
    private String bankName;

    /**
     * 账户类型：public_account  对公账户  private_account 对私账户
     */
    private String accountType;

    /**
     * 营业执照图片
     */
    private String businessLicenseImg;

    /**
     * 情况说明图片
     */
    private String descImg;

    /**
     * 采购交接单
     */
    private String handoverImg;

    /**
     * processing 审批中 success 成功  fail 失败
     */
    private String approvalStatus;

    /**
     *
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date modifyTime;

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBusinessLicenseImg() {
        return businessLicenseImg;
    }

    public void setBusinessLicenseImg(String businessLicenseImg) {
        this.businessLicenseImg = businessLicenseImg;
    }

    public String getDescImg() {
        return descImg;
    }

    public void setDescImg(String descImg) {
        this.descImg = descImg;
    }

    public String getHandoverImg() {
        return handoverImg;
    }

    public void setHandoverImg(String handoverImg) {
        this.handoverImg = handoverImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
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

    @Override
    public String toString() {
        return "PayApprovalModel{" +
                "id=" + id +
                ", companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", contractNumber='" + contractNumber + '\'' +
                ", payNumber='" + payNumber + '\'' +
                ", payAmount=" + payAmount +
                ", payType='" + payType + '\'' +
                ", accountName='" + accountName + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", businessLicenseImg='" + businessLicenseImg + '\'' +
                ", descImg='" + descImg + '\'' +
                ", handoverImg='" + handoverImg + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                '}';
    }
}
