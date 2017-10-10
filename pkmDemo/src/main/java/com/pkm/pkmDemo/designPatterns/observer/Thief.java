package com.pkm.pkmDemo.designPatterns.observer;

public class Thief implements Watcher {

	@Override
	public void update() {
      System.out.println("运输车有行动，准备动手抢劫！");
	}

}
