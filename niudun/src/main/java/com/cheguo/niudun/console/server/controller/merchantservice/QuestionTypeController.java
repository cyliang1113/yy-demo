package com.cheguo.niudun.console.server.controller.merchantservice;

import com.cheguo.basic.model.DataPageBo;
import com.cheguo.commons.rpc.lang.Result;
import com.cheguo.other.model.QuestionType;
import com.cheguo.other.service.IQuestionTypeService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@RestController
@RequestMapping("/merchantService/questionType")
public class QuestionTypeController {
    Logger logger = Logger.getLogger(QuestionTypeController.class);

    @Resource
    private IQuestionTypeService questionTypeService;


    @RequestMapping(value = "/list")
    public Result list(
            @RequestParam(value = "questionTypeNameLike", required = false) String questionTypeNameLike,
            @RequestParam(value = "application", required = false) String application,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "currentPage", required = false) Integer currentPage,
            @RequestParam(value = "pageSize", required = false) Integer pageSize
    ) {
        HashMap<String, Object> params = new HashMap<>();
        if(StringUtils.isNotBlank(questionTypeNameLike)){
            params.put("questionTypeNameLike", questionTypeNameLike);
        }
        if(StringUtils.isNotBlank(application)){
            params.put("application", application);
        }
        if(StringUtils.isNotBlank(status)){
            params.put("status", status);
        }

        params.put("orderBy", "create_time");
        params.put("orderType", "asc");

        if(currentPage == null){
            currentPage = 0;
        }else if(currentPage >= 1){
            currentPage = currentPage - 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }

        DataPageBo<QuestionType> pageList = questionTypeService.getList(params, currentPage, pageSize);
        return Result.buildSucc(pageList);
    }

    @RequestMapping(value = "/item")
    public Result item(
            @RequestParam(value = "questionTypeId", required = true) Integer questionTypeId
    ) {
        QuestionType questionType = questionTypeService.selectByPrimaryKey(questionTypeId);
        return Result.buildSucc(questionType);
    }

    @RequestMapping(value = "update")
    public Result update(HttpServletRequest httpRequest, QuestionType questionType) {
        questionTypeService.updateByPrimaryKeySelective(questionType);
        return Result.buildSucc(null);
    }

    @RequestMapping(value = "create")
    public Result create(HttpServletRequest httpRequest, QuestionType questionType) {
        questionTypeService.insertSelective(questionType);
        return Result.buildSucc(null);
    }

}
