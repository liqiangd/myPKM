package com.pkm.pkmDemo.minasocket;

import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class MyTextLineFactory implements ProtocolCodecFactory{
	
	 private ProtocolEncoder encoder;
	    private ProtocolDecoder decoder;

	    public MyTextLineFactory() {
	        encoder = new MyTextLineEncoder();
	        decoder = new MyCumulativeProtocolDecoder();
	    }

	    @Override
	    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
	        return encoder;
	    }

	    @Override
	    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
	        return decoder;
	    }
	}

class MyTextLineEncoder implements ProtocolEncoder {

	    @Override
	    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
	        // 编码格式
	        CharsetEncoder charsetEncoder = (CharsetEncoder) session.getAttribute("encoder");
	        if (charsetEncoder == null) {
	            charsetEncoder = Charset.defaultCharset().newEncoder();
	            session.setAttribute("encoder", charsetEncoder);
	        }
	        String value = (message == null ? "" : message.toString());
	        IoBuffer buffer = IoBuffer.allocate(value.length()).setAutoExpand(true);
	        buffer.putString(value, charsetEncoder);
	        buffer.flip();
	        out.write(buffer);
	    }

	    @Override
	    public void dispose(IoSession session) throws Exception {

	    }
	}

class MyCumulativeProtocolDecoder extends CumulativeProtocolDecoder {

	    @Override
	    protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
	        int startPosition = in.position();
	        while (in.hasRemaining()) {
	            byte b = in.get();
	            if (b == '\n') {
	                int currentPosition = in.position();
	                int limit = in.limit();
	                in.position(startPosition);
	                in.limit(currentPosition);
	                IoBuffer buffer = in.slice();
	                byte[] dest = new byte[buffer.limit()];
	                buffer.get(dest);
	                String str = new String(dest);
	                out.write(str);
	                in.position(currentPosition);
	                in.limit(limit);
	                return true;
	            }
	        }
	        in.position(startPosition);
	        return false;
	    }

	}