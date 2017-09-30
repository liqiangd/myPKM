package com.pkm.pkmDemo.designPatterns.adapter;

/**适配器的测试*/
public class TestAdapter {

	public static void main(String[] args) {
		
		   GBSocketInterface gbSocket = new GBSocket();  
         
	        Hotel hotel = new Hotel();  
	          
	        SocketAdapter socketAdapter = new SocketAdapter(gbSocket);  
	          
	        hotel.setSocket(socketAdapter);  
	          
	        hotel.charge();  
	}

}
