package com.cheguo.niudun.console.server.controller.log;

import com.cheguo.basic.model.DataPageBo;
import com.cheguo.basic.model.Result;
import com.cheguo.toolkit.utils.DateUtils;
import com.cheguo.userinfo.model.ServiceLogModel;
import com.cheguo.userinfo.request.QueryServiceLogRequest;
import com.cheguo.userinfo.service.IServiceLogService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Created by luodn on 2018/10/23.
 */
@Slf4j
@Controller
@RequestMapping("/serviceLog")
public class ServiceLogController {
    @Autowired
    private IServiceLogService serviceLogService;

    @ResponseBody
    @RequestMapping(value = "/queryServiceLog", method = RequestMethod.POST)
    public Result queryServiceLog(@RequestBody QueryServiceLogRequest queryRequest) {
        try {
            if(queryRequest == null || queryRequest.getCurrentPage() == null || queryRequest.getPageSize() == null){
                return Result.buildFail("20001","必填字段为空");
            }
            queryRequest.setCurrentPage(queryRequest.getCurrentPage() - 1);
            Map<String, Object> map = buildSearchMap(queryRequest);

            DataPageBo<ServiceLogModel> response = serviceLogService.getServiceLogList(map, queryRequest.getCurrentPage(), queryRequest.getPageSize());
            return Result.buildSucc(response);
        } catch (Exception ex) {
            log.error("查询失败", ex);
            return Result.buildFail("20002","查询失败");
        }
    }

    /**
     * 构建查询车商查询日志的请求 map
     * @param queryRequest 查询参数
     * @return
     */
    private Map<String, Object> buildSearchMap(QueryServiceLogRequest queryRequest){
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isNotBlank(queryRequest.getCompanyName())){
            map.put("companyName",queryRequest.getCompanyName());
        }
        if(StringUtils.isNotBlank(queryRequest.getUserName())){
            map.put("userName",queryRequest.getUserName());
        }
        if(StringUtils.isNotBlank(queryRequest.getRealName())){
            map.put("realName",queryRequest.getRealName());
        }
        if(queryRequest.getCreateTimeStart() != null){
            map.put("createTimeStart", DateUtils.formatFullDate(DateUtils.getStartOfDay(queryRequest.getCreateTimeStart())));
        }
        if(queryRequest.getCreateTimeEnd() != null){
            map.put("createTimeEnd", DateUtils.formatFullDate(DateUtils.getEndOfDay(queryRequest.getCreateTimeEnd())));
        }

        return map;
    }
}
