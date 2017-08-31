package com.pkm.pkmDemo.apache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class client {

	private final static String FTPIP = "192.168.190.156";  
    private static int port = 9982;  
    private final static String FTPUSER = "lizhiwei";  
    private final static String FTPPWD = "123456";  
    private final static String FTPROOT = "traffic";  
    private final static int CLIENTSIZE = 10;  
      
    private static List<FTPClient> ftpLists = new ArrayList<FTPClient>();  
    private static HashMap<FTPClient, Integer> ftpStatus = new HashMap<FTPClient, Integer >();// 0表示可用 1 表示不可用  
      
    static { }  
    
    /** 
     * FunName:  连接服务器 
     * @Author: sam 
     * @Create Date: 2011-11-16 
      */  
     public static void connect(FTPClient ftp) throws IOException {//必要时，可以设置编码为gbk  
         try {  
             ftp.connect(FTPIP, port);  
             int reply = ftp.getReplyCode();//连接ftp后返回值类型  
             if(!FTPReply.isPositiveCompletion(reply)) {  
                 ftp.disconnect();  
                 System.exit(0);//throw new IOException("服务器拒绝连接.");  
             } else if(!ftp.login(FTPUSER, FTPPWD)) {  
                 ftp.logout();  
                 throw new IOException("FTP用户名或密码!");  
             }  
             ftp.setFileType(FTP.BINARY_FILE_TYPE);//使用二进制  
             if(FTPROOT != null && !"".equals(FTPROOT.trim())) {  
                 ftp.changeWorkingDirectory(FTPROOT);//改变目录路径  
             }  
         } catch(IOException ex) {  
             if(ftp.isConnected()) {  
                 try {  
                     ftp.disconnect();  
                 } catch(IOException ioex) {  
                     System.err.println("断开服务器连接失败");  
                 }  
             }  
             System.err.println(ex.getMessage());  
         }  
     }  
   
     /** 
     * FunName:  断开重新连接 
     * @Author: sam 
     * @Create Date: 2011-11-16 
      */  
     public static void reConnect(FTPClient ftp) throws IOException {  
         try{  
             disConnect(ftp);  
         }catch(IOException e){}  
         connect(ftp);  
     }  
       
     /** 
     * FunName:  断开连接 
     * @Author: sam 
     * @Create Date: 2011-11-16 
      */  
     public static void disConnect(FTPClient ftp) throws IOException {  
         try {  
             ftp.noop(); // check that control connection is working OK  
             ftp.logout();  
         } catch(IOException ioe) {  
             System.err.println(ioe.getMessage());  
         } finally {  
             if(ftp.isConnected()) System.err.println("FTP未断开连接");  
         }  
     }  
       
     /** 
     * FunName:  关闭ftp 
     * @Author: sam 
     * @Create Date: 2011-11-16 
      */  
     public static void closeFtp(FTPClient ftp) throws IOException {  
         if(ftpLists.indexOf(ftp) < 0) {  
             disConnect(ftp);  
         } else {  
             ftpStatus.put(ftp, 0);  
         }  
     }  
   
     /** 
     * FunName:  获得一个ftp 
     * @Author: sam 
     * @Create Date: 2011-11-16 
      */  
     public synchronized static FTPClient getFtpClient() throws IOException {  
         FTPClient ftp = null;  
         for(FTPClient client : ftpLists) {  
             //判断当前client是否已经有连接  
             if(ftpStatus.get(client) != null && ftpStatus.get(client).equals(new Integer(1))) {  
                 ftpStatus.put(client, 1);  
                 ftp = client;  
                 break;  
             }  
         }  
         if(ftp == null) {  
             //新建一个连接，放到ftpDownloads连接池  
             try {  
                 FTPClient newClient = new FTPClient();  
                 connect(newClient);  
                 if(ftpLists.size() < CLIENTSIZE) {  
                     ftpLists.add(newClient);  
                     ftpStatus.put(newClient, 1);  
                     ftp = newClient;  
                 }  
             } catch(IOException ioe) {  
//                 LogFactory.getLog("/").error(ioe.getMessage());  
            	 System.out.println(ioe.getMessage());
             }  
         }  
           
         if(!ftp.isAvailable()) {//判断ftp是否还保持连接状态  
             reConnect(ftp);  
         } else {  
             ftp.changeToParentDirectory();  
             if(FTPROOT != null && !"".equals(FTPROOT.trim())) ftp.changeWorkingDirectory(FTPROOT);  
         }  
         return ftp;  
     } 
     
 	public static void main(String[] args) {
 		try {
			connect(new FTPClient());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }	
}
