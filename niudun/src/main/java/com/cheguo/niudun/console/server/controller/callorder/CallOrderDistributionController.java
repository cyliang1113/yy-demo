package com.cheguo.niudun.console.server.controller.callorder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cheguo.basic.model.DataPageBo;
import com.cheguo.commons.util.StreamUtils;
import com.cheguo.commons.util.StringUtil;
import com.cheguo.foundation.core.common.Result;
import com.cheguo.order.enums.DealerTrackStatusEnum;
import com.cheguo.order.model.callorder.*;
import com.cheguo.order.service.callorder.CallOrderDealerDistributionService;
import com.cheguo.order.service.callorder.CallOrderDealerImportService;
import com.cheguo.order.service.callorder.CallOrderDealerService;
import com.cheguo.toolkit.utils.ExcelUtil;
import com.cheguo.util.ResultDO;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**电销工单车商分配页面相关接口controller
 * /api/ct:c端接口前缀标识
 * Created by wuzhiquan@zafh.com.cn on 2018/8/30.
 */
@RestController
@RequestMapping("/api/ct/distribute")
public class CallOrderDistributionController {

    private static final Logger logger = Logger.getLogger(CallOrderDistributionController.class);

    @Autowired
    private CallOrderDealerService callOrderDealerService;

    @Autowired
    private CallOrderDealerDistributionService callOrderDealerDistributionService;

    @Autowired
    private CallOrderDealerImportService callOrderDealerImportService;

    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    public String initList(HttpServletRequest request){
        return "callorder/distribution/list";
    }


    /**
     * 获取待分配车商列表数据
     * @param httpRequest
     * @param callOrderUnHandleDealerRequest
     * @return Result
     */
    @RequestMapping(value = "/getList" ,method = RequestMethod.POST)
    public Result getList(HttpServletRequest httpRequest,@RequestParam String currentPage,
                          @RequestParam String pageSize,CallOrderUnHandleDealerRequest callOrderUnHandleDealerRequest){
        Result<DataPageBo<CallOrderDealer>> result = new Result<>();
        if (org.apache.commons.lang3.StringUtils.isBlank(currentPage) || org.apache.commons.lang3.StringUtils.isBlank(pageSize)) {
            result.setSuccess(false);
            result.setErrorMessage("分页参数不能为空!");
            logger.error("分页参数为空!");
            return result;
        }
        try {
            callOrderUnHandleDealerRequest.setCompanyLabelStatus(DealerTrackStatusEnum.NON_TRACK.getValue());
            DataPageBo<CallOrderDealer> dataPageBo = callOrderDealerService.queryUnHandleDealerForPage(callOrderUnHandleDealerRequest, Integer.valueOf(currentPage), Integer.valueOf(pageSize));
            result.setSuccess(true);
            result.setData(dataPageBo);
        }catch (Exception e){
            logger.error("获取待分配车商列表数据失败"+e);
            result.setSuccess(false);
            result.setErrorMessage("获取待分配车商列表数据失败");
        }
        return result;
    }

    /**
     * 获取未处理车商数量
     * @param httpRequest
     * @return Result
     */
    @RequestMapping(value = "/getUnHandledDealerCount" ,method = RequestMethod.POST)
    public Result getUnHandledDealerCount(HttpServletRequest httpRequest){
        Result<List<CallOrderUnHandleDealerCountBO>> result = new Result<>();
        try {
            ResultDO<List<CallOrderUnHandleDealerCountBO>> resultDO = callOrderDealerService.getUnHandledDealerCount();
            if(!resultDO.isSuccess()){
                result.setSuccess(false);
                result.setErrorMessage("获取未处理车商数量接口调用异常");
                return result;
            }
            result.setSuccess(true);
            result.setData(resultDO.getModule());
        }catch (Exception e){
            result.setSuccess(false);
            result.setErrorMessage("获取未处理车商数量失败");
        }
        return result;
    }

