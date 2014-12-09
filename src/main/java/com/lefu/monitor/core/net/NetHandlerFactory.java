package com.lefu.monitor.core.net;

import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelOutboundHandler;

import com.lefu.remote.netty.ChannelHandlerFactoryAdapter;

public class NetHandlerFactory extends ChannelHandlerFactoryAdapter {
	private final MonitorCache cache;
	
	public NetHandlerFactory(MonitorCache cache) {
		this.cache = cache;
	}
	
	@Override
	public ChannelInboundHandler newInstance() {
		NetHandler handler = new NetHandler();
		handler.setChannelHandlerFactory(this);
		handler.setCache(cache);
		return handler;
	}
	
	@Override
	public ChannelInboundHandler newDecoder() {
		return new ChannelDecoder();
	}
	
	@Override
	public ChannelOutboundHandler newEncoder() {
		return new ChannelEncoder();
	}

}
