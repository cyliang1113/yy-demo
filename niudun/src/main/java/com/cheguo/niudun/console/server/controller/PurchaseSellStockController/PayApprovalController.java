package com.cheguo.niudun.console.server.controller.PurchaseSellStockController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cheguo.carsaas.facade.PurchaseApprovalFacade;
import com.cheguo.carsaas.facade.response.PayApprovalResponse;
import com.cheguo.commons.rpc.lang.Result;
import com.cheguo.commons.util.StreamUtils;
import com.cheguo.niudun.console.server.enums.PayTypeEnum;
import com.cheguo.niudun.console.server.enums.PurchaseApprovalStatusEnum;
import com.cheguo.niudun.console.server.response.NewPayApprovalResponse;
import com.cheguo.toolkit.utils.DateUtils;
import com.cheguo.toolkit.utils.ExcelUtil;
import com.cheguo.toolkit.utils.FormSupport;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 进销存二期 付款审批
 * Created by wukuijun on 2018/9/13
 */
@RestController
@RequestMapping("/completecar/payapproval")
public class PayApprovalController {
    Logger logger = Logger.getLogger(PayApprovalController.class);

    @Resource
    private PurchaseApprovalFacade purchaseApprovalFacade;

    /**
     * 自采页
     */
    @RequestMapping(value = "/page")
    public String selfPurchasePage() {
        return "success";
    }

