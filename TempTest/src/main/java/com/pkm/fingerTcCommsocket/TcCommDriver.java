package com.pkm.fingerTcCommsocket;

import java.io.File;
import java.io.UnsupportedEncodingException;

import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class TcCommDriver {

	private interface TcComm extends Library {

		TcComm library = (TcComm) Native.loadLibrary(TcComm.class.getResource("").getPath()+File.separator+"libTcComm_lix64.so", TcComm.class);

		/**
		 * 指纹比对
		 * 
		 * @param IP
		 *            IP地址
		 * @param port端口
		 * @param InstNo机构号
		 * @param TlrNo柜员号
		 * @param FingerBase64
		 *            指纹特征Base64
		 * @author liqiang
		 */
		public int FPIMatch(String IP, String port, String InstNo, String TlrNo, String FingerBase64, byte[] errorMsg);
	}

	// private static TcComm tcComm;
	/**
	 * 指纹比对
	 * 
	 * @param IP
	 *            IP地址
	 * @param port端口
	 * @param InstNo机构号
	 * @param TlrNo柜员号
	 * @param FingerBase64
	 *            指纹特征Base64
	 * @author liqiang
	 */
	public TcResult FPIMatch(String IP, String port, String InstNo, String TlrNo, String FingerBase64) {
		byte[] resultv=new byte[128];
		int res = -99;
		try {
			res = TcComm.library.FPIMatch(IP, port, InstNo, TlrNo, FingerBase64, resultv);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		TcResult tcResult = new TcResult();
		tcResult.setErrorCode(res);
		try {
			tcResult.setErrorMsg(new String(resultv, "GBK"));
		} catch (UnsupportedEncodingException e) {
			System.out.println("exception:"+e.getMessage());
		}
		return tcResult;

	}
}
