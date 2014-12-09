package com.lefu.monitor.core.net;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ChannelEncoder extends MessageToByteEncoder<String> {

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out)
			throws Exception {
		if (msg == null || "".equals(msg)) {
			out.writeBytes("No Contents\n".getBytes());
		} else {
			out.writeBytes(msg.getBytes(Charset.forName("UTF-8")));
		}
	}
	
}
