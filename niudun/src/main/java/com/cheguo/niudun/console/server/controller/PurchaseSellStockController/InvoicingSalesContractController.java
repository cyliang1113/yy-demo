package com.cheguo.niudun.console.server.controller.PurchaseSellStockController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cheguo.carsaas.facade.SalesContractFacade;
import com.cheguo.carsaas.facade.response.SalesContractResponse;
import com.cheguo.commons.rpc.lang.Result;
import com.cheguo.commons.util.StreamUtils;
import com.cheguo.niudun.console.server.response.NewSalesContractResponse;
import com.cheguo.toolkit.utils.BeanUtils;
import com.cheguo.toolkit.utils.DateUtils;
import com.cheguo.toolkit.utils.ExcelUtil;
import com.cheguo.toolkit.utils.FormSupport;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 进销存  销售合同
 * Created by liuyang on 2018/9/6.
 */
@RestController
@RequestMapping("/completecar/salescontract")
public class InvoicingSalesContractController {
    Logger logger = Logger.getLogger(InvoicingSalesContractController.class);

    @Resource
    private SalesContractFacade salesContractFacade;

    /**
     * 销售合同列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result getSalesContractList(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        HashMap<String, Object> params = new HashMap<>();
        Result<PageInfo<NewSalesContractResponse>> ret = new Result<>();
        try {
            String data = obj.toJSONString();
            JSONObject json = JSON.parseObject(data);

            Integer currentPage = Integer.parseInt(json.getString("currentPage"));
            Integer pageSize = Integer.parseInt(json.getString("pageSize"));

            String orderNumber = json.getString("orderNumber");
            String type = json.getString("type");
            String contractor = json.getString("contractor");
            String companyName = json.getString("companyName");
            String status = json.getString("status");
            String settlementStatus = json.getString("settlementStatus");
            String storageStatus = json.getString("storageStatus");
            String applyTimeStart = json.getString("applyTimeStart");
            String applyTimeEnd = json.getString("applyTimeEnd");
            if (StringUtils.isNotBlank(orderNumber)) {
                params.put("orderNumber", orderNumber.trim());
            }
            if (StringUtils.isNotBlank(type)) {
                params.put("type", type.trim());
            }
            if (StringUtils.isNotBlank(contractor)) {
                params.put("contractor", contractor.trim());
            }
            if (StringUtils.isNotBlank(companyName)) {
                params.put("companyName", companyName.trim());
            }
            if (StringUtils.isNotBlank(status)) {
                params.put("status", status.trim());
            }
            if (StringUtils.isNotBlank(settlementStatus)) {
                params.put("settlementStatus", settlementStatus.trim());
            }
            if (StringUtils.isNotBlank(storageStatus)) {
                params.put("storageStatus", storageStatus.trim());
            }

            if (StringUtils.isNotBlank(applyTimeStart)) {
                Date date = DateUtils.parseDate(applyTimeStart, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getStartOfDay(date);
                    params.put("startData", DateUtils.formatFullDate(date));
                }
            }
            if (StringUtils.isNotBlank(applyTimeEnd)) {
                Date date = DateUtils.parseDate(applyTimeEnd, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getEndOfDay(date);
                    params.put("endData", DateUtils.formatFullDate(date));
                }
            }
            params.put("orderField", "create_time");
            params.put("orderType", "desc");
            params.put("pageIndex", currentPage);
            params.put("pageSize", pageSize);

            Result<PageInfo<SalesContractResponse>> result = salesContractFacade.getSalesContractList(params);
            Integer salesContractCount = salesContractFacade.getSalesContractCount(params);
            if (result.isSuccess()) {
                PageInfo<SalesContractResponse> page = result.getData();
                List<SalesContractResponse> list = page.getList();
                PageInfo<NewSalesContractResponse> info = new PageInfo<>();
                List<NewSalesContractResponse> list1 = new ArrayList<>();
                if (!CollectionUtils.isEmpty(list)) {
                    for (SalesContractResponse salesContractResponse : list) {
                        NewSalesContractResponse response = new NewSalesContractResponse();
                        BeanUtils.copyProperty(salesContractResponse, response);
                        list1.add(response);
                    }
                }
                info.setList(list1);
                info.setTotal(salesContractCount);
                ret.setData(info);
                ret.setSuccess(true);
            } else {
                ret.setSuccess(false);
                ret.setErrorCode("获取销售列表失败");
            }
            return ret;
        } catch (Exception e) {
            logger.error("查询销售列表失败" + ExceptionUtils.getFullStackTrace(e));
            ret.setSuccess(false);
            ret.setErrorCode("获取销售列表失败");
            return ret;
        }
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public Result salesContractExport(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        ServletOutputStream out = null;
        Result result = new Result();
        try {
            String excelName = "销售合同列表";
            // 转码防止乱码
            response.addHeader("Content-Disposition", ExcelUtil.fileDisposition(request.getHeader("user-agent"), excelName) + ".xls");
            String[] headers = new String[]{"销售合同审批号", "合同类型", "直营店", "申请日期", "合同方", "车辆总数", "车辆总价", "代收代付款", "合同总价"
                    , "审批状态", "结算状态", "出库状态"};
            String[] columnNames = new String[]
                    {"orderNumber", "type", "companyName", "createTime",
                            "contractor", "storageTotal", "salesQuote",
                            "businessAmount", "receivablesTotal", "status", "settlementStatus", "storageStatus"};
            out = response.getOutputStream();
            Map<String, Object> params = FormSupport.generatePagingQueryMap(request, null);
            params.put("orderField", "create_time");
            params.put("orderType", "desc");
            params.put("currentPage", Integer.parseInt(request.getParameter("currentPage")));
            params.put("pageSize", Integer.parseInt(request.getParameter("pageSize")));
            Result<PageInfo<SalesContractResponse>> res = salesContractFacade.getSalesContractList(params);
            List<Map<String, Object>> exportList = new ArrayList<Map<String, Object>>();
            if (res.isSuccess()) {
                //组装成excel需要的数据格式
                List<SalesContractResponse> list = res.getData().getList();
                Map<String, Object> exportMap = null;
                for (SalesContractResponse salesContractResponse : list) {
                    exportMap = new HashMap<String, Object>();

                    String type = salesContractResponse.getType();
                    if (!StringUtils.isBlank(type)) {
                        if (type.equals("sell")) {
                            exportMap.put("type", "销售");
                        } else if (type.equals("advance")) {
                            exportMap.put("type", "代销垫资");
                        } else {
                            exportMap.put("type", "代销只收服务费");
                        }
                    }

                    String status = salesContractResponse.getStatus();
                    if (!StringUtils.isBlank(status)) {
                        if (status.equals("succ")) {
                            exportMap.put("status", "审批通过");
                        } else if (status.equals("fail")) {
                            exportMap.put("status", "审批未通过");
                        } else {
                            exportMap.put("status", "审批中");
                        }
                    }
                    String settlementStatus = salesContractResponse.getSettlementStatus();
                    if (!StringUtils.isBlank(settlementStatus)) {
                        if (settlementStatus.equals("succ")) {
                            exportMap.put("settlementStatus", "已结算");
                        } else {
                            exportMap.put("settlementStatus", "未结算");
                        }
                    }
                    String storageStatus = salesContractResponse.getStorageStatus();
                    if (!StringUtils.isBlank(storageStatus)) {
                        if (storageStatus.equals("instorage")) {
                            exportMap.put("storageStatus", "入库");
                        } else if (storageStatus.equals("outstorage")) {
                            exportMap.put("storageStatus", "出库");
                        } else {
                            exportMap.put("storageStatus", "待出库");
                        }
                    }
                    exportMap.put("orderNumber", salesContractResponse.getOrderNumber());
                    exportMap.put("companyName", salesContractResponse.getCompanyName());
                    exportMap.put("createTime", DateUtils.formatFullDate(salesContractResponse.getCreateTime()));
                    exportMap.put("contractor", salesContractResponse.getContractor());
                    exportMap.put("storageTotal", salesContractResponse.getStorageTotal());
                    exportMap.put("salesQuote", salesContractResponse.getSalesQuote());
                    exportMap.put("businessAmount", salesContractResponse.getBusinessAmount());
                    exportMap.put("receivablesTotal", salesContractResponse.getReceivablesTotal());
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

    /**
     * 查看合同明细
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail")
    public Result getSalesContractDetail(HttpServletRequest request, @RequestBody JSONObject obj) {
        Result res = new Result();
        String str = obj.toJSONString();
        JSONObject json = JSON.parseObject(str);
        Result<SalesContractResponse> result = salesContractFacade.getSalesContractByorderNumber(json.getString("orderNumber"));
        if (result.isSuccess()) {
            SalesContractResponse data = result.getData();
            if (data == null) {
                res.setSuccess(false);
                res.setErrorCode("未查询到车辆信息");
                return res;
            } else {
                result.setSuccess(true);
                return result;
            }
        } else {
            res.setSuccess(false);
            res.setErrorCode("数据处理异常");
            return res;
        }
    }
}
