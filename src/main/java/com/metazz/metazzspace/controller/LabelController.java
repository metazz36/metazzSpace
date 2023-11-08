package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.dto.LabelDTO;
import com.metazz.metazzspace.entity.Category;
import com.metazz.metazzspace.entity.Label;
import com.metazz.metazzspace.service.ILabelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/label")
@Slf4j
@Api(value = "标签")
@SuppressWarnings("all")
public class LabelController implements BaseController{

    @Autowired
    ILabelService labelService;

    @PostMapping("/add")
    @ApiOperation(value = "新增标签", httpMethod = "POST")
    public CR addLabel(@RequestBody LabelDTO labelDTO){
        log.info("新增标签：{}");
        labelService.addLabel(labelDTO);
        return success();
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "查询所有标签", httpMethod = "GET")
    public CR getAllLabel(){
        List<Label> data = labelService.getAllLabel();
        return success(data);
    }

    @GetMapping("/get")
    @ApiOperation(value = "根据id查询标签", httpMethod = "GET")
    public CR getLabelById(@RequestParam("id") String id){
        return success(labelService.getLabelById(id));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "根据id删除标签", httpMethod = "DELETE")
    public CR deleteLabelById(@RequestParam("id") String id){
        labelService.deleteLabelById(id);
        return success();
    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改标签", httpMethod = "PUT")
    public CR modifyLabel(@RequestBody LabelDTO labelDTO){
        labelService.modifyLabel(labelDTO);
        return success();
    }

}
