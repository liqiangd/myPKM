package com.pkm.pkmDemo.singleton;


/**
 * 单例
 * */
public class Singleton {

	private Singleton() {
		System.out.println("singleton is created!");
	}
	
	private static Singleton instance=new Singleton();
	
	public static Singleton getInstance() {
		return instance;
	}
}
