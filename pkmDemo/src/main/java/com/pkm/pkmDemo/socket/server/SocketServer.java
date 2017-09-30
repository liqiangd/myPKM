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
public class SocketServer // implements ServletContextListener
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
    public SocketServer()
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
                executorService.execute(new HandlerServer(socket));
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
        
        Properties pro = null;
        InputStream stream = null;
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
class HandlerServer implements Runnable
{
    
    private static final Logger LOG = LoggerFactory.getLogger(HandlerServer.class);
    
    private int outTime = 60000; // 超时1分钟
    
    private Socket socket;
    
    private static final String STR_FORMAT = "00000000";
    
	OutputStreamWriter output = null;

	BufferedWriter bw = null;
    public HandlerServer(Socket socket)
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
    	InputStream is=null;
        InputStreamReader isr=null;
        BufferedReader br=null;
        OutputStream os=null;
        PrintWriter pw=null;
        try {
            //获取输入流，并读取客户端信息
            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String info=null;
            while((info=br.readLine())!=null){//循环读取客户端的信息
                System.out.println("我是服务器，客户端说："+info);
            }
            socket.shutdownInput();//关闭输入流
            //获取输出流，响应客户端的请求
            os = socket.getOutputStream();
            pw = new PrintWriter(os);
            pw.write("欢迎您！");
            pw.flush();//调用flush()方法将缓冲输出
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //关闭资源
            try {
                if(pw!=null)
                    pw.close();
                if(os!=null)
                    os.close();
                if(br!=null)
                    br.close();
                if(isr!=null)
                    isr.close();
                if(is!=null)
                    is.close();
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
