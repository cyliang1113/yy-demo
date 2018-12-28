package com.cheguo.niudun.console.server.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 表名 sales_contract
 */
public class SalesContractModel implements Serializable {
    /**
     *
     */
    private Integer id;

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

    private List<SalesContractDetailModel> salesContractDetailDOList;

    private List<AccessoryModel> accessoryDOList;

    public List<SalesContractDetailModel> getSalesContractDetailDOList() {
        return salesContractDetailDOList;
    }

    public void setSalesContractDetailDOList(List<SalesContractDetailModel> salesContractDetailDOList) {
        this.salesContractDetailDOList = salesContractDetailDOList;
    }

    public List<AccessoryModel> getAccessoryDOList() {
        return accessoryDOList;
    }

    public void setAccessoryDOList(List<AccessoryModel> accessoryDOList) {
        this.accessoryDOList = accessoryDOList;
    }

    /**
     * sales_contract
     */
    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 销售单号
     *
     * @return order_number 销售单号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * 销售单号
     *
     * @param orderNumber 销售单号
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * 业务类型
     *
     * @return type 业务类型
     */
    public String getType() {
        return type;
    }

    /**
     * 业务类型
     *
     * @param type 业务类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 订单状态
     *
     * @return status 订单状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 订单状态
     *
     * @param status 订单状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 合同方id
     *
     * @return contractor_id 合同方id
     */
    public Integer getContractorId() {
        return contractorId;
    }

    /**
     * 合同方id
     *
     * @param contractorId 合同方id
     */
    public void setContractorId(Integer contractorId) {
        this.contractorId = contractorId;
    }

    /**
     * 合同方
     *
     * @return contractor 合同方
     */
    public String getContractor() {
        return contractor;
    }

    /**
     * 合同方
     *
     * @param contractor 合同方
     */
    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    /**
     * 车商id
     *
     * @return company_id 车商id
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 车商id
     *
     * @param companyId 车商id
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 车商名称
     *
     * @return company_name 车商名称
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 车商名称
     *
     * @param companyName 车商名称
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 用户id
     *
     * @return userid 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 用户id
     *
     * @param userid 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 客户ID
     *
     * @return customer_id 客户ID
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 客户ID
     *
     * @param customerId 客户ID
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 客户名称
     *
     * @return customer_name 客户名称
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 客户名称
     *
     * @param customerName 客户名称
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 身份证
     *
     * @return id_card 身份证
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 身份证
     *
     * @param idCard 身份证
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 联系电话
     *
     * @return tel 联系电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 联系电话
     *
     * @param tel 联系电话
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * 客户来源类型:online-线上线索,offline-线下新增
     *
     * @return customer_from 客户来源类型:online-线上线索,offline-线下新增
     */
    public String getCustomerFrom() {
        return customerFrom;
    }

    /**
     * 客户来源类型:online-线上线索,offline-线下新增
     *
     * @param customerFrom 客户来源类型:online-线上线索,offline-线下新增
     */
    public void setCustomerFrom(String customerFrom) {
        this.customerFrom = customerFrom;
    }

    /**
     * 客户来源
     *
     * @return customer_source 客户来源
     */
    public String getCustomerSource() {
        return customerSource;
    }

    /**
     * 客户来源
     *
     * @param customerSource 客户来源
     */
    public void setCustomerSource(String customerSource) {
        this.customerSource = customerSource;
    }

    /**
     * 地址
     *
     * @return address 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 详细地址
     *
     * @return detail_address 详细地址
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * 详细地址
     *
     * @param detailAddress 详细地址
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    /**
     * 销售总价格(含税)
     *
     * @return sales_quote 销售总价格(含税)
     */
    public BigDecimal getSalesQuote() {
        return salesQuote;
    }

    /**
     * 销售总价格(含税)
     *
     * @param salesQuote 销售总价格(含税)
     */
    public void setSalesQuote(BigDecimal salesQuote) {
        this.salesQuote = salesQuote;
    }

