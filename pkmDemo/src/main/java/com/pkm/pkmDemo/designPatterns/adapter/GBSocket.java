package com.pkm.pkmDemo.designPatterns.adapter;

public class GBSocket implements GBSocketInterface {

	@Override
	public void powerWithThreeFlat() {
           System.out.println("使用国标的三插头充电器充电!");
	}

}
