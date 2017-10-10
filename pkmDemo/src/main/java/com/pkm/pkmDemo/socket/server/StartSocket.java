package com.pkm.pkmDemo.socket.server;

public class StartSocket {

	public static void main(String[] args) {
		new MultiThreadServer().service();//这种方式总是socket reset
//               new SocketServer().service();//这个方法执行2s
	}

}
