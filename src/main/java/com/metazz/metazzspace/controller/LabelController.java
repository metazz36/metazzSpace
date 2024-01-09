package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.annotation.SysLog;
import com.metazz.metazzspace.common.response.BaseController;
import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.model.dto.LabelAddDTO;
import com.metazz.metazzspace.model.dto.LabelModifyDTO;
import com.metazz.metazzspace.model.dto.PageQueryDTO;
import com.metazz.metazzspace.service.ILabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/label")
@Slf4j
@Api(value = "标签")
@SuppressWarnings("all")
public class LabelController implements BaseController {

    @Autowired
    ILabelService labelService;

    @PostMapping("/add")
    @SysLog(operateName = "新增标签")
    @ApiOperation(value = "新增标签", httpMethod = "POST")
    public CR addLabel(@RequestBody LabelAddDTO labelAddDTO){
        log.info("新增标签：{}", labelAddDTO);
        labelService.addLabel(labelAddDTO);
        return success();
    }

    @DeleteMapping("/delete")
    @SysLog(operateName = "根据id删除标签")
    @ApiOperation(value = "根据id删除标签", httpMethod = "DELETE")
    public CR deleteLabelById(@RequestParam("id") String id){
        labelService.deleteLabelById(id);
        return success();
    }

    @PutMapping("/modify")
    @SysLog(operateName = "修改标签")
    @ApiOperation(value = "修改标签", httpMethod = "PUT")
    public CR modifyLabel(@RequestBody LabelModifyDTO labelModifyDTO){
        labelService.modifyLabel(labelModifyDTO);
        return success();
    }

    @PostMapping("/getAll")
    @SysLog(operateName = "查询所有标签")
    @ApiOperation(value = "查询所有标签", httpMethod = "POST")
    public CR getAllLabel(@RequestBody PageQueryDTO pageQueryDTO){
        return success(labelService.getAllLabel(pageQueryDTO));
    }

    @GetMapping("/get")
    @SysLog(operateName = "根据id查询标签")
    @ApiOperation(value = "根据id查询标签", httpMethod = "GET")
    public CR getLabelById(@RequestParam("id") String id){
        return success(labelService.getLabelById(id));
    }

}
