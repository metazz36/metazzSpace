package com.metazz.metazzspace.service.xxljob;

import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class XxlJobService {

    @XxlJob("test")
    public void test(){
        System.out.println("Test XXL-JOB SUCCESS!!");
    }

}
