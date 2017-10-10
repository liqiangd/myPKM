package com.pkm.pkmDemo;

public class Test {

	public static int ret() {
		int flag=0;
		try {
			flag=1;
			System.out.println("ret");		
			return flag;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			System.out.println("finally!");
		}
		return flag;
	}
	public static void main(String[] args) {
       ret();
	}

}
