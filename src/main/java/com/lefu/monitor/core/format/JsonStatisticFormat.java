package com.lefu.monitor.core.format;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.lefu.monitor.Pair;
import com.lefu.monitor.StatisticSet;

/**
 * JSON格式转换
 * @author jiang.li
 *
 */
public class JsonStatisticFormat implements StatisticFormat {
	private final static int DEFAULT_BUFFER_SIZE = 1024;
	private JsonFactory f = new JsonFactory();
	
	@Override
	public String format(StatisticSet set) {
		if (set == null) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream(DEFAULT_BUFFER_SIZE);
		try {
			JsonGenerator g = f.createGenerator(out);
			g.writeStartObject();
			g.writeStringField("name", set.getName());
			g.writeStringField("timestamp", set.getTimestamp());
			g.writeObjectFieldStart("datapoint");
			for (Pair p : set.getDatapoints()) {
				g.writeFieldName(p.getKey());
				g.writeObject(p.getValue());
			}
			g.writeEndObject();
			g.writeEndObject();
			g.close();
			return new String(out.toByteArray(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String format(List<StatisticSet> sets) {
		if (sets == null) {
			return null;
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream(DEFAULT_BUFFER_SIZE);
		try {
			JsonGenerator g = f.createGenerator(out);
			g.writeStartArray();
			for (StatisticSet set : sets) {
				g.writeStartObject();
				g.writeStringField("name", set.getName());
				g.writeStringField("timestamp", set.getTimestamp());
				g.writeObjectFieldStart("datapoint");
				for (Pair p : set.getDatapoints()) {
					g.writeFieldName(p.getKey());
					g.writeObject(p.getValue());
				}
				g.writeEndObject();
				g.writeEndObject();
			}
			g.writeEndArray();
			g.close();
			return new String(out.toByteArray(), Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
