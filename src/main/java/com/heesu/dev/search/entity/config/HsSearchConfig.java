package com.heesu.dev.search.entity.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("config")
@Setter
@Getter
public class HsSearchConfig {

    private String indexPath;

}
