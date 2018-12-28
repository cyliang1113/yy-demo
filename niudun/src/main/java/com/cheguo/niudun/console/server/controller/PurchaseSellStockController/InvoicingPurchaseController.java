package com.cheguo.niudun.console.server.controller.PurchaseSellStockController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cheguo.carmodel.model.*;
import com.cheguo.carmodel.service.*;
import com.cheguo.carsaas.facade.InvoicingFacade;
import com.cheguo.carsaas.facade.response.CarInfoResponse;
import com.cheguo.carsaas.facade.response.HeadquartersPurchaseResponse;
import com.cheguo.commons.rpc.lang.Result;
import com.cheguo.commons.util.StreamUtils;
import com.cheguo.niudun.console.server.model.PurchaseModel;
import com.cheguo.toolkit.utils.DateUtils;
import com.cheguo.toolkit.utils.ExcelUtil;
import com.cheguo.toolkit.utils.StringUtil;
import com.cheguo.toolkit.utils.Util;
import com.cheguo.userinfo.model.CompanyModel;
import com.cheguo.userinfo.service.ICompanyService;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 进销存 集采
 */
@RestController
@RequestMapping("/completecar/purchase")
public class InvoicingPurchaseController {
    Logger logger = Logger.getLogger(InvoicingPurchaseController.class);

    @Autowired
    private InvoicingFacade invoicingFacade;

    @Autowired
    private CarmodMakerBrandService carmodMakerBrandService;

    @Autowired
    private CarmodSeriesService carmodSeriesService;

    @Autowired
    private CarmodBrandService carmodBrandService;

    @Autowired
    private IBrandInfoService brandService;

    @Autowired
    private ICarBaseService carBaseService;

    @Autowired
    private ICompanyService companyService;
    /**
     * 总部采集单页
     */
    @RequestMapping(value = "/page")
    public String purchasePage() {
        return "success";
    }


