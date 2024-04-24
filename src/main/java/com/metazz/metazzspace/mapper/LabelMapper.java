package com.metazz.metazzspace.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.metazz.metazzspace.model.entity.Label;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelMapper extends BaseMapper<Label> {

    void incrCount(@Param("id") Integer labelId);

    void decrCount(@Param("id") Integer labelId);

}