package com.metazz.metazzspace.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "qiniu")
public class QiNiuProperties {

    private String access_key;

    private String secret_key;

    private String bucket;

    private String download_url;

}
