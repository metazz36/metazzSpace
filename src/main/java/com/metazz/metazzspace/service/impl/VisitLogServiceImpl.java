package com.metazz.metazzspace.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.metazz.metazzspace.entity.VisitLog;
import com.metazz.metazzspace.mapper.VisitLogMapper;
import com.metazz.metazzspace.service.IVisitLogService;
import org.springframework.stereotype.Service;

@Service
public class VisitLogServiceImpl extends ServiceImpl<VisitLogMapper, VisitLog> implements IVisitLogService {

}