    /**
     * 分配车商
     * @param httpRequest
     * @param dealerDistributionBO
     * @return Result
     */
    @RequestMapping(value = "/distributionDealer" ,method = RequestMethod.POST)
    public Result distributionDealer(HttpServletRequest httpRequest,CallOrderDealerDistributionBO dealerDistributionBO,String userStr){
        Result<String> result = new Result<>();
        try {
            if(StringUtil.isBlank(userStr)){
                result.setSuccess(false);
                result.setErrorMessage("待跟进人员不能为空");
                return result;
            }
            List<Map> userList = JSONArray.parseArray(userStr,Map.class);
            List<Map<String, String>> userIds = new ArrayList<>();
            for(Map map : userList){
                Map<String, String> hashMap = new HashMap<>();
                hashMap.put("userId",String.valueOf(map.get("userId")));
                hashMap.put("userNo",String.valueOf(map.get("userNo")));
                hashMap.put("userName",String.valueOf(map.get("userName")));
                userIds.add(hashMap);
            }
            dealerDistributionBO.setTrackorInfos(userIds);
            ResultDO<String> resultDO = callOrderDealerDistributionService.distributionDealer(dealerDistributionBO);
            if(!resultDO.isSuccess()){
                result.setSuccess(false);
                result.setErrorMessage(resultDO.getModule());
                return result;
            }
            result.setSuccess(true);
            result.setData(resultDO.getModule());
        }catch (Exception e){
            result.setSuccess(false);
            result.setErrorMessage("车商分配失败");
        }
        return result;
    }

    /**
     * 导入车商
     * @param response
     * @param request
     * @param multipartFile
     */
    @RequestMapping(value = "/importDealerInfo" ,method = RequestMethod.POST)
    public void importDealerInfo(HttpServletResponse response, HttpServletRequest request,
                               @RequestParam(value = "file", required = false) MultipartFile multipartFile){
        String result = "";
        PrintWriter pw = null;
        InputStream is = null;
        Workbook workbook = null;
        try {
            pw = response.getWriter();
            //获取文件的后缀名
            String fileName = multipartFile.getOriginalFilename();
            String extName = "";
            if(fileName.indexOf(".") > 0){
                extName = fileName.substring(fileName.lastIndexOf("."));
            }
            String customDealerLabel = request.getParameter("customDealerLabel");
            is = multipartFile.getInputStream();
            if(extName.toLowerCase().equals(".xls")){
                workbook = new HSSFWorkbook(is);
            }else if(extName.toLowerCase().equals(".xlsx")){
                workbook = new XSSFWorkbook(is);
            }
            result = checkExcel(workbook,0);
            if(StringUtil.isBlank(result)){
                List<CallOrderDealer> list = exportListFromExcel(workbook,0);
                ResultDO<CallOrderDealerImportResponse> resultDO = callOrderDealerImportService.importDealerInfo(list,customDealerLabel);
                logger.info("导入车商返回结果："+JSON.toJSONString(resultDO.getModule()));
                result = JSON.toJSONString(resultDO);
            }
        }catch (Exception e){
            logger.error("excel文件数据导入异常：",e);
            result = "{ \"message\":"+"\""+e.getMessage()+"\""+"}";
        }finally {
            try {
                pw.write(result);
                pw.close();
                is.close();
            }catch (Exception e){
                logger.error("流处理异常！" + e);
            }
        }
    }

    /**
     * 校验excel格式
     * @param workbook
     * @param sheetNum
     * @return String
     */
    private String checkExcel(Workbook workbook, int sheetNum){
        ResultDO<CallOrderDealerImportResponse> resultDO = new ResultDO<>();
        Sheet sheet = workbook.getSheetAt(sheetNum);
        int maxRowIx = sheet.getLastRowNum();
        if(maxRowIx < 1){
            resultDO.setSuccess(false);
            resultDO.setMsg("导入的excel模板格式不正确");
            return JSON.toJSONString(resultDO);
        }else {
            Row row = sheet.getRow(1);
            short maxColIx = row.getLastCellNum();
            if(maxColIx > 2){
                resultDO.setSuccess(false);
                resultDO.setMsg("导入的excel模板格式不正确");
                return JSON.toJSONString(resultDO);
            }
        }
        return null;
    }

