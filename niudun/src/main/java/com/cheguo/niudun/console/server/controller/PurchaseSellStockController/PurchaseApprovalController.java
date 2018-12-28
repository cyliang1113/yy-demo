package com.cheguo.niudun.console.server.controller.PurchaseSellStockController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cheguo.carsaas.facade.PurchaseApprovalFacade;
import com.cheguo.carsaas.facade.response.ContractResponse;
import com.cheguo.commons.rpc.lang.Result;
import com.cheguo.commons.util.StreamUtils;
import com.cheguo.niudun.console.server.enums.ContractTypeEnum;
import com.cheguo.niudun.console.server.enums.PayStatusEnum;
import com.cheguo.niudun.console.server.enums.PurchaseApprovalStatusEnum;
import com.cheguo.niudun.console.server.enums.StorageStatusEnum;
import com.cheguo.niudun.console.server.response.NewContractResponse;
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
 * 进销存二期 采购审批和付款审批
 * Created by wukuijun on 2018/9/13
 */
@RestController
@RequestMapping("/completecar/purchasecontract")
public class PurchaseApprovalController {

    Logger logger = Logger.getLogger(PurchaseApprovalController.class);

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
    public Result getPurchaseApprovalList(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        HashMap<String, Object> params = new HashMap<>();
//        Result<PageInfo<ContractResponse>> ret = new Result<>();
        Result<PageInfo<NewContractResponse>> ret = new Result<>();
        try {
            String data = obj.toJSONString();
            JSONObject json = JSON.parseObject(data);

            Integer currentPage = Integer.parseInt(json.getString("currentPage"));
            Integer pageSize = Integer.parseInt(json.getString("pageSize"));
            String contractNumber = json.getString("contractNumber");
            String contractType = json.getString("contractType");
            String companyName = json.getString("companyName");
            String startDate = json.getString("startDate");
            String endDate = json.getString("endDate");
            String approvalStatus = json.getString("approvalStatus");
            String payStatus = json.getString("payStatus");
            String storageStatus = json.getString("storageStatus");
            if (org.apache.commons.lang.StringUtils.isNotBlank(contractType)) {
                params.put("contractType", contractType.trim());
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
            if (org.apache.commons.lang.StringUtils.isNotBlank(payStatus)) {
                params.put("payStatus", payStatus.trim());
            }
            if (org.apache.commons.lang.StringUtils.isNotBlank(storageStatus)) {
                params.put("storageStatus", storageStatus.trim());
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

            Result<PageInfo<ContractResponse>> result = purchaseApprovalFacade.getPurchaseApprovalList(params);
            Integer purchaseContractCount = purchaseApprovalFacade.getPurchaseContractCount(params);
            if (result.isSuccess()) {
                PageInfo<ContractResponse> page = result.getData();
                List<ContractResponse> list = page.getList();
                List<NewContractResponse> newList = new ArrayList<>();
                if (list != null && list.size() > 0) {
                    for (ContractResponse contractResponse : list) {
                        NewContractResponse newContractResponse = new NewContractResponse();
                        BeanUtils.copyProperties(contractResponse,newContractResponse);
                        newList.add(newContractResponse);
                    }
                }

                PageInfo<NewContractResponse> page1 = new PageInfo<>();
                page.setTotal(purchaseContractCount);
                page1.setTotal(page.getTotal());
                page1.setList(newList);
                ret.setData(page1);
                ret.setSuccess(true);
                return ret;
            } else {
                ret.setSuccess(false);
                ret.setErrorCode("获取采购合同列表失败");
                return ret;
            }
        } catch (Exception e) {
            logger.error("查询采购合列表失败" + ExceptionUtils.getFullStackTrace(e));
            ret.setSuccess(false);
            ret.setErrorCode("获取采购合列表失败");
            return ret;
        }
    }

    /**
     * 采购审批合同明细
     *
     * @param httpRequest
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail")
    public Result getPurchaseApprovalList(HttpServletRequest httpRequest, HttpServletResponse response,
                                          @RequestBody JSONObject obj) {
        Result res = new Result();
        String str = obj.toJSONString();
        JSONObject json = JSON.parseObject(str);
        Result<ContractResponse> result = purchaseApprovalFacade.getPurchaseApprovalDetail(json.getString("contractNumber"));
        if (result.isSuccess()) {
            ContractResponse data = result.getData();
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

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public Result purchaseContractExport(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        ServletOutputStream out = null;
        Result result = new Result();
        try {
            String excelName = "采购审批合同列表";
            // 转码防止乱码
            response.addHeader("Content-Disposition", ExcelUtil.fileDisposition(request.getHeader("user-agent"), excelName) + ".xls");
            String[] headers = new String[]{"采购合同审批号", "合同类型", "直营店", "申请日期", "合同方", "车辆总数", "车辆总价", "代收代付款", "合同总价"
                    , "审批状态", "结算状态", "入库状态"};
            String[] columnNames = new String[]
                    {"contractNumber", "contractType", "companyName", "applyDate",
                            "contractor", "totalCount", "totalAmount",
                            "waitFee", "fullAmonut", "approvalStatus", "payStatus", "storageStatus"};
            out = response.getOutputStream();
            Map<String, Object> params = FormSupport.generatePagingQueryMap(request, null);
            params.put("orderField", "create_time");
            params.put("orderType", "desc");
            params.put("currentPage", Integer.parseInt(request.getParameter("currentPage")));
            params.put("pageSize", Integer.parseInt(request.getParameter("pageSize")));
            Result<PageInfo<ContractResponse>> res = purchaseApprovalFacade.getPurchaseApprovalList(params);
            List<Map<String, Object>> exportList = new ArrayList<Map<String, Object>>();
            if (res.isSuccess()) {
                //组装成excel需要的数据格式
                List<ContractResponse> list = res.getData().getList();
                Map<String, Object> exportMap = null;
                for (ContractResponse contractResponse : list) {
                    exportMap = new HashMap<String, Object>();
                    exportMap.put("contractNumber", contractResponse.getContractNumber());
                    exportMap.put("contractType", ContractTypeEnum.getByCode(contractResponse.getContractType()).getValue());
                    exportMap.put("companyName", contractResponse.getCompanyName());
                    exportMap.put("applyDate", DateUtils.formatFullDate(contractResponse.getCreateTime()));
                    exportMap.put("contractor", contractResponse.getContractor());
                    exportMap.put("totalCount", contractResponse.getTotalCount());
                    exportMap.put("totalAmount", contractResponse.getTotalAmount());
                    exportMap.put("waitFee", contractResponse.getWaitFee());
                    exportMap.put("fullAmonut", contractResponse.getFullAmount());
                    exportMap.put("approvalStatus", PurchaseApprovalStatusEnum.getByCode(contractResponse.getApprovalStatus()).getValue());
                    exportMap.put("payStatus", PayStatusEnum.getByCode(contractResponse.getPayStatus()).getValue());
                    exportMap.put("storageStatus", StorageStatusEnum.getByCode(contractResponse.getStorageStatus()).getValue());
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
}
