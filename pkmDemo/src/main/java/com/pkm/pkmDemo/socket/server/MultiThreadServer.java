/**
 * Project Name:biometric-socket
 * File Name:MultiThreadServer.java
 * Package Name:com.techshino.biometric.socket
 * Date:2017-6-12下午5:51:31
 * Copyright (c) 2017, liqiangd@techshino.com All Rights Reserved.
 *
 */

package com.pkm.pkmDemo.socket.server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yytech.commons.lang.StringUtils;
import com.yytech.frame.secret.MessageDigestFactory;

/**
 * ClassName:MultiThreadServer <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2017-6-12 下午5:51:31 <br/>
 * 
 * @author liqiang
 * @version
 * @since JDK 1.6
 * @see
 */
public class MultiThreadServer // implements ServletContextListener
{
    
    /**
     * serialVersionUID:TODO(用一句话描述这个变量表示什么).
     * 
     * @since JDK 1.6
     */
    // private static final long serialVersionUID = 1L;
    
    private static final Logger LOG = LoggerFactory.getLogger(MultiThreadServer.class);
    
    // socket服务端口号
    private int port = 9376;
    
    private ServerSocket serverSocket;
    
    private ExecutorService executorService;// 线程池
    
    /**
     * 启动监听socket端口的线程服务
     * 
     * @throws IOException
     */
    public MultiThreadServer()
      //  throws Exception
    {
        try
        {
            serverSocket = new ServerSocket(port);

        int POOL_SIZE = getPoolSize();
        LOG.debug("线程池个数:[{}]", POOL_SIZE);
        executorService = Executors.newFixedThreadPool(POOL_SIZE);
        LOG.info("服务器已启动，监听的端口为[{}]", port);
        }
        catch (IOException e)
        {
            LOG.error("socket服务器启动异常！",e);
        }
    }
    
    /**
     * 运行socket服务
     * 
     */
    public void service()
    {
        while (true)
        {
            Socket socket = null;
            try
            {
                // 接收客户连接,只要客户进行了连接,就会触发accept();从而建立连接
                socket = serverSocket.accept();
                executorService.execute(new Handler(socket));
            }
            catch (Exception e)
            {
                // logger.error(e.getMessage());
                LOG.error(e.getMessage());
            }
        }
    }
    
    /**
     * 获取线程池个数
     * 
     * @return
     */
    private int getPoolSize()
    {
        
//        Properties pro = null;
//        InputStream stream = null;
        // 获取cpu个数
        int cpuSum = Runtime.getRuntime().availableProcessors();
        int POOLSIZE = cpuSum;
        LOG.debug("当前系统CPU数目：[{}]", cpuSum);
//        try
//        {
//            stream = new BufferedInputStream(this.getClass().getResourceAsStream("/poolsize.properties"));
//            pro = new Properties();
//            pro.load(stream);
//            String param = pro.getProperty("threadNum");
//            POOLSIZE = Integer.parseInt(param);
//        }
//        catch (Exception e)
//        {
//            LOG.error("解析文件格式出错【" + e.getMessage() + "】");
//        }
//        finally
//        {
//            try
//            {
//                if (stream != null)
//                {
//                    stream.close();
//                }
//            }
//            catch (IOException e)
//            {
//                LOG.error("关闭解析文件格式流出错【" + e.getMessage() + "】");
//            }
//        }
        return POOLSIZE;
    }
}

/**
 * 每个socket请求分配一个线程
 * 
 * @author kingpoon
 * 
 */
class Handler implements Runnable
{
    
    private static final Logger LOG = LoggerFactory.getLogger(Handler.class);
    
    private int outTime = 60000; // 超时1分钟
    
    private Socket socket;
    
    private static final String STR_FORMAT = "00000000";
    
	OutputStreamWriter output = null;

	BufferedWriter bw = null;
	
	
    OutputStream os=null;
    PrintWriter pw=null;
    
    
    public Handler(Socket socket)
    {
        this.socket = socket;
    }
    
	/**
	 * @title
	 * */
	public byte[] readByte(InputStream inStream, int len)
			throws InterruptedException {
		byte[] readByte = new byte[len];
		int length = 0;
		try {
			while ((length += inStream.read(readByte, length, len - length)) < len) {
//				Thread.sleep(100);
			}
			return readByte;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
  
   
    public void run()
    {
		InputStream in = null;
//		 BufferedOutputStream  out=null;
		try {
			socket.setTcpNoDelay(true);
			in = socket.getInputStream();
			LOG.info("NEW CONNECTION ACCEPTED [" + socket.getInetAddress()
					+ ":" + socket.getPort() + "]......");
			long startTime = System.currentTimeMillis();
			socket.setSoTimeout(outTime);
			byte[] headerByte = readByte(in, STR_FORMAT.length());
			int len = Integer.valueOf(new String(headerByte));

			byte[] brecv = readByte(in, len-8);
			if ((len-8) == brecv.length) {
				String objMsg = new String(brecv, "GBK");
                  String reqXml="XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX";
	              System.out.println("接收数据长度:"+objMsg.length());
	              Thread.sleep(600);
			      byte[] fs = reqXml.getBytes("GBK");
			      byte[] all = new byte[fs.length + 8];
			      DecimalFormat df = new DecimalFormat("00000000");
			      String strLength = df.format(fs.length );
			      for (int i = 0; i < 8; i++) {
			        all[i] = ((byte)strLength.charAt(i));
			      }
			      System.arraycopy(fs, 0, all, 8, fs.length);
			      socket.getOutputStream().write(all);
			} else {
				LOG.error("socket服务端接收报文长度不对");
			}
		} catch (InterruptedException i) {
			LOG.error("socket服务端接收报文异常");
		} catch (IOException e) {
			LOG.error("socket服务端接收报文异常");
		} finally {
//			try {
//				in.close();
//				 socket.getOutputStream().close();
//				 socket.close();
//
//				if (output != null) {
//					output.close();
//				}
//				if (bw != null) {
//					bw.close();
//				}
//		         if(pw!=null)
//	                    pw.close();
//	                if(os!=null)
//	                    os.close();
//	                
//	                if(socket!=null)
//	                    socket.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}	
    }
}
