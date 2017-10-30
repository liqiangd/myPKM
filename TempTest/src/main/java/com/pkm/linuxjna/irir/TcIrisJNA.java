package com.pkm.linuxjna.irir;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.PointerByReference;
//import com.teso.drivers.BioDriver;
//import com.teso.drivers.BioLibFinder;

public class TcIrisJNA {

	private static final Logger LOG = Logger.getLogger(TcIrisJNA.class.getName());
	private static boolean LOAD_SUCCESS = false;
	private String libName;

	// JNA接口
	private interface TesoIrisAlg extends Library {

		/**
		 *
		 * @param nType
		 *            0=本库版本，1=算法版本，2=模板版本，3=in 授权，4=out 信息。
		 * @param content
		 *            [in/out]：按 nType 值，获取或设置字符串。
		 * @return 成功时返回模板的最大字节长度 TPL_SIZ，<0 失败。
		 */
		public int SsMobiVersn(int nType, byte[] content);

		/**
		 *
		 * @param handle
		 *            [in,out] 环境句柄地址
		 * @param width
		 *            [in] 目标图宽
		 * @param height
		 *            [in] 目标图高
		 * @param nTcnt
		 *            [in] -2 特征，-1 仅采集，0 注册，>0 验证 已经注册的模板个数
		 * @param tmpls
		 *            [in] 连续多个模板 模板数据首地址，采集/注册时为 NULL。 验证时，为 nTcnt 个（最大模板）的连续内存区，
		 *            此内存由外部维护，库内仅简单指向而已，任务过程中须有效。
		 * @param optCfg
		 *            [in,out] 指向 StV4hInit 结构体，用于初始化。为 NULL 时则用默认参数。
		 * @return >=0 成功，<0 失败。
		 */
		public int SsMobiDinit(PointerByReference handle, int width, int height, int nTcnt, byte[] tmpls,
				IntByReference optCfg);

		/**
		 * 提取特征
		 * 
		 * @param
		 * @param
		 * @param
		 * @param
		 * @param
		 * @author liqiang
		 */
		public int SsMobipFea(byte[] feature, int featureSize, byte[] image, int width, int height,
				double imageCompressRate, int hint);

		/**
		 * 两个特征比对
		 * 
		 * @param feature
		 * @param tmpl
		 * @param level
		 * @param sameCheck
		 * @return -7： 特征格式不对； >=0, 比对分数，阈值 600 分。
		 */
		public int SsMobiComp(byte[] feature, byte[] tmpl, int level, int sameCheck);

		/**
		 * @param 环境句柄地址
		 */
		public int SsMobiDexit(PointerByReference handle);
	}

	private static TesoIrisAlg tesoIrisAlg;

	// @Override
	public byte[] getFeatureByImg(byte[] image, int arg1, int arg2, int arg3, int arg4, int arg5) {

		byte[] feature = new byte[5664];
		int res = tesoIrisAlg.SsMobipFea(feature, 5664, image, 640, 480, 1.0, 2);
		LOG.log(Level.INFO, "get feature result is [{0}]", res);
		if (res > 0 && feature != null) {
			return feature;
		}
		return null;
	}

	/**
	 * 获取当前库
	 */
	// @Override
	public String getLibBaseName() {
		return this.libName;
	}

	// @Override
	public String getLibVersion() {
		return null;
	}

	// @Override
	public boolean init() {
		if (LOAD_SUCCESS) {
			return true;
		}
		try {
			// File path = BioLibFinder.findLib(getLibBaseName()); // .getCanonicalPath();
			LOG.log(Level.INFO, "try load iris lib from [{0}]", TcIrisJNA.class.getResource("").getPath());
			tesoIrisAlg = (TesoIrisAlg) Native.loadLibrary(
					TcIrisJNA.class.getResource("").getPath() + File.separator + "libSsLy.so", TesoIrisAlg.class);
		} catch (Throwable e) {
			LOG.log(Level.SEVERE, e.getMessage(), e);
		}
		LOAD_SUCCESS = tesoIrisAlg != null;
		LOG.log(Level.INFO, " init  tesoIrisAlg  result=[{0}]", LOAD_SUCCESS);
		return LOAD_SUCCESS;
	}

	/**
	 * @param 特征1
	 * @param 模板
	 * @return score 分值
	 */
	// @Override
	public int matchdebug(byte[] feature, byte[] tmpl) {
		int score = tesoIrisAlg.SsMobiComp(feature, tmpl, 0, 0);
		return score;
	}

	public static void main(String[] args) {
		TcIrisJNA jna = new TcIrisJNA();
		boolean init = jna.init();
		System.out.println("init=" + init);
		byte[] image1 = null;
		try {
			image1 = FileUtils.readFileToByteArray(
					new File(TcIrisJNA.class.getResource("").getPath() + File.separator + "0514_1.bmp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] feature1 = jna.getFeatureByImg(image1, 0, 0, 0, 0, 0);
		System.out.println(image1.length);

		byte[] image2 = null;
		try {
			image2 = FileUtils.readFileToByteArray(
					new File(TcIrisJNA.class.getResource("").getPath() + File.separator + "0514_2.bmp"));
		} catch (IOException e) {
//			e.printStackTrace();
		}

		byte[] feature2 = jna.getFeatureByImg(image2, 0, 0, 0, 0, 0);
		System.out.println(image1.length);

		int score = jna.matchdebug(feature1, feature2);

		System.out.println("score=" + score);
	}

}
