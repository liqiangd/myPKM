package com.pkm.pkmDemo.singleton;


/**
 * 单例,使用内部类维护单例
 * */
public class StaticSingleton {
  private StaticSingleton() {
	  System.out.println("static singleton is created!");
  }
  
  private static class SingletonHolder{
	private static  StaticSingleton instance=new StaticSingleton();
  }
  
  public static StaticSingleton getIntance() {
	  return SingletonHolder.instance;
  }
}
