package com.pkm.webservice.cxf.restful;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;
import org.apache.http.entity.*;


import java.net.URI;
import java.util.*;
import java.io.*;


import org.json.*;
//import cxf.server.*;

public class TestRestfuleClient {

	public static void main(String[] args) throws Exception {
		 User muser = new User();
		 muser.setId("5");
		 muser.setName("Jane");
		 
		//post method to add user
		 httpMethodgo("http://localhost:8080/cxf-restful-demo/WSRest/userws/addUser", muser); 
		
//		 //put method to update user
//		 muser.setName("Jane5");
//		 HttpPutgo("http://localhost:8080/cxf-restful-demo/WSRest/userws/updateUser", muser); 		 
//	    /*
//		 * get a specified user via ID
//		 */
//		System.out.println("get one user via Get method of WS-Restful");
//		HttpGetgo("http://localhost:8080/cxf-restful-demo/WSRest/userws/getUserById",5, true);
//		
//		System.out.println("get all users via Get method of WS-Restful");
//		/*
//		 * get all users
//		 */
//		HttpGetgo("http://localhost:8080/cxf-restful-demo/WSRest/userws",1,false);  
//		//delete this user
//		 muser.setId("100");
//		 HttpDeletego("http://localhost:8080/cxf-restful-demo/WSRest/userws/delUser", muser); 
//		 //get all users again
//		 HttpGetgo("http://localhost:8080/cxf-restful-demo/WSRest/userws",1,false);  
//		 return;
	  }
	
	private static void HttpPutgo(String uri, User muser ) throws Exception { 
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 HttpPut httpMethod = new HttpPut(uri);
		 httpMethod.addHeader("content-type","application/json");


		 JSONObject c = new JSONObject();
		 c.put("name", muser.getName());
		 c.put("id", muser.getId());
		 
		 JSONStringer js = new JSONStringer();
		 js.object();
		 js.key("user").value(c);
		 js.endObject();
		 
		 String ms=js.toString();
		 /** Console output
		  * HttpPutgo(): request string={"user":{"name":"Jane5","id":"5"}}
		  */
		 System.out.println("HttpPutgo(): request string="+ms); 
		 StringEntity entity = new StringEntity(ms,"UTF-8"); 
		 httpMethod.setEntity(entity);
		 CloseableHttpResponse response2 = httpclient.execute(httpMethod);
		 System.out.println(response2.getStatusLine());
		 String rst=EntityUtils.toString(response2.getEntity());
		 /**
		  * console output:
		  * HTTP/1.1 200 OK
		  */
        System.out.println(rst); 
	 }
	
	 private static void httpMethodgo(String uri, User muser) throws Exception { 
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 HttpPost httpMethod = new HttpPost(uri);
		 httpMethod.addHeader("content-type","application/json");
		 
		 JSONObject c = new JSONObject();
		 c.put("name", muser.getName());
		 c.put("id", muser.getId());
		 
		 JSONStringer js = new JSONStringer();
		 js.object();
		 js.key("user").value(c);
		 js.endObject();
		 
		 String ms=js.toString();
		 /** console output:
		  * httpMethodgo(): request string={"user":{"name":"Jane","id":"5"}}
		  */
		 System.out.println("httpMethodgo(): request string="+ms); 
		 
		 StringEntity entity = new StringEntity(ms,"UTF-8"); 
		 httpMethod.setEntity(entity);
		 
		 CloseableHttpResponse response2 = httpclient.execute(httpMethod);
		 System.out.println(response2.getStatusLine());
		 String rst=EntityUtils.toString(response2.getEntity());
		 /**
		  * HTTP/1.1 200 OK
		  */
        System.out.println(rst);    
	 }
	 
	 private static void HttpGetgo(String resource, Integer userid, boolean SingleResult) throws Exception {  
		 CloseableHttpClient httpclient = HttpClients.createDefault();
		 HttpGet request=null;
		 try {
			 if (SingleResult) {
				 //get one user via id
				request=new HttpGet(resource+"/"+userid.toString());
			 } 
			 else 	 
			    request = new HttpGet(resource);
			 
			 HttpResponse response = httpclient.execute(request); 
			 try {
				    System.out.println(response.getStatusLine());
				    String rst=EntityUtils.toString(response.getEntity());
	                System.out.println(rst);
	                
	              if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK) {
	                 System.out.println("receive OK rsp");
	                 HttpEntity entity = response.getEntity();
	                 if(entity == null) {
	                	 System.out.println("entity is null");
	                	return;
	                }
	                
	                JSONObject jsonrsp = null;
	                jsonrsp = new JSONObject(new JSONTokener(rst));
	              
	                if(SingleResult==false) {
	                 JSONArray mobj=jsonrsp.getJSONArray("user");
	                 Iterator it=mobj.iterator();
	                 while(it.hasNext()){
	                	JSONObject obj=(JSONObject)it.next();
	                	  System.out.println("id="+obj.getInt("id")+" name="+obj.getString("name"));
	                    } 
	                 } 
	                else {
	                	JSONObject obj=jsonrsp.getJSONObject("user");
	                	System.out.println("id="+obj.getInt("id")+" name="+obj.getString("name"));
	                   }
	                } 	              
	            else {
	                System.out.println("receive error rsp");
	                }
	      	    } 
			finally {
	                ;
	            }
		    } 
		 finally {
	            httpclient.close();
	       }
	 }  


private static void HttpDeletego(String resource, User muser) throws Exception {  
	 CloseableHttpClient httpclient = HttpClients.createDefault();
	 HttpDelete request=null;
	 try {
		 //delete one user via id
		  request=new HttpDelete(resource+"/"+muser.getId().toString());
		  HttpResponse response = httpclient.execute(request); 
		  if(response.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
			  System.out.println("deleting user succeed");  
		  } 
		  else {
			  System.out.println("deleting user failed");   
		  }
		  
		  System.out.println(response.getStatusLine());
		  String rst=EntityUtils.toString(response.getEntity());
         System.out.println(rst);    
	     } 
	 finally {
          httpclient.close();
     }
 }  
}
