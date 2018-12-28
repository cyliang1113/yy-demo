package com.cheguo.niudun.console.server.controller.PurchaseSellStockController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cheguo.carsaas.facade.TicketApprovalFacade;
import com.cheguo.carsaas.facade.response.SettlementResponse;
import com.cheguo.carsaas.facade.response.TicketApprovalModelResponse;
import com.cheguo.carsaas.facade.response.TicketApprovalResponse;
import com.cheguo.commons.rpc.lang.Result;
import com.cheguo.commons.util.StreamUtils;
import com.cheguo.niudun.console.server.enums.PurchaseApprovalStatusEnum;
import com.cheguo.toolkit.utils.DateUtils;
import com.cheguo.toolkit.utils.ExcelUtil;
import com.cheguo.toolkit.utils.FormSupport;
import com.cheguo.toolkit.utils.Util;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 进销存二期 采购审批和付款审批
 * Created by qiaoyamei on 2018/9/13
 */
@RestController
@RequestMapping("/completecar/ticketapproval")
public class TicketApprovalController {

    Logger logger = Logger.getLogger(TicketApprovalController.class);

    @Resource
    private TicketApprovalFacade ticketApprovalFacade;


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
    @RequestMapping(value = "/list")
    public Result getInvoiceApprovalList(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        HashMap<String, Object> params = new HashMap<>();
        Result<PageInfo<TicketApprovalModelResponse>> ret = new Result<>();
        try {
            String data = obj.toJSONString();
            JSONObject json = JSON.parseObject(data);

            Integer currentPage = Integer.parseInt(json.getString("currentPage"));
            Integer pageSize = Integer.parseInt(json.getString("pageSize"));

            String invoiceApprovalNo = json.getString("invoiceApprovalNo");
            String salesApprovalNo = json.getString("salesApprovalNo");
            String invoiceType = json.getString("invoiceType");
            String invoiceStatus = json.getString("invoiceStatus");
            String purchaserName = json.getString("purchaserName");
            String startDate = json.getString("startDate");
            String endDate = json.getString("endDate");


            if (StringUtils.isNotBlank(invoiceApprovalNo)) {
                params.put("invoiceApprovalNo", invoiceApprovalNo.trim());
            }
            if (StringUtils.isNotBlank(salesApprovalNo)) {
                params.put("salesApprovalNo", salesApprovalNo.trim());
            }
            if (StringUtils.isNotBlank(invoiceType)) {
                params.put("invoiceType", invoiceType.trim());
            }
            if (StringUtils.isNotBlank(startDate)) {
                Date date = DateUtils.parseDate(startDate, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getStartOfDay(date);
                    params.put("startDate", date);
                }
            }
            if (StringUtils.isNotBlank(endDate)) {
                Date date = DateUtils.parseDate(endDate, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getEndOfDay(date);
                    params.put("endDate", date);
                }
            }
            if (StringUtils.isNotBlank(invoiceStatus)) {
                params.put("invoiceStatus", invoiceStatus.trim());
            }
            if (StringUtils.isNotBlank(purchaserName)) {
                params.put("purchaserName", purchaserName.trim());
            }
            params.put("currentPage", currentPage);
            params.put("pageSize", pageSize);

            Result<PageInfo<TicketApprovalModelResponse>> result = ticketApprovalFacade.getListByMap(params);
            if (result.isSuccess()) {
                ret.setData(result.getData());
                ret.setSuccess(true);
                return ret;
            } else {
                ret.setSuccess(false);
                ret.setErrorCode("获取开票列表失败");
                return ret;
            }
        } catch (Exception e) {
            logger.error("获取开票列表失败" + ExceptionUtils.getFullStackTrace(e));
            ret.setSuccess(false);
            ret.setErrorCode("获取开票列表失败");
            return ret;
        }
    }
    /**
     * 开票明细
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail")
    public Result getPurchaseApprovalList(HttpServletRequest request, @RequestBody JSONObject obj) {
        Result res = new Result();
        String str = obj.toJSONString();
        JSONObject json = JSON.parseObject(str);

        Result<TicketApprovalResponse> result =ticketApprovalFacade.getTicketApprovalInfo(json.getString("invoiceApprovalNo"));
        if (result.isSuccess()) {
            TicketApprovalResponse data = result.getData();
            if (data == null) {
                    res.setSuccess(false);
                    res.setErrorCode("未查询到明细信息");
                    return res;
                } else {
                    result.setSuccess(true);
                    return result;
                }
            }else {
            res.setSuccess(false);
            res.setErrorCode("数据处理异常");
            return res;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public String invoiceExport(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        ServletOutputStream out = null;
        try {
            String excelName = "开票列表";
            // 转码防止乱码
            response.addHeader("Content-Disposition", ExcelUtil.fileDisposition(request.getHeader("user-agent"), excelName) + ".xls");
            String[] headers = new String[]{"开票审批号", "发票类型", "直营店", "开票金额", "开票车辆数", "审批状态"};
            String[] columnNames = new String[]
                    {"invoiceApprovalNo", "invoiceType", "purchaserName", "invoiceAmount",
                            "carNumber", "invoiceStatus"};
            out = response.getOutputStream();

            Map<String, Object> params = FormSupport.generatePagingQueryMap(request, null);
            params.put("orderField", "create_time");
            params.put("orderType", "desc");
            params.put("start", 0);
            params.put("end", 10000);
            Result<PageInfo<TicketApprovalModelResponse>> result = ticketApprovalFacade.getListByMap(params);
            List<Map<String, Object>> exportList = new ArrayList<Map<String, Object>>();
            if (result.isSuccess()) {
                //组装成excel需要的数据格式
                List<TicketApprovalModelResponse> list = result.getData().getList();
                Map<String, Object> exportMap = null;
                for (TicketApprovalModelResponse contractResponse : list) {
                    exportMap = new HashMap<String, Object>();
                    exportMap.put("invoiceApprovalNo", contractResponse.getInvoiceApprovalNo());
                    exportMap.put("invoiceType", contractResponse.getInvoiceType());
                    exportMap.put("purchaserName", contractResponse.getCompanyName());
                    exportMap.put("invoiceAmount", contractResponse.getCreateTime());
                    exportMap.put("carNumber", contractResponse.getCarNumber());
                    exportMap.put("invoiceStatus", PurchaseApprovalStatusEnum.getByCode(contractResponse.getInvoiceStatus()));
                    exportList.add(exportMap);
                }
            }
            ExcelUtil.exportExcel(excelName, headers, columnNames, exportList, out);
            return Util.getSuccessJson("导出成功").toString();
        } catch (Exception ex) {
            logger.error("error:{}", ex);
            return Util.getFailureJson().toString();
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
