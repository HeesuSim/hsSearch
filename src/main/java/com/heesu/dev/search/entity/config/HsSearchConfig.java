package com.heesu.dev.search.entity.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("config")
@Setter
@Getter
@ToString
public class HsSearchConfig {

    private String indexPath;
    @NestedConfigurationProperty
    private TransferConfig transfer;

}
