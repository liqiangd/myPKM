package com.pkm.pkmDemo.designPatterns.observer;

//保镖
public class Security implements Watcher {

	@Override
	public void update() {
		 System.out.println("输车有行动，保安贴身保护");
	}

}
