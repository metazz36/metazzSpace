package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.annotation.LoginCheck;
import com.metazz.metazzspace.common.annotation.SysLog;
import com.metazz.metazzspace.common.response.BaseController;
import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.model.dto.CategoryAddDTO;
import com.metazz.metazzspace.model.dto.CategoryModifyDTO;
import com.metazz.metazzspace.model.dto.PageQueryDTO;
import com.metazz.metazzspace.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@Slf4j
@Api(value = "分类")
@SuppressWarnings("all")
public class CategoryController implements BaseController {

    @Autowired
    ICategoryService categoryService;

    @PostMapping("/add")
    @SysLog(operateName = "新增分类")
    @LoginCheck(useType = "1")
    @ApiOperation(value = "新增分类", httpMethod = "POST")
    public CR addCategory(@RequestBody CategoryAddDTO categoryAddDTO){
        log.info("新增分类：{}", categoryAddDTO);
        categoryService.addCategory(categoryAddDTO);
        return success();
    }

    @DeleteMapping("/delete")
    @SysLog(operateName = "根据id删除分类")
    @LoginCheck(useType = "1")
    @ApiOperation(value = "根据id删除分类", httpMethod = "DELETE")
    public CR deleteCategoryById(@RequestParam("id") String id){
        log.info("根据id删除分类：{}",id);
        categoryService.deleteCategoryById(id);
        return success();
    }

    @PutMapping("/modify")
    @SysLog(operateName = "修改分类")
    @LoginCheck(useType = "1")
    @ApiOperation(value = "修改分类", httpMethod = "PUT")
    public CR modifyCategory(@RequestBody CategoryModifyDTO categoryModifyDTO){
        log.info("修改分类：{}", categoryModifyDTO);
        categoryService.modifyCategory(categoryModifyDTO);
        return success();
    }

    @PostMapping("/getAll")
    @SysLog(operateName = "查询所有分类")
    @ApiOperation(value = "查询所有分类", httpMethod = "POST")
    public CR getAllCategory(@RequestBody PageQueryDTO pageQueryDTO){
        return success(categoryService.getAllCategory(pageQueryDTO));
    }

    @GetMapping("/get")
    @SysLog(operateName = "根据id查询分类")
    @ApiOperation(value = "根据id查询分类", httpMethod = "GET")
    public CR getCategoryById(@RequestParam("id") String id){
        return success(categoryService.getCategoryById(id));
    }

}
