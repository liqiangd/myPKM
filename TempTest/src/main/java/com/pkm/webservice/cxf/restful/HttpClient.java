package com.pkm.webservice.cxf.restful;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpClient {

	public static void main(String[] args) {
		  String wsdl = "http://localhost:8080/cxf-restful-demo/WSRest/userws/addUser";
	        URL urlConn;
	        try {
	            urlConn = new URL(wsdl);
	            HttpURLConnection httpConn = (HttpURLConnection)urlConn.openConnection();
	            httpConn.setDoInput(true);
	            httpConn.setDoOutput(true);
	            httpConn.setConnectTimeout(1000*30);
	            httpConn.setReadTimeout(1000*60);
	            httpConn.setRequestMethod("POST");
	            httpConn.setRequestProperty("Content-Type", "application/xml;charset=UTF-8");
	            OutputStream oStream = httpConn.getOutputStream();
	            StringBuffer buffer = new StringBuffer();
	            //buffer.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><User><id>1</id><name>2</name><classes><classId>345</classId><className>Name</className></classes></User>");
	            buffer.append("<?xml version='1.0' encoding='UTF-8'?><TX><TX_HEADER><SYS_HDR_LEN>100</SYS_HDR_LEN><SYS_PKG_VRSN>01</SYS_PKG_VRSN><SYS_TTL_LEN>400</SYS_TTL_LEN><SYS_REQ_SEC_ID>0123456789</SYS_REQ_SEC_ID><SYS_SND_SEC_ID>9876543210</SYS_SND_SEC_ID><SYS_TX_CODE>BiolServices</SYS_TX_CODE><SYS_TX_VRSN>01</SYS_TX_VRSN><SYS_TX_TYPE>00000</SYS_TX_TYPE><SYS_RESERVED>AA</SYS_RESERVED><SYS_EVT_TRACE_ID>系统时间：yyyyMMddHHmmssms</SYS_EVT_TRACE_ID><SYS_SND_SERIAL_NO>0000000023</SYS_SND_SERIAL_NO><SYS_PKG_TYPE>1</SYS_PKG_TYPE><SYS_MSG_LEN>100</SYS_MSG_LEN><SYS_IS_ENCRYPTED>0</SYS_IS_ENCRYPTED><SYS_ENCRYPT_TYPE>3</SYS_ENCRYPT_TYPE><SYS_COMPRESS_TYPE>0</SYS_COMPRESS_TYPE><SYS_EMB_MSG_LEN>0</SYS_EMB_MSG_LEN><SYS_REQ_TIME>系统时间：yyyyMMddHHmmssms</SYS_REQ_TIME><SYS_TIME_LEFT>系统时间：HHmmssms</SYS_TIME_LEFT><SYS_PKG_STS_TYPE>00</SYS_PKG_STS_TYPE></TX_HEADER><TX_BODY><COMMON><COM1><TXN_INSID>440000000</TXN_INSID><TXN_ITT_CHNL_ID>0130</TXN_ITT_CHNL_ID><TXN_ITT_CHNL_CGY_CODE>0001</TXN_ITT_CHNL_CGY_CODE><TXN_DT>系统日期：yyyyMMdd</TXN_DT><TXN_TM>系统时间：HHmmss</TXN_TM><TXN_STFF_ID>999999</TXN_STFF_ID><MULTI_TENANCY_ID>CN000</MULTI_TENANCY_ID><LNG_ID>zh-cn</LNG_ID></COM1></COMMON><ENTITY><AUTH_TYPE>认证类型</AUTH_TYPE><AUTH_MODE>认证方式</AUTH_MODE><CUST_NAME>客户名称</CUST_NAME><ID_NO>证件号码</ID_NO><HITS>命中数</HITS><THRESHOLD>阀值</THRESHOLD><FEATURES>特征值</FEATURES></ENTITY></TX_BODY><TX_EMB></TX_EMB></TX>");
	            oStream.write(buffer.toString().getBytes());
	            int code = httpConn.getResponseCode();
	            String tempString = null;
	            StringBuffer sb = new StringBuffer();
	            if(code==HttpURLConnection.HTTP_OK){
	                System.out.println("sb==="+sb);
	            }
	            
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (ProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } 
	}

}
