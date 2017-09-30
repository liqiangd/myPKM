package com.pkm.pkmDemo.socket.client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.bind.DatatypeConverter;

public class TestSocketClient {

	
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
    
	public static void main(String[] args) {
		
		  long startTime = System.currentTimeMillis();
		 try {
	            //1.创建客户端Socket，指定服务器地址和端口
	            Socket socket=new Socket("localhost", 9376);
	            //2.获取输出流，向服务器端发送信息
	            OutputStream os=socket.getOutputStream();//字节输出流
	            PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
	            String reqXml=imageToBase64("D:\\\\Work\\\\techshino\\\\project\\\\code\\\\com.bankcomm-交行统一生物识别认证平台\\\\20170824\\\\b77b5b2f1554e65fe56412cba6dba00d2a9fb833_1.jpg");
	            pw.write(reqXml);
	            pw.flush();
	            socket.shutdownOutput();//关闭输出流
	            //3.获取输入流，并读取服务器端的响应信息
	            InputStream is=socket.getInputStream();
	            BufferedReader br=new BufferedReader(new InputStreamReader(is));
	            String info=null;
	            while((info=br.readLine())!=null){
	                System.out.println("我是客户端，服务器说："+info);
	            }
	            //4.关闭资源
	            br.close();
	            is.close();
	            pw.close();
	            os.close();
	            socket.close();
	        } catch (UnknownHostException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 System.out.println(System.currentTimeMillis()-startTime+"--------ms-------");
	}

}
