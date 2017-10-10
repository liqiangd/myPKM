package com.pkm.pkmDemo.designPatterns.adapter;


/**
 * 适配器
 * */
public class SocketAdapter implements DBSocketInterface {


    //组合国标新接口  
    private GBSocketInterface gbSocket; 
    
    /** 
     * 在创建适配器对象时，必须传入一个新接口的实现类 
     * @param gbSocket 
     */  
    public SocketAdapter(GBSocketInterface gbSocket) {  
        this.gbSocket = gbSocket;  
    }  
  
    /***/
	@Override
	public void powerWithTwoRound() {
		gbSocket.powerWithThreeFlat();
	}

}
