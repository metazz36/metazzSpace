package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.model.dto.CategoryDTO;
import com.metazz.metazzspace.model.dto.PageDTO;
import com.metazz.metazzspace.model.entity.Category;
import com.metazz.metazzspace.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/category")
@Slf4j
@Api(value = "分类")
@SuppressWarnings("all")
public class CategoryController implements BaseController{

    @Autowired
    ICategoryService categoryService;

    @PostMapping("/add")
    @ApiOperation(value = "新增分类", httpMethod = "POST")
    public CR addCategory(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类：{}");
        categoryService.addCategory(categoryDTO);
        return success();
    }

    @GetMapping("/getAll")
    @ApiOperation(value = "查询所有分类", httpMethod = "GET")
    public CR getAllCategory(@RequestBody PageDTO pageDTO){
        return success(categoryService.getAllCategory(pageDTO));
    }

    @GetMapping("/get")
    @ApiOperation(value = "根据id查询分类", httpMethod = "GET")
    public CR getCategoryById(@RequestParam("id") String id){
        return success(categoryService.getCategoryById(id));
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "根据id删除分类", httpMethod = "DELETE")
    public CR deleteCategoryById(@RequestParam("id") String id){
        categoryService.deleteCategoryById(id);
        return success();
    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改分类", httpMethod = "PUT")
    public CR modifyCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.modifyCategory(categoryDTO);
        return success();
    }

}
