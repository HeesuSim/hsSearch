package com.heesu.dev.search.transfer;

import io.netty.bootstrap.Bootstrap;

public class BootstrapBuilder{

    private TransferProtocol protocol;
    private String host;
    private int port;



    public static class Builder {

        Bootstrap bootstrap = new Bootstrap();

    }
}
