package com.pkm.pkmDemo.socket.client;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.Reader;
import java.net.Socket;
import java.text.DecimalFormat;

import javax.xml.bind.DatatypeConverter;

public class SocketClient {

    /**
     * 图片转化为base64字符串
     * 
     * @param photoPath
     *            图片路径
     * 
     * @return base64String
     * */
    public static String imageToBase64(String photoPath) {

        File img2 = new File(photoPath);
        return fileToBase64(img2);
    }
    public static String fileToBase64(File img2) {
        byte[] res2 = new byte[(int) img2.length()];
        int totalBytesRead2 = 0;
        String b64PersonImage = null;

        try {
            BufferedInputStream input2 = new BufferedInputStream(new FileInputStream(img2));
            while (totalBytesRead2 < res2.length) {
                int bytesRemaining = res2.length - totalBytesRead2;
                int bytesRead = input2.read(res2, totalBytesRead2, bytesRemaining);
                if (bytesRead > 0) {
                    totalBytesRead2 = totalBytesRead2 + bytesRead;
                }
            }
            input2.close();
            b64PersonImage = DatatypeConverter.printBase64Binary(res2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return b64PersonImage;
    }
    
	  public static final String IP_ADDR = "127.0.0.1";//服务器地址   
	  public static final int PORT = 9376;//服务器端口号  
//	  private static InputStream in;
//	  private static OutputStream out;
	  public static void main(String[] args) {
		  String message = null;
		  long startTime = System.currentTimeMillis();
		  BufferedOutputStream out=null;
		    try
		    {
		      Socket soc = new Socket(IP_ADDR, PORT);
		      soc.setTcpNoDelay(true);
		      soc.setSoTimeout(60000);
//		      String reqXml="=========";
              String reqXml=imageToBase64("D:\\\\Work\\\\techshino\\\\project\\\\code\\\\com.bankcomm-交行统一生物识别认证平台\\\\20170824\\\\b77b5b2f1554e65fe56412cba6dba00d2a9fb833_1.jpg");
		      byte[] fs = (reqXml).getBytes("GBK");
		      byte[] all = new byte[fs.length + 8];
		      DecimalFormat df = new DecimalFormat("00000000");
//		      String strLength = df.format(fs.length + transCode.length());
		      String strLength = df.format(fs.length );
		      for (int i = 0; i < 8; i++) {
		        all[i] = ((byte)strLength.charAt(i));
		      }

//		      for (int i = 8; i < 12; i++) {
//		        all[i] = ((byte)transCode.charAt(i - 8));
//		      }
		      System.arraycopy(fs, 0, all, 8, fs.length);
		      soc.getOutputStream().write(all);
		      
//		   
		      InputStream is = soc.getInputStream();
		      byte[] rs = new byte[1024000];
		      int start = 0;
		      while (start < 8) {
		        start += is.read(rs, start, 8 - start);
		      }
		      int resLenth = Integer.parseInt(new String(rs, 0, 8));
		      while (start < resLenth + 8) {
		        start += is.read(rs, start, resLenth + 8 - start);
		      }
//		      System.out.println(System.currentTimeMillis()-startTime+"--------ms-------");
		      message = new String(rs,"GBK");
		      System.out.println(message);
		      System.out.println(System.currentTimeMillis()-startTime+"--------ms--11-----");
             
//		      soc.getOutputStream().close();
//		      is.close();
//		      soc.close();
		    }
		    catch (Throwable t) {
		      t.printStackTrace();
		    }
	 }

}
