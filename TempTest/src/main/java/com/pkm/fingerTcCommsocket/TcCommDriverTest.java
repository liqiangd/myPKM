package com.pkm.fingerTcCommsocket;

public class TcCommDriverTest {

	/**
	 * errorCode >0 通过，否则不通过.
	 * errorMsg 中文提示信息
	 * */
	public static void main(String[] args) {
		TcCommDriver driver=new TcCommDriver();
		TcResult result=driver.FPIMatch("192.168.0.220", "6000", "37000090", "20080731650", "EgEudRXCEADGF50WgCsr0jiAgSy/EIClPqcOAM1DSw8AVUfWFoAhVesUAOhYjS4AilynFADCXJASAEZn4RoAWWjcGIB0bcscgDNyOA4A3XU7EwDRfn0SAKGBeWgAj5BsOoCClEsSAMKUcR6AqpppIoBunD9ggO2gdhKAcKo5NIAgsvgggEi67CaAFsTEHgC/x08UgOvIZhaALszSjICN2DYeALHjRAAAzepIbgCOpvwSgJipSjSAarbyGIBhtPAagK3ETBSAoNBGDICqz/0AgKfaRmKAOME3GoAvvOY0gL7h/QQAwOg8GoA=");
		System.out.println("errorCode="+result.getErrorCode());
		System.out.println("errorMsg="+result.getErrorMsg());
	}

}
