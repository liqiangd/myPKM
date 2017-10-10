package com.pkm.pkmDemo.minasocket;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class MinaServer {

	public static void main(String[] args) {
		 try {
	            // 4步操作
	            // 1.新建 NioSocketAcceptor 事例对象
	            NioSocketAcceptor acceptor = new NioSocketAcceptor();
	            // 2.设置消息处理对象
	            acceptor.setHandler(new MyServerHandler());
	            // 3.设置消息解码器
	            acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyTextLineFactory()));// acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 3);
	            // //设置idle时长
	            // 4.绑定端口开启服务
	            acceptor.bind(new InetSocketAddress(9999));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}

}


/**
 * 专门处理消息的类,实现网络连接和消息处理的解耦
 */
class MyServerHandler extends IoHandlerAdapter {

    /** 异常时候的处理 */
    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        System.out.println("exceptionCaught");
    }

    /** 收到消息 */
    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        System.out.println("messageReceived " + message);
        // session.write("reply " + message); //收到消息就马上把消息回送给客户端
    }

    /** 发送消息 */
    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("messageSent " + message);
    }

    /** 通讯闲置时候的处理 */
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        System.out.println("sessionIdle");
    }
}