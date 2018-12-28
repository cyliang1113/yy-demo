package com.cheguo.niudun.console.server.controller.callorder;

import com.cheguo.apollo.sys.base.response.user.UserOnlyDetailRespDTO;
import com.cheguo.apollo.sys.biz.manager.SysRoleManager;
import com.cheguo.basic.model.DataPageBo;
import com.cheguo.basic.model.PubCodeModel;
import com.cheguo.commons.util.StreamUtils;
import com.cheguo.foundation.core.common.Result;
import com.cheguo.order.model.callorder.*;
import com.cheguo.order.service.callorder.CallOrderDealerService;
import com.cheguo.order.service.callorder.CallOrderTrackService;
import com.cheguo.toolkit.utils.ExcelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author Wangjuguo
 * @Date 2018-08-30-18:27
 * @Description 电销工单车商列表、跟进信息
 */
@RestController
@RequestMapping("/api/ct")
public class CallOrderDealerTrackController {

    Logger logger = Logger.getLogger(CallOrderDealerTrackController.class);

    private static DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private CallOrderTrackService callOrderTrackService;

    @Autowired
    private CallOrderDealerService callOrderDealerService;

    @Autowired
    private SysRoleManager sysRoleManager;

    /**
     * @Author Wangjuguo
     * @Date 2018/8/31 9:05
     * @Param companyId
     * @Return Result
     * @Throws
     * @Description: 根据车商id获取车商跟进信息
     */
    @PostMapping("/callOrder/getTrackById")
    public Result getTrackById(@RequestParam Integer companyId) {
        Result<Map<String, Object>> ret = new Result<>();
        try {
            if (null == companyId) {
                logger.error("车商id为空!");
                ret.setSuccess(false);
                ret.setErrorMessage("请输入正确的车商id!");
                return ret;
            }
            Map<String, Object> key = callOrderTrackService.getByCompanyId(companyId);
            ret.setData(key);
            ret.setSuccess(true);
            return ret;
        } catch (Exception e) {
            logger.error("获取车商跟进信息异常!");
            e.printStackTrace();
            ret.setSuccess(false);
            ret.setErrorMessage("获取车商跟进信息异常!");
            return ret;
        }
    }

    /**
     * @Author Wangjuguo
     * @Date 2018/8/31 9:05
     * @Param callOrderTrackDto
     * @Return Result
     * @Throws
     * @Description: 新增车商跟进信息
     */
    @PostMapping("/callOrder/insertTrackInfo")
    public Result insertTrackInfo(CallOrderTrackDto callOrderTrackDto) {
        Result<String> ret = new Result<>();
        try {
            if (null == callOrderTrackDto.getCompanyId() || null == callOrderTrackDto.getCommunicateResult() || null == callOrderTrackDto.getCommunicateResultType()) {
                logger.error("车商id、沟通结果为空!");
                ret.setErrorMessage("新增失败，车商id、沟通结果为空!");
                ret.setSuccess(false);
                return ret;
            }
            int result = callOrderTrackService.insertDealerTrackInfo(callOrderTrackDto);
            if (result > 0) {
                ret.setSuccess(true);
                ret.setData("提交成功!");
                return ret;
            }
            ret.setSuccess(false);
            ret.setErrorMessage("新增车商跟进信息失败!");
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增车商跟进信息异常!");
            ret.setSuccess(false);
            ret.setErrorMessage("新增车商跟进信息异常!");
            return ret;
        }
    }

    /**
     * @Author Wangjuguo
     * @Date 2018/8/31 9:05
     * @Param callOrderDealerDto, currentPage, pageSize
     * @Return Result
     * @Throws
     * @Description: 获取车商列表
     */
    @PostMapping("/callOrder/getDealerList")
    public Result getDealerList(CallOrderDealerDto callOrderDealerDto,
                                @RequestParam String currentPage,
                                @RequestParam String pageSize) {
        Result<DataPageBo<CallOrderDealer>> ret = new Result<>();
        try {
            if (StringUtils.isBlank(currentPage) || StringUtils.isBlank(pageSize)) {
                ret.setSuccess(false);
                ret.setErrorMessage("分页参数不能为空!");
                logger.error("分页参数为空!");
                return ret;
            }
            DataPageBo<CallOrderDealer> callOrderDealerDataPageBo = callOrderDealerService.queryForPage(callOrderDealerDto, Integer.valueOf(currentPage), Integer.valueOf(pageSize));
            ret.setData(callOrderDealerDataPageBo);
            ret.setSuccess(true);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询车商列表异常!");
            ret.setSuccess(false);
            ret.setErrorMessage("条件查询车商列表异常!");
            return ret;
        }
    }

    /**
     * @Author Wangjuguo
     * @Date 2018/8/31 9:05
     * @Param companyId, currentPage, pageSize
     * @Return Result
     * @Throws
     * @Description: 获取车商跟进列表
     */
    @PostMapping("/callOrder/getDealerTrackList")
    public Result getDealerTrackList(@RequestParam String companyId,
                                     @RequestParam String currentPage,
                                     @RequestParam String pageSize) {
        Result<DataPageBo<CallOrderTrack>> ret = new Result<>();
        try {
            if (StringUtils.isBlank(currentPage) || StringUtils.isBlank(pageSize) || StringUtils.isBlank(companyId)) {
                ret.setSuccess(false);
                ret.setErrorMessage("分页参数、车商id不能为空!");
                logger.error("分页参数为空!");
                return ret;
            }
            CallOrderTrackQuery callOrderTrackQuery = new CallOrderTrackQuery();
            callOrderTrackQuery.setCompanyId(Integer.valueOf(companyId));
            DataPageBo<CallOrderTrack> callOrderTrackDataPageBo = callOrderTrackService.queryForPage(callOrderTrackQuery, Integer.valueOf(currentPage), Integer.valueOf(pageSize));
            ret.setData(callOrderTrackDataPageBo);
            ret.setSuccess(true);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询车商跟进信息列表异常!");
            ret.setSuccess(false);
            ret.setErrorMessage("条件查询跟进信息列表异常!");
            return ret;
        }
    }