    /**
     * 总部采集单列表
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public Result getPurchaseList(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        Result<PageInfo<PurchaseModel>> ret = new Result<>();
        HashMap<String, Object> params = new HashMap<>();
        try {
            String data = obj.toJSONString();
            JSONObject json = JSON.parseObject(data);

            Integer currentPage = Integer.parseInt(json.getString("currentPage"));
            Integer pageSize = Integer.parseInt(json.getString("pageSize"));

            String vin = json.getString("vin");
            String carType = json.getString("carType");
            String carState = json.getString("carState");
            String companyName = json.getString("companyName");
            String depositAmountTimeStart = json.getString("depositAmountTimeStart");
            String depositAmountTimeEnd = json.getString("depositAmountTimeEnd");
            String tailAmountTimeStart = json.getString("tailAmountTimeStart");
            String tailAmountTimeEnd = json.getString("tailAmountTimeEnd");

            if (StringUtils.isNotBlank(vin)) {
                params.put("vin", vin.trim());
            }
            if (StringUtils.isNotBlank(carType)) {
                params.put("carTypeDesc", carType.trim());
            }
            if (StringUtils.isNotBlank(carState)) {
                params.put("carState", carState.trim());
            }
            if (StringUtils.isNotBlank(companyName)) {
                params.put("companyName", companyName.trim());
            }
            if (StringUtils.isNotBlank(depositAmountTimeStart)) {
                Date date = DateUtils.parseDate(depositAmountTimeStart, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getStartOfDay(date);
                    params.put("depositAmountTimeStart", date);
                }
            }
            if (StringUtils.isNotBlank(depositAmountTimeEnd)) {
                Date date = DateUtils.parseDate(depositAmountTimeEnd, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getEndOfDay(date);
                    params.put("depositAmountTimeEnd", date);
                }
            }
            if (StringUtils.isNotBlank(tailAmountTimeStart)) {
                Date date = DateUtils.parseDate(tailAmountTimeStart, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getStartOfDay(date);
                    params.put("tailAmountTimeStart", date);
                }
            }
            if (StringUtils.isNotBlank(tailAmountTimeEnd)) {
                Date date = DateUtils.parseDate(tailAmountTimeEnd, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getEndOfDay(date);
                    params.put("tailAmountTimeEnd", date);
                }
            }
            params.put("orderField", "create_time");
            params.put("orderType", "desc");
            params.put("currentPage", currentPage);
            params.put("pageSize", pageSize);

            Integer count = invoicingFacade.getPurchaseListCount(params);

            PageInfo<HeadquartersPurchaseResponse> purchaseList = invoicingFacade.getPurchaseListPage(params);
            PageInfo<PurchaseModel> modelPageInfo = new PageInfo<>();
            List<PurchaseModel> list = new ArrayList<PurchaseModel>();
            if (purchaseList.getList() != null && purchaseList.getList().size() > 0) {
                for (HeadquartersPurchaseResponse item : purchaseList.getList()) {
                    PurchaseModel purchaseModel = new PurchaseModel();
                    purchaseModel.setId(item.getId());
                    purchaseModel.setOrderNumber(item.getOrderNumber());
                    purchaseModel.setVin(item.getVin());
                    purchaseModel.setCarTypeDesc(item.getCarTypeDesc());
                    purchaseModel.setGuidingPrice(item.getGuidingPrice());
                    purchaseModel.setColour(item.getColour());
                    purchaseModel.setInteriorColour(item.getInteriorColour());
                    purchaseModel.setTotalCount(item.getTotalCount());
                    purchaseModel.setTotalAmount(item.getTotalAmount());
                    purchaseModel.setServiceFee(item.getServiceFee());
                    purchaseModel.setDepositAmountTime(item.getDepositAmountTime());
                    purchaseModel.setDepositAmount(item.getDepositAmount());
                    purchaseModel.setTailAmountTime(item.getTailAmountTime());
                    purchaseModel.setCarState(item.getCarState());
                    purchaseModel.setCompanyName(item.getCompanyName());
                    purchaseModel.setInStockTime(item.getInStockTime());
                    list.add(purchaseModel);
                }
                modelPageInfo.setList(list);
            }
            modelPageInfo.setTotal(count);
            ret.setSuccess(true);
            ret.setData(modelPageInfo);
            return ret;
        } catch (Exception e) {
            logger.error("查询总部采集列表失败" + ExceptionUtils.getFullStackTrace(e));
            ret.setSuccess(false);
            ret.setErrorCode("获取总部采集列表失败");
            return ret;
        }
    }

    /**
     * 根据车架号查询车辆信息
     *
     * @param httpRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryCarInfoByVin")
    public Result queryCarInfoByVin(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        Result<List<CarInfoResponse>> result = null;
        try {
            String data = obj.toJSONString();
            JSONObject json = JSON.parseObject(data);
            result = invoicingFacade.getCarInfoByVin(json.getString("vin"));
        } catch (Exception e) {
            logger.error("根据vin查询异常.", e);
        }
        if (result == null) {
            result.setSuccess(false);
            result.setErrorCode("数据处理异常");
            return result;
        }
        if (result.isSuccess()) {
            List<CarInfoResponse> data = result.getData();
            if (data == null || data.size() == 0) {
                result.setSuccess(false);
                result.setErrorCode("未查询到车辆信息");
            }
            result.setSuccess(true);
            return result;
        } else {
            result.setSuccess(false);
            result.setErrorCode(result.getErrorMsg());
            return result;
        }
    }


    /**
     * 增加总部集采单
     */
    @ResponseBody
    @RequestMapping(value = "/add")
    public Result addPurchase(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        String data = obj.toJSONString();
        PurchaseModel purchase = JSONObject.parseObject(data, PurchaseModel.class);
        Result result = new Result();
        // 根据vin查询总部集采单数量
        int purchaseCountByVin = invoicingFacade.getPurchaseCountByVin(purchase.getVin());
        if (purchaseCountByVin == 1) {
            result.setSuccess(false);
            result.setErrorMsg("车架号已存在.");
            return result;
        } else if (purchaseCountByVin > 1) {
            result.setSuccess(false);
            result.setErrorMsg("车架号存在多条.");
            return result;
        }

        HashMap<String, Object> params = new HashMap<>();
        String orderNo = invoicingFacade.purchaseOrderNumber(); // 生成采集单号
        params.put("orderNumber", orderNo);
        params.put("vin", purchase.getVin());
        params.put("carTypeId", purchase.getCarTypeId());
        params.put("carTypeDesc", purchase.getCarTypeDesc());
        params.put("guidingPrice", purchase.getGuidingPrice());
        params.put("carEngineNo", purchase.getCarEngineNo());
        params.put("colour", purchase.getColour());
        params.put("interiorColour", purchase.getInteriorColour());
        params.put("totalAmount", purchase.getTotalAmount());
        params.put("serviceFee", purchase.getServiceFee());
        params.put("depositAmount", purchase.getDepositAmount());
        params.put("depositAmountTime", new Date());
        params.put("companyId", purchase.getCompanyId());
        params.put("companyName", purchase.getCompanyName());
        params.put("totalCount", 1);
        params.put("carState", 1);
        invoicingFacade.addPurchase(params);
        result.setSuccess(true);
        return result;
    }


