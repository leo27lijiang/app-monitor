package com.lefu.monitor.core.net;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;

import com.lefu.remote.netty.BlockingReadHandlerAdapter;

public class NetHandler extends BlockingReadHandlerAdapter {
	private MonitorCache cache;
	
	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(cache.getContent()).addListener(ChannelFutureListener.CLOSE);
	}
	
	@Override
	protected void doRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		//do nothing
	}
	
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
		cause.printStackTrace();
		ctx.close().addListener(ChannelFutureListener.CLOSE);
    }

	public void setCache(MonitorCache cache) {
		this.cache = cache;
	}
	
}
