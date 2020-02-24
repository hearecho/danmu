package com.echo.danmu;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author hearecho
 * @createtime 2020/2/24 10:09
 */
public class WebsocketDanmuServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast("http-decodec",new HttpRequestDecoder());
        pipeline.addLast("http-aggregator",new HttpObjectAggregator(65536));
        pipeline.addLast("http-encodec",new HttpResponseEncoder());
//        pipeline.addLast("http-chunked",new ChunkedWriteHandler());

//		pipeline.addLast("http-request",new HttpRequestHandler("/ws"));
        pipeline.addLast("WebSocket-protocol",new WebSocketServerProtocolHandler("/ws"));
        pipeline.addLast("WebSocket-request",new TextWebSocketFrameHandler());
    }
}