    /**
     * 根据Id查询总部采集单
     */
    @ResponseBody
    @RequestMapping(value = "/item")
    public Result getPurchaseById(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        HashMap<String, Object> params = new HashMap<>();
        Result<PurchaseModel> result = new Result<>();
        String data = obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        params.put("id", json.getString("id"));
        PageInfo<HeadquartersPurchaseResponse> purchaseList = invoicingFacade.getPurchaseListPage(params);
        PurchaseModel purchaseModel = new PurchaseModel();
        if (purchaseList.getList() != null && purchaseList.getList().size() == 1) {
            HeadquartersPurchaseResponse item = purchaseList.getList().get(0);
            purchaseModel.setId(item.getId());
            //purchaseModel.setOrderNumber(item.getOrderNumber());
            purchaseModel.setVin(item.getVin());
            purchaseModel.setCarTypeId(item.getCarTypeId());
            purchaseModel.setCarTypeDesc(item.getCarTypeDesc());
            purchaseModel.setGuidingPrice(item.getGuidingPrice());
            purchaseModel.setColour(item.getColour());
            purchaseModel.setInteriorColour(item.getInteriorColour());
            purchaseModel.setTotalAmount(item.getTotalAmount());
            purchaseModel.setServiceFee(item.getServiceFee());
            purchaseModel.setDepositAmount(item.getDepositAmount());
            //purchaseModel.setTailAmountTime(item.getTailAmountTime());
            //purchaseModel.setCarState(item.getCarState());
            purchaseModel.setCompanyId(item.getCompanyId());
            purchaseModel.setCompanyName(item.getCompanyName());
        }
        result.setSuccess(true);
        result.setData(purchaseModel);
        return result;
    }

