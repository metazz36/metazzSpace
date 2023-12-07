package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.annotation.LoginCheck;
import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.model.dto.CommentAddDTO;
import com.metazz.metazzspace.model.dto.CommentQueryDTO;
import com.metazz.metazzspace.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
@Slf4j
@Api(value = "评论")
@SuppressWarnings("all")
public class CommentController implements BaseController{

    @Autowired
    ICommentService commentService;

    @PostMapping("/add")
    @ApiOperation(value = "发表评论", httpMethod = "POST")
    @LoginCheck
    public CR addComment(@RequestBody @Valid CommentAddDTO commentAddDTO){
        commentService.addComment(commentAddDTO);
        return success();
    }

    @PostMapping("/query")
    @ApiOperation(value = "查询评论", httpMethod = "POST")
    public CR queryComment(@RequestBody @Valid CommentQueryDTO commentQueryDTO){
        return success(commentService.queryComment(commentQueryDTO));
    }

    @PostMapping("/queryCount")
    @ApiOperation(value = "查询评论数量", httpMethod = "POST")
    public CR queryCount(@RequestBody @Valid CommentQueryDTO commentQueryDTO){
        return success(commentService.queryCount(commentQueryDTO));
    }

}
