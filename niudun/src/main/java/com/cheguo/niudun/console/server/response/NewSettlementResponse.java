/*
* Copyright (c) 2018. 浙江车国网络科技有限公司.  All rights reserved.
*/
package com.cheguo.niudun.console.server.response;

import com.alibaba.fastjson.annotation.JSONField;
import com.cheguo.carsaas.facade.request.SalesContractDetailRequest;
import com.cheguo.carsaas.facade.response.SettlementDetailResponse;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 收款结算单
 * </p>
 *
 * @author liuyang
 * @since 2018-09-29
 */
@Data
public class NewSettlementResponse implements Serializable {

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
     * yes显示结算审批 no不显示
     */
    private String canSettlement;

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
     * 销售总价格(含税车款)
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
     * 已收定金(元)
     */
    private BigDecimal depositAmountAlready;
    /**
     * 已收保证金(元)
     */
    private BigDecimal firstPayAmountAlready;
    /**
     * 已收尾款(元)
     */
    private BigDecimal tailMoneyAlready;
    /**
     * 未收车款(元)
     */
    private BigDecimal unSalesQuote;
    /**
     * 已收车款(元)
     */
    private BigDecimal salesQuoteAlready;
    /**
     * 收款审批中(元)
     */
    private BigDecimal onSalesQuote;
    /**
     * 代收待付款
     */
    private String businessItems;

    /**
     * 代收待付款汇总
     */
    private BigDecimal businessAmount;
    /**
     * 服务费(元)
     */
    private BigDecimal serviceFee;
    /**
     * 已收服务费(元)
     */
    private BigDecimal serviceFeeAlready;
    /**
     * 上牌服务费(元)
     */
    private BigDecimal licensePlateFee;
    /**
     * 已收上牌服务费(元)
     */
    private BigDecimal licensePlateFeeAlready;
    /**
     * 精品加装费(元)
     */
    private BigDecimal addDecorationFee;
    /**
     * 已收精品加装费(元)
     */
    private BigDecimal addDecorationFeeAlready;
    /**
     * 购置税(元)
     */
    private BigDecimal purchaseTaxFee;
    /**
     * 已收购置税(元)
     */
    private BigDecimal purchaseTaxFeeAlready;
    /**
     * 收款审批中(代收代付款)(元)
     */
    private BigDecimal onBusinessAmount;
    /**
     * 已收代收代付款(元)
     */
    private BigDecimal businessAmountAlready;
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
     * 退款金额
     */
    private BigDecimal backAmount;

    /**
     * 银行户名
     */
    private String backName;

    /**
     * 银行账户
     */
    private String backAccount;

    /**
     * 开户行
     */
    private String bankName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 备注
     */
    private String paymentName;

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

    private List<SettlementDetailResponse> detailResponses;

    /**
     * 车辆总数
     */
    private Integer storageTotal;

    private List<SalesContractDetailRequest> carDetailResponses;
}