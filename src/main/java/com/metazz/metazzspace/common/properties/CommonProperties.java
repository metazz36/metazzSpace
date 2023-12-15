package com.metazz.metazzspace.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "metazz.space")
public class CommonProperties {

    /**
     * 博客应用状态
     */
    private Boolean status;

}
