package com.pkm.pkmDemo.designPatterns.proxy;

public class TestProxy {

	public static void main(String[] args) {
//		//简单实现，也可以客户直接买车
//		 Customer customer=new Customer();
//		  customer.setCash(120000);
//		  BuyCarProxy buyCarProxy=new BuyCarProxy(customer);
//		  buyCarProxy.buyCar();
		  
		  
		  Customer customer2=new Customer();
		  customer2.setCash(120000);
		  BuyCarProxy buyCarProxy2=new BuyCarProxy(customer2);
		  buyCarProxy2.buyCar();

		  Customer customer3 =new Customer();
		  customer3.setCash(90000);
		  BuyCarProxy buyCarProxy3 =new BuyCarProxy(customer3);
		  buyCarProxy3.buyCar();
	}

}