    /**
     * 定金金额(元)
     *
     * @return deposit_amount 定金金额(元)
     */
    public BigDecimal getDepositAmount() {
        return depositAmount;
    }

    /**
     * 定金金额(元)
     *
     * @param depositAmount 定金金额(元)
     */
    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    /**
     * 保证金金额(元)
     *
     * @return first_pay_amount 保证金金额(元)
     */
    public BigDecimal getFirstPayAmount() {
        return firstPayAmount;
    }

    /**
     * 保证金金额(元)
     *
     * @param firstPayAmount 保证金金额(元)
     */
    public void setFirstPayAmount(BigDecimal firstPayAmount) {
        this.firstPayAmount = firstPayAmount;
    }

    /**
     * 尾款金额(元)
     *
     * @return tail_money 尾款金额(元)
     */
    public BigDecimal getTailMoney() {
        return tailMoney;
    }

    /**
     * 尾款金额(元)
     *
     * @param tailMoney 尾款金额(元)
     */
    public void setTailMoney(BigDecimal tailMoney) {
        this.tailMoney = tailMoney;
    }

    /**
     * 代收待付款
     *
     * @return business_items 代收待付款
     */
    public String getBusinessItems() {
        return businessItems;
    }

    /**
     * 代收待付款
     *
     * @param businessItems 代收待付款
     */
    public void setBusinessItems(String businessItems) {
        this.businessItems = businessItems;
    }

    /**
     * 代收待付款汇总
     *
     * @return business_amount 代收待付款汇总
     */
    public BigDecimal getBusinessAmount() {
        return businessAmount;
    }

    /**
     * 代收待付款汇总
     *
     * @param businessAmount 代收待付款汇总
     */
    public void setBusinessAmount(BigDecimal businessAmount) {
        this.businessAmount = businessAmount;
    }

    /**
     * 出库状态
     *
     * @return storage_status 出库状态
     */
    public String getStorageStatus() {
        return storageStatus;
    }

    /**
     * 出库状态
     *
     * @param storageStatus 出库状态
     */
    public void setStorageStatus(String storageStatus) {
        this.storageStatus = storageStatus;
    }

    /**
     * 出库数量
     *
     * @return storage_total 出库数量
     */
    public Integer getStorageTotal() {
        return storageTotal;
    }

    /**
     * 出库数量
     *
     * @param storageTotal 出库数量
     */
    public void setStorageTotal(Integer storageTotal) {
        this.storageTotal = storageTotal;
    }

    /**
     * 已出库数量
     *
     * @return storage_already 已出库数量
     */
    public Integer getStorageAlready() {
        return storageAlready;
    }

    /**
     * 已出库数量
     *
     * @param storageAlready 已出库数量
     */
    public void setStorageAlready(Integer storageAlready) {
        this.storageAlready = storageAlready;
    }

    /**
     * 待出库数量
     *
     * @return unstorage 待出库数量
     */
    public Integer getUnstorage() {
        return unstorage;
    }

    /**
     * 待出库数量
     *
     * @param unstorage 待出库数量
     */
    public void setUnstorage(Integer unstorage) {
        this.unstorage = unstorage;
    }

    /**
     * 结算状态
     *
     * @return settlement_status 结算状态
     */
    public String getSettlementStatus() {
        return settlementStatus;
    }

    /**
     * 结算状态
     *
     * @param settlementStatus 结算状态
     */
    public void setSettlementStatus(String settlementStatus) {
        this.settlementStatus = settlementStatus;
    }

    /**
     * 应收总额
     *
     * @return receivables_total 应收总额
     */
    public BigDecimal getReceivablesTotal() {
        return receivablesTotal;
    }

    /**
     * 应收总额
     *
     * @param receivablesTotal 应收总额
     */
    public void setReceivablesTotal(BigDecimal receivablesTotal) {
        this.receivablesTotal = receivablesTotal;
    }

