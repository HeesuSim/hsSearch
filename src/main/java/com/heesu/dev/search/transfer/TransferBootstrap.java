package com.heesu.dev.search.transfer;

import com.heesu.dev.search.transfer.handler.TcpInboundHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class TransferBootstrap extends Bootstrap{

    private Logger logger = LoggerFactory.getLogger(TransferBootstrap.class);

    private TransferProtocol protocol;
    private String host;
    private int port;

    private TransferBootstrap(Builder b) {
        this.protocol = b.protocol;
        this.host = b.host;
        this.port = b.port;

    }

    private void init() {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            group(group);
            channel(getChannel());
            remoteAddress(new InetSocketAddress(this.host, this.port));
            handler(ChannelInitHandler());

            ChannelFuture f = connect().sync();
            logger.info("#### ready to send TCP request");
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            logger.error("#### error while creating bootstrap");
            e.printStackTrace();

        } finally {
            group.shutdownGracefully();
        }
    }

    private ChannelInitializer ChannelInitHandler() {
        ChannelInitializer initializer = null;
        if(this.protocol.equals(TransferProtocol.TCP)) {
            initializer = new ChannelInitializer() {
                @Override
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(new TcpInboundHandler());
                }
            };
        }
        return initializer;
    }

    private Class getChannel() {
        Class channel;
        if(this.protocol.equals(TransferProtocol.TCP)) {
            channel = NioSocketChannel.class;
        } else if(this.protocol.equals(TransferProtocol.UDP)) {
            channel = DatagramChannel.class;
        } else {
            // temp else. tls will be configured.
            channel = NioSocketChannel.class;
        }

        return channel;
    }

    public static class Builder {

        private TransferProtocol protocol;
        private String host;
        private int port;

        public Builder protocol(TransferProtocol protocol) {
            this.protocol = protocol;
            return this;
        }

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public TransferBootstrap build() {
            return new TransferBootstrap(this);
        }

    }
}
