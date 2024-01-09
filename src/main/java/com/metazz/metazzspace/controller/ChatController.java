package com.metazz.metazzspace.controller;

import com.metazz.metazzspace.common.annotation.SysLog;
import com.metazz.metazzspace.common.response.BaseController;
import com.metazz.metazzspace.common.response.CR;
import com.metazz.metazzspace.model.dto.ChatAddDTO;
import com.metazz.metazzspace.model.dto.ChatModifyDTO;
import com.metazz.metazzspace.model.dto.ChatQueryDTO;
import com.metazz.metazzspace.service.IChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@Slf4j
@Api(value = "说说")
@SuppressWarnings("all")
public class ChatController implements BaseController {

    @Autowired
    IChatService chatService;

    @PostMapping("/add")
    @SysLog(operateName = "新增说说")
    @ApiOperation(value = "新增说说", httpMethod = "POST")
    public CR addChat(@RequestBody ChatAddDTO chatAddDTO){
        log.info("新增说说：{}", chatAddDTO);
        chatService.addChat(chatAddDTO);
        return success();
    }

    @PutMapping("/modify")
    @SysLog(operateName = "修改说说")
    @ApiOperation(value = "修改说说", httpMethod = "PUT")
    public CR modifyChat(@RequestBody ChatModifyDTO chatModifyDTO){
        log.info("修改说说：{}", chatModifyDTO);
        chatService.modifyChat(chatModifyDTO);
        return success();
    }

    @DeleteMapping("/delete")
    @SysLog(operateName = "删除说说")
    @ApiOperation(value = "删除说说", httpMethod = "DELETE")
    public CR deleteChat(@RequestParam("id") String id){
        log.info("删除说说：{}",id);
        chatService.deleteChat(id);
        return success();
    }

    @PostMapping("/getByPage")
    @SysLog(operateName = "分页查询说说信息")
    @ApiOperation(value = "分页查询说说信息", httpMethod = "POST")
    public CR getChatByPage(@RequestBody ChatQueryDTO chatQueryDTO){
        log.info("分页查询说说信息：{}",chatQueryDTO);
        return success(chatService.getChatByPage(chatQueryDTO));
    }

    @GetMapping("/get")
    @SysLog(operateName = "根据id查询说说")
    @ApiOperation(value = "根据id查询说说", httpMethod = "GET")
    public CR getChatById(@RequestParam("id") String id){
        log.info("根据id查询说说：{}",id);
        return success(chatService.getChatById(id));
    }

}
