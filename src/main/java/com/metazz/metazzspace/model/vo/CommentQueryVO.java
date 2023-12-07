package com.metazz.metazzspace.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@ApiModel(value = "查询评论VO")
@AllArgsConstructor
public class CommentQueryVO {

    /**
     * 评论组集合
     */
    @ApiModelProperty(value = "评论组集合")
    List<CommentGroupVO> commentGroupVOS;

}
