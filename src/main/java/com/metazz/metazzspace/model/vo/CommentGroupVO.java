package com.metazz.metazzspace.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@Data
@ApiModel(value = "评论组VO（包含父评论和子评论集合）")
public class CommentGroupVO {

    /**
     * 父评论
     */
    @ApiModelProperty(value = "父评论")
    CommentVO fatherComment;

    /**
     * 子评论集合
     */
    @ApiModelProperty(value = "子评论集合")
    List<CommentVO> childComments;

}
