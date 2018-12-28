package com.cheguo.niudun.console.server.controller.merchantservice;

import com.cheguo.basic.model.DataPageBo;
import com.cheguo.commons.rpc.lang.Result;
import com.cheguo.other.model.Question;
import com.cheguo.other.service.IQuestionService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


@RestController
@RequestMapping("/merchantService/question")
public class QuestionController {
    Logger logger = Logger.getLogger(QuestionController.class);

    @Resource
    private IQuestionService questionService;


    @RequestMapping(value = "/list")
    public Result list(HttpServletRequest httpRequest,
            @RequestParam(value = "application", required = false) String application,
            @RequestParam(value = "questionTypeId", required = false) Integer questionTypeId,
            @RequestParam(value = "questionNameLIke", required = false) String questionNameLIke,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "isNormalQuestion", required = false) String isNormalQuestion,
            @RequestParam(value = "currentPage", required = true) Integer currentPage,
            @RequestParam(value = "pageSize", required = true) Integer pageSize
    ) {
        HashMap<String, Object> params = new HashMap<>();
        if(StringUtils.isNotBlank(application)){
            params.put("application", application);
        }
        if(questionTypeId != null){
            params.put("questionTypeId", questionTypeId);
        }
        if(StringUtils.isNotBlank(questionNameLIke)){
            params.put("questionNameLIke", questionNameLIke);
        }
        if(StringUtils.isNotBlank(status)){
            params.put("status", status);
        }
        if(StringUtils.isNotBlank(isNormalQuestion)){
            params.put("isNormalQuestion", isNormalQuestion);
        }
        if(currentPage == null){
            currentPage = 0;
        }else if(currentPage >= 1){
            currentPage = currentPage - 1;
        }
        if(pageSize == null){
            pageSize = 10;
        }
        params.put("orderBy", "create_time");
        params.put("orderType", "asc");
        DataPageBo<Question> pageList = questionService.getList(params, currentPage, pageSize);
        return Result.buildSucc(pageList);
    }

    @RequestMapping(value = "/item")
    public Result item(Integer questionId){
        Question question = questionService.selectByPrimaryKey(questionId);
        return Result.buildSucc(question);
    }

    @RequestMapping(value = "update")
    public Result update(HttpServletRequest httpRequest, Question question) {
        questionService.updateByPrimaryKeySelective(question);
        return Result.buildSucc(null);
    }

    @RequestMapping(value = "create")
    public Result create(HttpServletRequest httpRequest, Question question) {
        return Result.buildSucc(null);
    }

}