    /**
     * 已收账款
     *
     * @return receivables_already 已收账款
     */
    public BigDecimal getReceivablesAlready() {
        return receivablesAlready;
    }

    /**
     * 已收账款
     *
     * @param receivablesAlready 已收账款
     */
    public void setReceivablesAlready(BigDecimal receivablesAlready) {
        this.receivablesAlready = receivablesAlready;
    }

    /**
     * 待结算账款
     *
     * @return unreceivable 待结算账款
     */
    public BigDecimal getUnreceivable() {
        return unreceivable;
    }

    /**
     * 待结算账款
     *
     * @param unreceivable 待结算账款
     */
    public void setUnreceivable(BigDecimal unreceivable) {
        this.unreceivable = unreceivable;
    }

    /**
     * 开票方
     *
     * @return ticket_type 开票方
     */
    public String getTicketType() {
        return ticketType;
    }

    /**
     * 开票方
     *
     * @param ticketType 开票方
     */
    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    /**
     * 开票状态
     *
     * @return ticket_status 开票状态
     */
    public String getTicketStatus() {
        return ticketStatus;
    }

    /**
     * 开票状态
     *
     * @param ticketStatus 开票状态
     */
    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    /**
     * 开票总额
     *
     * @return ticket_total 开票总额
     */
    public BigDecimal getTicketTotal() {
        return ticketTotal;
    }

    /**
     * 开票总额
     *
     * @param ticketTotal 开票总额
     */
    public void setTicketTotal(BigDecimal ticketTotal) {
        this.ticketTotal = ticketTotal;
    }

    /**
     * 已开票额
     *
     * @return ticket_already 已开票额
     */
    public BigDecimal getTicketAlready() {
        return ticketAlready;
    }

    /**
     * 已开票额
     *
     * @param ticketAlready 已开票额
     */
    public void setTicketAlready(BigDecimal ticketAlready) {
        this.ticketAlready = ticketAlready;
    }

    /**
     * 待开票额
     *
     * @return unticket 待开票额
     */
    public BigDecimal getUnticket() {
        return unticket;
    }

    /**
     * 待开票额
     *
     * @param unticket 待开票额
     */
    public void setUnticket(BigDecimal unticket) {
        this.unticket = unticket;
    }

    /**
     * 删除标识 1=删除 0=有效
     *
     * @return is_deleted 删除标识 1=删除 0=有效
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * 删除标识 1=删除 0=有效
     *
     * @param isDeleted 删除标识 1=删除 0=有效
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 附加字段
     *
     * @return extra_json 附加字段
     */
    public String getExtraJson() {
        return extraJson;
    }

    /**
     * 附加字段
     *
     * @param extraJson 附加字段
     */
    public void setExtraJson(String extraJson) {
        this.extraJson = extraJson;
    }

    /**
     * 备注
     *
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 新增时间
     *
     * @return create_time 新增时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 新增时间
     *
     * @param createTime 新增时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 创建人姓名
     *
     * @return creator_name 创建人姓名
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * 创建人姓名
     *
     * @param creatorName 创建人姓名
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    /**
     * 修改时间
     *
     * @return modify_time 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     * 修改时间
     *
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 修改人姓名
     *
     * @return modifier_name 修改人姓名
     */
    public String getModifierName() {
        return modifierName;
    }

    /**
     * 修改人姓名
     *
     * @param modifierName 修改人姓名
     */
    public void setModifierName(String modifierName) {
        this.modifierName = modifierName;
    }

    @Override
    public String toString() {
        return "SalesContractModel{" +
                "id=" + id +
                ", orderNumber='" + orderNumber + '\'' +
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
                ", storageStatus='" + storageStatus + '\'' +
                ", storageTotal=" + storageTotal +
                ", storageAlready=" + storageAlready +
                ", unstorage=" + unstorage +
                ", settlementStatus='" + settlementStatus + '\'' +
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
                ", salesContractDetailDOList=" + salesContractDetailDOList +
                ", accessoryDOList=" + accessoryDOList +
                '}';
    }
}