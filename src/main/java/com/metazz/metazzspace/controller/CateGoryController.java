package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.response.CR;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@Slf4j
@Api(value = "分类")
@SuppressWarnings("all")
public class CateGoryController implements BaseController{

    @PostMapping("/add")
    @ApiOperation(value = "新增分类", httpMethod = "POST")
    public CR addCategory(){
        log.info("新增分类：{}");
        return success();
    }
}
