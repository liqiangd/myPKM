package com.pkm.pkmDemo.designPatterns.adapter;

/**德国的宾馆*/
public class Hotel {
	  //旅馆中有一个德标的插口  
    private DBSocketInterface dbSocket;
    
    public Hotel(){}
    
    public Hotel(DBSocketInterface dbSocket) {  
        this.dbSocket = dbSocket;  
    }  
  
    public void setSocket (DBSocketInterface dbSocket){  
        this.dbSocket = dbSocket;  
    }  
    
    /**充电*/
    public void charge() {
    	dbSocket.powerWithTwoRound();
    }
}