    /**
     * 由指定的Sheet导出至List
     * @param workbook
     * @param sheetNum
     * @return list
     * @throws Exception
     */
    private List<CallOrderDealer> exportListFromExcel(Workbook workbook, int sheetNum) throws Exception{
        List<CallOrderDealer> list = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(sheetNum);
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        int minRowIx = sheet.getFirstRowNum();
        int maxRowIx = sheet.getLastRowNum();
        if(maxRowIx < 1){
            return list;
        }
        for (int rowIx = minRowIx ; rowIx <= maxRowIx ; rowIx++){
            if(0 == rowIx){
                continue;
            }
            Row row = sheet.getRow(rowIx);
            short minColIx = row.getFirstCellNum();
            short maxColIx = row.getLastCellNum();
            CallOrderDealer callOrderDealer = new CallOrderDealer();
            for(short colIx = minColIx ; colIx <= maxColIx ; colIx++){
                Cell cell = row.getCell(new Integer(colIx));
                CellValue cellValue = evaluator.evaluate(cell);
                if(cell == null){
                    continue;
                }
                //车商编号
                if(0 == colIx){
                    String companyCode;
                    if(null == cellValue){
                        companyCode = "";
                    }else {
                        if(StringUtil.isBlank(cellValue.getStringValue())){
                            BigDecimal bd2 = new BigDecimal(cellValue.getNumberValue());
                            companyCode = bd2.toPlainString();
                        }else {
                            companyCode = cellValue.getStringValue();
                        }
                    }
                    callOrderDealer.setCompanyCode(companyCode);
                }
                if(1 == colIx){
                    String companyName;
                    if(null == cellValue){
                        companyName = "";
                    }else {
                        if(StringUtil.isBlank(cellValue.getStringValue())){
                            BigDecimal bd2 = new BigDecimal(cellValue.getNumberValue());
                            companyName = bd2.toPlainString();
                        }else {
                            companyName = cellValue.getStringValue();
                        }
                    }
                    callOrderDealer.setCompanyName(companyName);
                }
            }
            list.add(callOrderDealer);
        }
        return list;
    }

    /**
     * 导入失败的车商导出
     * @param request
     * @param response
     * @return Result
     */
    @RequestMapping(value = "/exportFailureDealer" ,method = RequestMethod.GET)
    public Result exportFailureDealer(HttpServletRequest request,HttpServletResponse response){
        Result<List<CallOrderDealerExportResponse>> result = new Result<>();
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        ServletOutputStream out = null;
        try {
            String excelName = "导入失败的车商列表";
            // 转码防止乱码
            response.addHeader("Content-Disposition", ExcelUtil.fileDisposition(request.getHeader("user-agent"), excelName) + ".xls");
            String[] headers = new String[] {"车商编号" , "车商名称" ,"导入失败原因"};
            String[] columnNames = new String[]{"companyCode","companyName","dealerImportResult"};
            out = response.getOutputStream();
            String customDealerLabel = request.getParameter("customDealerLabel");

            ResultDO<List<CallOrderDealerExportResponse>> resultDO = callOrderDealerImportService.getImportFailureDealer(customDealerLabel);
            if(!resultDO.isSuccess()){
                result.setSuccess(false);
                result.setErrorMessage(resultDO.getMsg());
                return result;
            }
            List<CallOrderDealerExportResponse> list = resultDO.getModule();
            logger.info("导入失败的车商数据:"+JSON.toJSONString(list));
            //组装成excel需要的数据格式
            List<Map<String,Object>> exportList = new ArrayList<>();
            Map<String, Object> exportMap;
            for(CallOrderDealerExportResponse dealerExportResponse : list){
                exportMap = new HashMap<>();
                exportMap.put("companyCode", dealerExportResponse.getCompanyCode() != null ? dealerExportResponse.getCompanyCode() : "");
                exportMap.put("companyName", dealerExportResponse.getCompanyName() != null ? dealerExportResponse.getCompanyName() : "");
                exportMap.put("dealerImportResult", dealerExportResponse.getDealerImportResult() != null ? dealerExportResponse.getDealerImportResult() : "");
                exportList.add(exportMap);
            }
            ExcelUtil.exportExcel(excelName, headers,columnNames, exportList, out);
            result.setSuccess(true);
            result.setData(resultDO.getModule());
        } catch (Exception e){
            logger.error("车商导出失败:"+e);
            result.setSuccess(false);
            result.setErrorMessage("车商导出失败");
        } finally {
            StreamUtils.streamFlushAndClose(out, out);
        }
        return result;
    }


}
