package com.cheguo.niudun.console.server.controller.PurchaseSellStockController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cheguo.basic.model.DataPageBo;
import com.cheguo.carsaas.facade.SupplierFacade;
import com.cheguo.carsaas.facade.request.SupplierQueryRequest;
import com.cheguo.carsaas.facade.response.SupplierResponse;
import com.cheguo.commons.rpc.lang.Result;
import com.cheguo.toolkit.utils.DateUtils;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 供应商管理
 * Created by fdx on 18/5/23.
 */
@RestController
@RequestMapping("/completecar/supplier")
public class SupplierController {
    Logger logger = Logger.getLogger(SupplierController.class);
    @Autowired
    private SupplierFacade supplierFacade;

    /**
     * 供应商管理
     */
    @RequestMapping(value = "/page")
    public String purchasePage() {
        return "invoicing/supplier";
    }


    @ResponseBody
    @RequestMapping(value = "/supplierList", method = RequestMethod.POST)
    public Result supplierList(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        Result<DataPageBo<SupplierResponse>> result = new Result<>();
        try {
            String data = obj.toJSONString();
            JSONObject json = JSON.parseObject(data);

            Integer currentPage = Integer.parseInt(json.getString("currentPage"));
            Integer pageSize = Integer.parseInt(json.getString("pageSize"));
            String supplierName = json.getString("supplierName");
            String supplierFrom = json.getString("supplierFrom");
            String companyName = json.getString("companyName");
            String startTime = json.getString("startTime");
            String endTime = json.getString("endTime");

            SupplierQueryRequest supplierQueryRequest = new SupplierQueryRequest();
            if (StringUtils.isNotBlank(supplierName)) {
                supplierQueryRequest.setSupplierNameLike(supplierName.trim());
            }
            if (StringUtils.isNotBlank(supplierFrom)) {
                supplierQueryRequest.setSupplierFrom(supplierFrom.trim());
            }
            if (StringUtils.isNotBlank(companyName)) {
                supplierQueryRequest.setCompanyNameLike(companyName.trim());
            }

            if (StringUtils.isNotBlank(startTime)) {
                Date date = DateUtils.parseDate(startTime, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getStartOfDay(date);
                    supplierQueryRequest.setStartTime(date);
                }
            }
            if (StringUtils.isNotBlank(endTime)) {
                Date date = DateUtils.parseDate(endTime, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getEndOfDay(date);
                    supplierQueryRequest.setEndTime(date);
                }
            }
            supplierQueryRequest.setStart(pageSize);
            supplierQueryRequest.setEnd(currentPage);
            final Result<PageInfo<SupplierResponse>> res = supplierFacade.getSupplierListPage(supplierQueryRequest);
            if (res.isSuccess()) {
                res.setSuccess(true);
                return res;
            } else {
                result.setSuccess(false);
                result.setErrorCode("获取供应商列表失败");
                return result;
            }
        } catch (Exception e) {
            logger.error("查询供应商列表失败" + ExceptionUtils.getFullStackTrace(e));
            result.setSuccess(false);
            result.setErrorCode("获取供应商列表失败");
            return result;
        }
    }
}