    /**
     * 采购审核列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result getPayApprovalList(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        HashMap<String, Object> params = new HashMap<>();
//        Result<PageInfo<PayApprovalResponse>> ret = new Result<>();
        Result<PageInfo<NewPayApprovalResponse>> ret = new Result<>();
        try {
            String data = obj.toJSONString();
            JSONObject json = JSON.parseObject(data);

            Integer currentPage = Integer.parseInt(json.getString("currentPage"));
            Integer pageSize = Integer.parseInt(json.getString("pageSize"));
            String payNumber = json.getString("payNumber");
            String payType = json.getString("payType");
            String companyName = json.getString("companyName");
            String startDate = json.getString("startDate");
            String endDate = json.getString("endDate");
            String contractor = json.getString("contractor");
            String approvalStatus = json.getString("approvalStatus");
            String contractNumber = json.getString("contractNumber");
            if (org.apache.commons.lang.StringUtils.isNotBlank(payNumber)) {
                params.put("payNumber", payNumber.trim());
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(companyName)) {
                params.put("companyName", companyName.trim());
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(contractNumber)) {
                params.put("contractNumber", contractNumber.trim());
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(companyName)) {
                params.put("companyName", companyName.trim());
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(approvalStatus)) {
                params.put("approvalStatus", approvalStatus.trim());
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(payType)) {
                params.put("payType", payType.trim());
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(contractor)) {
                params.put("contractor", contractor.trim());
            }

            if (org.apache.commons.lang.StringUtils.isNotBlank(startDate)) {
                Date date = DateUtils.parseDate(startDate, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getStartOfDay(date);
                    params.put("startDate", date);
                }
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(endDate)) {
                Date date = DateUtils.parseDate(endDate, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getEndOfDay(date);
                    params.put("endDate", date);
                }
            }
            params.put("currentPage", currentPage);
            params.put("pageSize", pageSize);

            Result<PageInfo<PayApprovalResponse>> result = purchaseApprovalFacade.getPayApprovalList(params);
            Integer payContractCount = purchaseApprovalFacade.getPayContractCount(params);
            if (result.isSuccess()) {
                PageInfo<PayApprovalResponse> page = result.getData();
                page.setTotal(payContractCount);
                List<PayApprovalResponse> payApprovalResponses = page.getList();
                List<NewPayApprovalResponse> newPayApprovalResponses = new ArrayList<>();
                if (payApprovalResponses != null && payApprovalResponses.size() > 0) {
                    for (PayApprovalResponse payApprovalResponse : payApprovalResponses) {
                        NewPayApprovalResponse newPayApprovalResponse = new NewPayApprovalResponse();
                        BeanUtils.copyProperties(payApprovalResponse,newPayApprovalResponse);
                        newPayApprovalResponses.add(newPayApprovalResponse);
                    }
                }
                PageInfo<NewPayApprovalResponse> page1 = new PageInfo<>();
                page1.setList(newPayApprovalResponses);
                page1.setTotal(page.getTotal());
                ret.setData(page1);
                ret.setSuccess(true);
                return ret;
            } else {
                ret.setSuccess(false);
                ret.setErrorCode("获取支付合同列表失败");
                return ret;
            }
        } catch (Exception e) {
            logger.error("查询支付合列表失败" + ExceptionUtils.getFullStackTrace(e));
            ret.setSuccess(false);
            ret.setErrorCode("获取支付合列表失败");
            return ret;
        }
    }

    /**
     * 导出
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public Result payContractExport(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        ServletOutputStream out = null;
        Result result = new Result();
        try {
            String excelName = "支付审批合同列表";
            // 转码防止乱码
            response.addHeader("Content-Disposition", ExcelUtil.fileDisposition(request.getHeader("user-agent"), excelName) + ".xls");
            String[] headers = new String[]{"支付审批号", "付款类型", "直营店", "付款金额", "合同方", "申请日期", "银行户名", "银行账号", "开户行"
                    , "审批状态", "采购合同审批号"};
            String[] columnNames = new String[]
                    {"payNumber", "payType", "companyName", "payAmount",
                            "contractor", "applyDate", "accountName",
                            "cardNumber", "bankName", "approvalStatus", "contractNumber"};
            out = response.getOutputStream();
            Map<String, Object> params = FormSupport.generatePagingQueryMap(request, null);
            params.put("orderField", "create_time");
            params.put("orderType", "desc");
            params.put("currentPage", Integer.parseInt(request.getParameter("currentPage")));
            params.put("pageSize", Integer.parseInt(request.getParameter("pageSize")));
            Result<PageInfo<PayApprovalResponse>> res = purchaseApprovalFacade.getPayApprovalList(params);
            List<Map<String, Object>> exportList = new ArrayList<Map<String, Object>>();
            if (res.isSuccess()) {
                //组装成excel需要的数据格式
                List<PayApprovalResponse> list = res.getData().getList();
                Map<String, Object> exportMap = null;
                for (PayApprovalResponse payApprovalResponse : list) {
                    exportMap = new HashMap<String, Object>();
                    exportMap.put("payNumber", payApprovalResponse.getPayNumber());
                    exportMap.put("payType", PayTypeEnum.getByCode(payApprovalResponse.getPayType()).getValue());
                    exportMap.put("companyName", payApprovalResponse.getCompanyName());
                    exportMap.put("payAmount", payApprovalResponse.getPayAmount());
                    exportMap.put("contractor", payApprovalResponse.getContractor());
                    exportMap.put("applyDate", DateUtils.formatFullDate(payApprovalResponse.getCreateTime()));
                    exportMap.put("accountName", payApprovalResponse.getAccountName());
                    exportMap.put("cardNumber", payApprovalResponse.getCardNumber());
                    exportMap.put("bankName", payApprovalResponse.getBankName());
                    exportMap.put("approvalStatus", PurchaseApprovalStatusEnum.getByCode(payApprovalResponse.getApprovalStatus()).getValue());
                    exportMap.put("contractNumber", payApprovalResponse.getContractNumber());
                    exportList.add(exportMap);
                }
            }
            ExcelUtil.exportExcel(excelName, headers, columnNames, exportList, out);
            result.setSuccess(true);
            return result;
        } catch (Exception ex) {
            logger.error("error:{}", ex);
            result.setSuccess(false);
            result.setErrorMsg("导出失败.");
            return result;
        } finally {
            StreamUtils.streamFlushAndClose(out, out);
        }
    }

    private static String getResult(PageInfo pageInfo, String api) {
        Map<String, Object> result = new HashMap<>();

        result.put("list", pageInfo.getList());
        result.put("api", api);
        result.put("rel", true);
        result.put("msg", "获取成功");
        result.put("count", pageInfo.getTotal());
        result.put("totalPage", pageInfo.getPages());
        result.put("isFirstPage", pageInfo.isIsFirstPage());
        result.put("isLastPage", pageInfo.isIsLastPage());

        return JSONObject.toJSONString(result);
    }

}
