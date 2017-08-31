package com.pkm.pkmDemo.datastructure;



/**
 * 泛型,来自java数据结构与算法
 * */
public class MemoryCell {

	private Object storedValue;
	
	public Object read() {
		return storedValue;
	}
	
	public void write(Object x) {
		storedValue=x;	
	}
	public static void main(String[] args) {

	}

}
