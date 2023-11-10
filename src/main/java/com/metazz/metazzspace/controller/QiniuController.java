package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.common.util.QiniuUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qiniu")
@Slf4j
@Api(value = "七牛云")
@SuppressWarnings("all")
public class QiniuController implements BaseController{

    public CR getUpToken(@RequestParam(value = "key",required = false) String key){
        return success(QiniuUtil.getToken(key));
    }

}
