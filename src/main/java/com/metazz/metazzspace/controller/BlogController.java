package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.annotation.SysLog;
import com.metazz.metazzspace.common.response.BaseController;
import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.model.dto.BlogAddDTO;
import com.metazz.metazzspace.model.dto.BlogModifyDTO;
import com.metazz.metazzspace.model.dto.BlogQueryDTO;
import com.metazz.metazzspace.model.entity.Blog;
import com.metazz.metazzspace.service.IBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/blog")
@Slf4j
@Api(value = "博客")
@SuppressWarnings("all")
public class BlogController implements BaseController {

    @Autowired
    IBlogService blogService;

    @PostMapping("/add")
    @SysLog(operateName = "新增博客")
    @ApiOperation(value = "新增博客", httpMethod = "POST")
    public CR addBlog(@RequestBody @Valid BlogAddDTO blogAddDTO){
        log.info("新增博客：{}", blogAddDTO);
        blogService.addBlog(blogAddDTO);
        return success();
    }

    @DeleteMapping("/delete")
    @SysLog(operateName = "删除博客")
    @ApiOperation(value = "删除博客", httpMethod = "DELETE")
    public CR deleteBlog(@RequestParam("id") String id){
        log.info("删除博客：{}",id);
        blogService.deleteBlog(id);
        return success();
    }

    @PutMapping("/modify")
    @SysLog(operateName = "修改博客")
    @ApiOperation(value = "修改博客", httpMethod = "PUT")
    public CR modifyBlog(@RequestBody @Valid BlogModifyDTO blogModifyDTO){
        log.info("修改博客：{}", blogModifyDTO);
        blogService.modifyBlog(blogModifyDTO);
        return success();
    }

    @PostMapping("/getByPage")
    @SysLog(operateName = "分页查询博客信息")
    @ApiOperation(value = "分页查询博客信息", httpMethod = "POST")
    public CR getBlogByPage(@RequestBody BlogQueryDTO blogQueryDTO){
        return success(blogService.getBlogByPage(blogQueryDTO));
    }

    @GetMapping("/get")
    @SysLog(operateName = "根据id查询博客")
    @ApiOperation(value = "根据id查询博客", httpMethod = "GET")
    public CR getBlogById(@RequestParam("id") String id){
        Blog blog = blogService.getBlogById(id);
        return success(blog);
    }

}
