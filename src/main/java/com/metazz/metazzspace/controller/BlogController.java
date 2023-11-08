package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.model.dto.BlogDTO;
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
public class BlogController implements BaseController{

    @Autowired
    IBlogService blogService;

    @PostMapping("/add")
    @ApiOperation(value = "新增博客", httpMethod = "POST")
    public CR addBlog(@RequestBody @Valid BlogDTO blogDTO){
        log.info("新增博客：{}",blogDTO);
        blogService.addBlog(blogDTO);
        return success();
    }

    @DeleteMapping("/delete")
    @ApiOperation(value = "删除博客", httpMethod = "DELETE")
    public CR deleteBlog(@RequestParam("id") String id){
        log.info("删除博客：{}",id);
        blogService.deleteBlog(id);
        return success();
    }

    @GetMapping("/get")
    @ApiOperation(value = "根据id查询博客", httpMethod = "GET")
    public CR getBlogById(@RequestParam("id") String id){
        Blog blog = blogService.getBlogById(id);
        return success();
    }

}
