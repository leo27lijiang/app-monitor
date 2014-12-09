package com.lefu.monitor.core.net;

import java.nio.charset.Charset;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class ChannelDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		if (in.readableBytes() > 0) {
			byte[] data = new byte[in.readableBytes()];
			in.readBytes(data);
			out.add(new String(data, Charset.forName("UTF-8")));
		}
	}

}
