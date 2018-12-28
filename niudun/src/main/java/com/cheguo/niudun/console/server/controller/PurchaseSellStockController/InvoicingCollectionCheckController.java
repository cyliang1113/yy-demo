package com.cheguo.niudun.console.server.controller.PurchaseSellStockController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cheguo.carsaas.facade.SalesContractFacade;
import com.cheguo.carsaas.facade.response.SettlementResponse;
import com.cheguo.commons.rpc.lang.Result;
import com.cheguo.commons.util.StreamUtils;
import com.cheguo.niudun.console.server.response.NewSettlementResponse;
import com.cheguo.toolkit.utils.BeanUtils;
import com.cheguo.toolkit.utils.DateUtils;
import com.cheguo.toolkit.utils.ExcelUtil;
import com.cheguo.toolkit.utils.FormSupport;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
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
 * 进销存  应收款审核
 * Created by liuyang on 2018/9/6.
 */
@RestController
@RequestMapping("/completecar/collectioncheck")
public class InvoicingCollectionCheckController {
    Logger logger = Logger.getLogger(InvoicingSalesContractController.class);

    @Resource
    private SalesContractFacade salesContractFacade;

    /**
     * 自采页
     */
    @RequestMapping(value = "/page")
    public String selfPurchasePage() {
        return "success";
    }

    /**
     * 收款审核列表
     *
     * @return
     */
    @PostMapping(value = "/list")
    public Result getCollectionCheckList(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        HashMap<String, Object> params = new HashMap<>();
        Result<PageInfo<NewSettlementResponse>> ret = new Result<>();
        try {
            String data = obj.toJSONString();
            JSONObject json = JSON.parseObject(data);

            Integer currentPage = Integer.parseInt(json.getString("currentPage"));
            Integer pageSize = Integer.parseInt(json.getString("pageSize"));

            String orderNumber = json.getString("orderNumber");
            String salesNumber = json.getString("salesNumber");
            String type = json.getString("type");
            String companyName = json.getString("companyName");
            String contractor = json.getString("contractor");
            String status = json.getString("status");
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
            if (StringUtils.isNotBlank(salesNumber)) {
                params.put("salesNumber", salesNumber.trim());
            }
            if (StringUtils.isNotBlank(applyTimeStart)) {
                Date date = DateUtils.parseDate(applyTimeStart, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getStartOfDay(date);
                    params.put("applyTimeStart", date);
                }
            }
            if (StringUtils.isNotBlank(applyTimeEnd)) {
                Date date = DateUtils.parseDate(applyTimeEnd, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getEndOfDay(date);
                    params.put("applyTimeEnd", date);
                }
            }
            params.put("orderField", "create_time");
            params.put("orderType", "desc");
            params.put("pageIndex", currentPage);
            params.put("pageSize", pageSize);
            Integer count = salesContractFacade.getSettlementDetailCount(params);
            Result<PageInfo<SettlementResponse>> result = salesContractFacade.getSettlementDetailList(params);
            if (result.isSuccess()) {
                PageInfo<SettlementResponse> page = result.getData();
                PageInfo<NewSettlementResponse> info = new PageInfo<>();
                List<SettlementResponse> list = result.getData().getList();
                List<NewSettlementResponse> list1 = new ArrayList<>();
                if (!CollectionUtils.isEmpty(list)) {
                    for (SettlementResponse settlementResponse : list) {
                        NewSettlementResponse response = new NewSettlementResponse();
                        BeanUtils.copyProperty(settlementResponse, response);
                        list1.add(response);
                    }
                }
                info.setList(list1);
                info.setTotal(count);
                ret.setData(info);
                ret.setSuccess(true);
            } else {
                ret.setSuccess(false);
                ret.setErrorCode("获取收款审核列表失败");
            }
            return ret;
        } catch (Exception e) {
            logger.error("查询收款审核列表失败" + ExceptionUtils.getFullStackTrace(e));
            ret.setSuccess(false);
            ret.setErrorCode("获取收款审核列表失败");
            return ret;
        }
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public Result collectionCheckExport(HttpServletRequest request, HttpServletResponse response,@RequestBody JSONObject obj) {
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        ServletOutputStream out = null;
        Result result = new Result();
        try {
            String excelName = "应收款审核表";
            // 转码防止乱码
            response.addHeader("Content-Disposition", ExcelUtil.fileDisposition(request.getHeader("user-agent"), excelName) + ".xls");
            String[] headers = new String[]{"收款审批号", "收款类型", "直营店", "收款金额", "合同方", "申请日期", "付款方户名", "收款账户", "审批状态", "销售合同审批号"};
            String[] columnNames = new String[]
                    {"orderNumber", "type", "companyName", "receivableAmount",
                            "contractor", "createTime", "receivableName",
                            "receivableAccount", "status", "salesNumber"};
            out = response.getOutputStream();

            Map<String, Object> params = FormSupport.generatePagingQueryMap(request, null);
            params.put("orderField", "create_time");
            params.put("orderType", "desc");
            params.put("currentPage", request.getParameter("currentPage"));
            params.put("pageSize", request.getParameter("pageSize"));

            Result<PageInfo<SettlementResponse>> res = salesContractFacade.getSettlementDetailList(params);
            List<Map<String, Object>> exportList = new ArrayList<Map<String, Object>>();
            if (res.isSuccess()) {
                //组装成excel需要的数据格式
                List<SettlementResponse> list = res.getData().getList();
                Map<String, Object> exportMap = null;
                for (SettlementResponse settlementResponse : list) {
                    exportMap = new HashMap<String, Object>();
                    exportMap.put("orderNumber", settlementResponse.getOrderNumber());
                    exportMap.put("type", settlementResponse.getType());
                    exportMap.put("companyName", settlementResponse.getCompanyName());
                    exportMap.put("createTime", settlementResponse.getCreateTime());
                    exportMap.put("contractor", settlementResponse.getContractor());
                    exportMap.put("receivableAmount", settlementResponse.getReceivableAmount());
                    exportMap.put("receivableName", settlementResponse.getReceivableName());
                    exportMap.put("status", settlementResponse.getStatus());
                    exportMap.put("salesNumber", settlementResponse.getSalesNumber());
                    exportMap.put("receivableAccount", settlementResponse.getReceivableAccount());
                    exportList.add(exportMap);
                }
            }
            ExcelUtil.exportExcel(excelName, headers, columnNames, exportList, out);
            result.setSuccess(true);
            return result;
        } catch (Exception ex) {
            logger.error("error:{}", ex);
            result.setSuccess(false);
            result.setErrorMsg("导出失败.") ;
            return result;
        } finally {
            StreamUtils.streamFlushAndClose(out, out);
        }
    }
}
