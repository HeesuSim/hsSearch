package com.heesu.dev.search.transfer.handler;

import com.heesu.dev.search.core.indexer.parser.CsvIndexParser;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TcpInboundHandler extends SimpleChannelInboundHandler<ByteBuf> {
    private Logger logger = LoggerFactory.getLogger(TcpInboundHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        logger.info("#### Server response after sending TCP request : " + byteBuf);
    }
}
