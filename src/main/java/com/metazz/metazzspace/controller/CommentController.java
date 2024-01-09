package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.annotation.LoginCheck;
import com.metazz.metazzspace.common.annotation.SysLog;
import com.metazz.metazzspace.common.response.BaseController;
import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.model.dto.CommentAddDTO;
import com.metazz.metazzspace.model.dto.CommentQueryDTO;
import com.metazz.metazzspace.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
@Slf4j
@Api(value = "评论")
@SuppressWarnings("all")
public class CommentController implements BaseController {

    @Autowired
    ICommentService commentService;

    @PostMapping("/add")
    @SysLog(operateName = "发表评论")
    @ApiOperation(value = "发表评论", httpMethod = "POST")
    @LoginCheck
    public CR addComment(@RequestBody @Valid CommentAddDTO commentAddDTO){
        log.info("发表评论:{}",commentAddDTO);
        commentService.addComment(commentAddDTO);
        return success();
    }

    @PostMapping("/query")
    @SysLog(operateName = "查询评论")
    @ApiOperation(value = "查询评论", httpMethod = "POST")
    public CR queryComment(@RequestBody @Valid CommentQueryDTO commentQueryDTO){
        return success(commentService.queryComment(commentQueryDTO));
    }

    @PostMapping("/queryCount")
    @SysLog(operateName = "查询评论数量")
    @ApiOperation(value = "查询评论数量", httpMethod = "POST")
    public CR queryCount(@RequestBody @Valid CommentQueryDTO commentQueryDTO){
        return success(commentService.queryCount(commentQueryDTO));
    }

    @DeleteMapping("/delete")
    @SysLog(operateName = "根据id删除评论")
    @ApiOperation(value = "根据id删除评论", httpMethod = "DELETE")
    public CR deleteComment(@RequestParam("id") String id){
        log.info("根据id删除评论:{}",id);
        commentService.deleteComment(id);
        return success();
    }

}
