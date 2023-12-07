package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.response.BaseController;
import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.common.util.QiniuUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qiniu")
@Slf4j
@Api(value = "七牛云")
@SuppressWarnings("all")
public class QiniuController implements BaseController {

    @GetMapping("/getUpToken")
    @ApiOperation(value = "获取覆盖上传凭证", httpMethod = "GET")
    public CR getUpToken(@RequestParam(value = "key",required = false) String key){
        return success(QiniuUtil.getToken(key));
    }

    @GetMapping("/serverUpload")
    @ApiOperation(value = "服务器直传", httpMethod = "GET")
    public CR serverUpload(@RequestParam(value = "fileName",required = true) String fileName) {
        return success(QiniuUtil.serverUpload(fileName));
    }

}
