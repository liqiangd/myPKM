package com.pkm.pkmDemo.designPatterns.observer;

/**
 * 抽象的被观察者
 * */
public interface Watched {
	
	//添加
	 public void addWatcher(Watcher watcher);  
	 //移除  
     public void removeWatcher(Watcher watcher);  
     //通知
     public void notifyWatchers();  
}
