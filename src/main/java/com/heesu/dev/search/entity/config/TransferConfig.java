package com.heesu.dev.search.entity.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransferConfig {
    private String protocol;
    private String host;
    private int port;
}