    /**
     * @Author Wangjuguo
     * @Date 2018/8/31 9:05
     * @Param CallOrderDealerDto
     * @Return Result
     * @Throws
     * @Description: 导出车商列表
     */
    @GetMapping("/callOrder/exportDealerList")
    public Result exportExcel(CallOrderDealerDto dto, HttpServletRequest request, HttpServletResponse response) {
        Result<String> ret = new Result<>();
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        ServletOutputStream out = null;
        try {
            String excelName = "电销工单系统数据报表";
            response.addHeader("Content-Disposition", ExcelUtil.fileDisposition(request.getHeader("user-agent"), excelName) + ".xls");
            String[] headers = new String[] {"车商编号", "车商名称", "联系人姓名", "联系电话", "加盟类型", "车商类型", "注册时间", "沟通结果", "该联系人身份", "客户微信号", "对公司了解程度", "车商是否知道自己注册了车国app", "对车国业务的熟悉程度", "感兴趣模块（多类型要用，隔开）", "商家所在省", "商家所在市", "最近跟进记录", "最近跟进日期", "最近跟进人"};
            String[] columnNames = new String[] {"companyCode", "companyName", "linkman", "linkmanPhone", "joinType", "companyType", "registerTime", "linkResult", "linkmanDuty", "weChatNumber", "companyComprehend", "appRegisterInfo", "bussinessFamiliarity", "interestModule", "province", "city", "lastTrackInfo", "trackTime", "trackerName"};
            out = response.getOutputStream();
            // 获取导出数据列表
            List<CallOrderDealerResponse> list = callOrderDealerService.queryDealerList(dto);
            // 组装成excel所需的数据格式
            List<Map<String, Object>> exportList = new ArrayList<>();
            Map<String, Object> exportMap = null;
            for (CallOrderDealerResponse dealer : list) {
                exportMap = new HashMap<>();
                exportMap.put("companyCode", dealer.getCompanyCode());
                exportMap.put("companyName", dealer.getCompanyName());
                exportMap.put("linkman", dealer.getLinkman());
                exportMap.put("linkmanPhone", dealer.getLinkmanPhone());
                exportMap.put("joinType", dealer.getJoinType());
                exportMap.put("companyType", dealer.getCompanyType());
                exportMap.put("registerTime", dealer.getRegisterTime() == null? "" : df.format(dealer.getRegisterTime()));
                exportMap.put("linkResult", dealer.getLinkResult());
                exportMap.put("linkmanDuty", dealer.getLinkmanDuty());
                exportMap.put("weChatNumber", dealer.getWechatNumber());
                exportMap.put("companyComprehend", dealer.getCompanyComprehend());
                exportMap.put("appRegisterInfo", dealer.getAppRegisterInfo());
                exportMap.put("bussinessFamiliarity", dealer.getBussinessFamiliarity());
                exportMap.put("interestModule", dealer.getInterestModule());
                exportMap.put("province", dealer.getProvince());
                exportMap.put("city", dealer.getCity());
                exportMap.put("lastTrackInfo", dealer.getLastTrackInfo());
                exportMap.put("trackTime", dealer.getTrackTime() == null? "" : df.format(dealer.getTrackTime()));
                exportMap.put("trackerName", dealer.getTrackerName());
                exportList.add(exportMap);
            }
            logger.info("车商列表开始导出!");
            ExcelUtil.exportExcel(excelName, headers,columnNames, exportList, out);
            ret.setSuccess(true);
            ret.setData("导出成功!");
            logger.info("车商列表导出完成!");
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("车商列表导出异常!");
            ret.setSuccess(false);
            ret.setData("车商列表导出异常!");
            return ret;
        } finally {
            StreamUtils.streamFlushAndClose(out, out);
        }
    }

    /**
     * @Author Wangjuguo
     * @Date 2018/9/18 10:05
     * @Param
     * @Return Result
     * @Throws
     * @Description: 获取权限列表
     */
    @GetMapping("/callOrder/getRoleList")
    public Result getRoleList(){
        Result<List<UserOnlyDetailRespDTO>> ret = new Result<>();
        try {
            List<PubCodeModel> pubCodeModelList = callOrderDealerService.queryPubCodeList();
            logger.info("获取电销工单角色字典表数据成功!");
            List<String> list = new ArrayList<>();
            if (CollectionUtils.isNotEmpty(pubCodeModelList)) {
                for (PubCodeModel model : pubCodeModelList) {
                    list.add(model.getVname());
                }
            }
            List<UserOnlyDetailRespDTO> roleCodeList = sysRoleManager.getUserListByMultipleRoleCode(list);
            logger.info("获取角色列表成功!");
            ret.setSuccess(true);
            ret.setData(roleCodeList);
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取角色列表异常!");
            ret.setSuccess(false);
            ret.setErrorMessage("获取角色列表异常!");
            return ret;
        }
    }
}
