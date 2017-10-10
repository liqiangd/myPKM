package com.pkm.pkmDemo.designPatterns.proxy;

public class BuyCarProxy implements IBuyCar {
	
	private Customer customer;//接收买车客户

    public BuyCarProxy(Customer customer){
        this.customer=customer;//接收买车客户
    }

    @Override
    public void buyCar() {//实现为客户买车
    	//简单实现
//        customer.buyCar();
    	//做审核处理
    	 int cash=customer.getCash();
         if(cash<100000){
             System.out.println("buyCar"+"你的钱不够买一辆车");
             return;
         }
         customer.buyCar();
    }

}
