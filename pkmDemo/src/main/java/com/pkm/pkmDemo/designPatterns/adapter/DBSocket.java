package com.pkm.pkmDemo.designPatterns.adapter;

public class DBSocket implements DBSocketInterface {

	@Override
	public void powerWithTwoRound() {
       System.out.println("使用德标的两插口充电器充电！");
	}

}