    /**
     * 修改总部采集单
     */
    @ResponseBody
    @RequestMapping(value = "/update")
    public Result updatePurchaseById(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        Result result = new Result();
        String data = obj.toJSONString();
        PurchaseModel purchase = JSONObject.parseObject(data, PurchaseModel.class);
        if (purchase.getId() == null) {
            result.setSuccess(false);
            result.setErrorMsg("总部集采单Id为空.");
            return result;
        }

        HashMap<String, Object> condition = new HashMap<>();
        condition.put("id", purchase.getId());
        PageInfo<HeadquartersPurchaseResponse> purchaseList = invoicingFacade.getPurchaseListPage(condition);
        if (purchaseList.getList() != null && purchaseList.getList().size() == 1) {
            HeadquartersPurchaseResponse item = purchaseList.getList().get(0);
            String oldVin = item.getVin();
            if (oldVin != null && oldVin.equals(purchase.getVin())) {
                // 没有修改vin
            } else {
                // 修改了vin, 查询
                // 根据vin查询总部集采单数量
                int purchaseCountByVin = invoicingFacade.getPurchaseCountByVin(purchase.getVin());
                if (purchaseCountByVin == 1) {
                    result.setSuccess(false);
                    result.setErrorMsg("车架号已存在.");
                    return result;
                } else if (purchaseCountByVin > 1) {
                    result.setSuccess(false);
                    result.setErrorMsg("车架号存在多条.");
                    return result;
                }
            }
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("id", purchase.getId());
        params.put("vin", purchase.getVin());
        params.put("carTypeId", purchase.getCarTypeId());
        params.put("carTypeDesc", purchase.getCarTypeDesc());
        params.put("guidingPrice", purchase.getGuidingPrice());
        params.put("carEngineNo", purchase.getCarEngineNo());
        params.put("colour", purchase.getColour());
        params.put("interiorColour", purchase.getInteriorColour());
        params.put("totalAmount", purchase.getTotalAmount()); //采购价格
        params.put("serviceFee", purchase.getServiceFee()); //服务费(补票)
        params.put("depositAmount", purchase.getDepositAmount()); //采购定金金额
        params.put("companyId", purchase.getCompanyId());
        params.put("companyName", purchase.getCompanyName());
        params.put("orderNumber",purchase.getOrderNumber());

        invoicingFacade.updatePurchaseById(params);

        result.setSuccess(true);
        return result;
    }

    /**
     * 总部集采尾款结算
     */
    @ResponseBody
    @RequestMapping(value = "/finalsettlement")
    public Result purchaseFinalSettlement(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        HashMap<String, Object> params = new HashMap<>();
        Result result = new Result();
        String data = obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        params.put("id", json.getString("id"));
        params.put("tailAmountTime", new Date());

        invoicingFacade.updatePurchaseById(params);

        result.setSuccess(true);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getbrandinfonew", method = RequestMethod.POST)
    public Result getBrandInfoNew(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        String data = obj.toJSONString();
        JSONObject json = JSON.parseObject(data);
        String code = json.getString("parentid");
        String isMake = json.getString("ifmake");
        Result res = new Result();
        try {
            if (StringUtil.isNotBlank(code) && (3 == code.length())) {
                Result<List<CarmodMakerVo>> result = new Result<>();
                List<CarmodMakerVo> carmodMakerVoList = carmodMakerBrandService.getListVoByBrandCode(code);
                result.setSuccess(true);
                result.setData(carmodMakerVoList);
                return result;

            } else if (StringUtil.isNotBlank(code) && (6 == code.length())) {
                Result<List<CarmodSeriesVo>> result = new Result<>();
                List<CarmodSeriesVo> carmodSeriesVoList = carmodSeriesService.getSeriesVoListBybrandCode(code, org.springframework.util.StringUtils.hasLength(isMake) ? Integer.valueOf(isMake) : null);
                result.setSuccess(true);
                result.setData(carmodSeriesVoList);
                return result;
            } else {
                Result<List<CarmodBrandVo>> result = new Result<>();
                List<CarmodBrandVo> carmodBrandVoList = carmodBrandService.getBrandVos(org.springframework.util.StringUtils.hasLength(isMake) ? Integer.valueOf(isMake) : null);
                result.setSuccess(true);
                result.setData(carmodBrandVoList);
                return result;
            }
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
            res.setSuccess(false);
            res.setErrorMsg("获取品牌异常");
            return res;
        }
    }

    /**
     * 取得车系
     *
     * @param httpRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getcars", method = RequestMethod.POST)
    public Result getCars(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        Result<List<BrandInfoModel>> res = new Result<>();
        try {
            String data = obj.toJSONString();
            JSONObject json = JSON.parseObject(data);
            String brandcode = json.getString("brandcode");
            String ifmake = json.getString("ifmake");
            res.setSuccess(true);
            res.setData(brandService.getBrandByCode(brandcode, org.springframework.util.StringUtils.hasLength(ifmake) ? Integer.valueOf(ifmake) : null));
            return res;
        } catch (Exception ex) {
            logger.error(ex);
            res.setSuccess(false);
            res.setErrorMsg("数据处理异常");
            return res;
        }
    }

    /**
     * 取得汽车型号
     *
     * @param httpRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getcarname", method = RequestMethod.POST)
    public Result getCarBaseInfo(HttpServletRequest httpRequest, @RequestBody JSONObject obj) {
        Result<List<CarBaseModel>> res = new Result<>();
        Map<String, Object> param = new HashMap<String, Object>();
        try {
            String data = obj.toJSONString();
            JSONObject json = JSON.parseObject(data);
            String brandcode = json.getString("brandcode");
            param.put("carcode", brandcode);
            res.setSuccess(true);
            res.setData(carBaseService.getList(param));
            return res;
        } catch (Exception ex) {
            logger.error(ex);
            res.setSuccess(false);
            res.setErrorMsg("数据处理异常");
            return res;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/getCompanyByName", method = RequestMethod.POST)
    public Result getCompanyByName(HttpServletRequest httpRequest, @RequestBody JSONObject obj){
        Result<List<CompanyModel>> res = new Result<>();
        try{
            String data = obj.toJSONString();
            JSONObject json = JSON.parseObject(data);
            String companyname = json.getString("companyname");
            if(!org.springframework.util.StringUtils.hasLength(companyname)){
                res.setSuccess(false);
                res.setErrorMsg("companyname不能为空");
                return res;
            }else{
                Map<String,Object> map = new HashMap<String,Object>();
                map.put("companyname", companyname);
                List<CompanyModel> companyModels = companyService.getList(map);
                res.setSuccess(true);
                res.setData(companyModels);
                return res;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            logger.error(ExceptionUtils.getFullStackTrace(e));
            res.setSuccess(false);
            res.setErrorMsg("获取数据异常");
            return res;
        }
    }

    /**
     * 导出
     *
     * @return
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public String purchaseeExport(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "vin", required = false) String vin,
                                  @RequestParam(value = "carType", required = false) String carType,
                                  @RequestParam(value = "carState", required = false) String carState,
                                  @RequestParam(value = "companyName", required = false) String companyName,
                                  @RequestParam(value = "depositAmountTimeStart", required = false) String depositAmountTimeStart,
                                  @RequestParam(value = "depositAmountTimeEnd", required = false) String depositAmountTimeEnd,
                                  @RequestParam(value = "tailAmountTimeStart", required = false) String tailAmountTimeStart,
                                  @RequestParam(value = "tailAmountTimeEnd", required = false) String tailAmountTimeEnd
    ) {
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        ServletOutputStream out = null;
        try {
            String excelName = "总部集采数据";
            // 转码防止乱码
            response.addHeader("Content-Disposition", ExcelUtil.fileDisposition(request.getHeader("user-agent"), excelName) + ".xls");
            String[] headers = new String[]{"集采业务号", "车架号", "车型", "官方指导价", "外观内饰颜色", "采购数量",
                    "单车采购价格", "服务费（补票）", "采购订金日期", "采购订金金额", "尾款日期", "车辆状态", "商户名称", "入库天数"};
            String[] columnNames = new String[]{"orderNumber", "vin", "carTypeDesc", "guidingPrice", "interiorColour", "totalCount",
                    "totalAmount", "serviceFee", "depositAmountTime", "depositAmount", "tailAmountTime", "carState", "companyName", "inStockDays"};
            out = response.getOutputStream();

            HashMap<String, Object> params = new HashMap<>();
            if (StringUtils.isNotBlank(vin)) {
                params.put("vin", vin.trim());
            }
            if (StringUtils.isNotBlank(carType)) {
                params.put("carTypeDesc", carType.trim());
            }
            if (StringUtils.isNotBlank(carState)) {
                params.put("carState", carState.trim());
            }
            if (StringUtils.isNotBlank(companyName)) {
                params.put("companyName", companyName.trim());
            }
            if (StringUtils.isNotBlank(depositAmountTimeStart)) {
                Date date = DateUtils.parseDate(depositAmountTimeStart, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getStartOfDay(date);
                    params.put("depositAmountTimeStart", date);
                }
            }
            if (StringUtils.isNotBlank(depositAmountTimeEnd)) {
                Date date = DateUtils.parseDate(depositAmountTimeEnd, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getEndOfDay(date);
                    params.put("depositAmountTimeEnd", date);
                }
            }
            if (StringUtils.isNotBlank(tailAmountTimeStart)) {
                Date date = DateUtils.parseDate(tailAmountTimeStart, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getStartOfDay(date);
                    params.put("tailAmountTimeStart", date);
                }
            }
            if (StringUtils.isNotBlank(tailAmountTimeEnd)) {
                Date date = DateUtils.parseDate(tailAmountTimeEnd, "yyyy-MM-dd");
                if (date != null) {
                    date = DateUtils.getEndOfDay(date);
                    params.put("tailAmountTimeEnd", date);
                }
            }
            params.put("start", 0);
            params.put("end", 10000);
            PageInfo<HeadquartersPurchaseResponse> purchaseList = invoicingFacade.getPurchaseListPage(params);

            //组装成excel需要的数据格式
            List<Map<String, Object>> exportList = new ArrayList<Map<String, Object>>();
            Map<String, Object> exportMap = null;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            for (HeadquartersPurchaseResponse p : purchaseList.getList()) {
                exportMap = new HashMap<String, Object>();
                exportMap.put("orderNumber", p.getOrderNumber() != null ? p.getOrderNumber() : "");
                exportMap.put("vin", p.getVin() != null ? p.getVin() : "");
                exportMap.put("carTypeDesc", p.getCarTypeDesc() != null ? p.getCarTypeDesc() : "");
                exportMap.put("guidingPrice", p.getGuidingPrice() != null ? p.getGuidingPrice() : "");
                exportMap.put("interiorColour", p.getInteriorColour() != null ? p.getInteriorColour() : "");
                exportMap.put("totalCount", p.getTotalCount() != null ? p.getTotalCount() : "");
                exportMap.put("totalAmount", p.getTotalAmount() != null ? p.getTotalAmount() : "");
                exportMap.put("serviceFee", p.getServiceFee() != null ? p.getServiceFee() : "");
                exportMap.put("depositAmountTime", p.getDepositAmountTime() != null ? df.format(p.getDepositAmountTime()) : "");
                exportMap.put("depositAmount", p.getDepositAmount() != null ? p.getDepositAmount() : "");
                exportMap.put("tailAmountTime", p.getTailAmountTime() != null ? df.format(p.getTailAmountTime()) : "");
                String carStatus = "";
                //1-在途,2-已入库,3-已销售',
                if ("1".equals(p.getCarState())) {
                    carStatus = "在途";
                } else if ("2".equals(p.getCarState())) {
                    carStatus = "已入库";
                } else if ("3".equals(p.getCarState())) {
                    carStatus = "已销售";
                }
                exportMap.put("carState", carStatus);
                exportMap.put("companyName", p.getCompanyName() != null ? p.getCompanyName() : "");

                String inStockDays = "";
                if (p.getInStockTime() != null) {
                    PurchaseModel model = new PurchaseModel();
                    model.setInStockTime(p.getInStockTime());
                    Integer days = model.getInStockDays();
                    if (days != null) {
                        inStockDays += days;
                    }
                }
                exportMap.put("inStockDays", inStockDays);

                exportList.add(exportMap);
            }
            ExcelUtil.exportExcel(excelName, headers, columnNames, exportList, out);
            return Util.getSuccessJson("导出成功").toString();
        } catch (Exception ex) {
            logger.error(ex);
            return Util.getFailureJson().toString();
        } finally {
            StreamUtils.streamFlushAndClose(out, out);
        }
    }


}
