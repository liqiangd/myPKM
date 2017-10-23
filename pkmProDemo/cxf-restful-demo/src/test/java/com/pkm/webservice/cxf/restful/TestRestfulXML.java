package com.pkm.webservice.cxf.restful;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class TestRestfulXML {
    
	private HttpClient client;
	public static void main(String[] args) {
		TestRestfulXML client = new TestRestfulXML();  
	        //发送XML数据到服务  
	        String result;
			try {
				result = client.sendXMLDataByPost("http://localhost:8080/cxf-restful-demo/WSRest/userws/addUser", client.getXMLString());
				  System.out.println(result); 
			} catch (Exception e) {
				e.printStackTrace();
			}  
	      
	}
	  // 获取XML  
    public String getXMLString() {  
        String XML_HEADER = "<?xml version=\"1.0\" encoding=\"GBK\"?>";  
        StringBuffer sb = new StringBuffer();  
        sb.append(XML_HEADER);  
        sb.append("<User>");  
        sb.append("<id>");  
        sb.append("11");  
        sb.append("</id>");  
        sb.append("<name>");  
        sb.append("123");  
        sb.append("</name>");  
        sb.append("</User>");  
        // 返回String格式  
        return sb.toString();  
    }  
  
    // 使用POST方法发送XML数据  
    public String sendXMLDataByPost(String url, String xmlData) throws Exception {  
        if (client == null){  
            client = HttpClients.createDefault();  
        }  
        HttpPost post = new HttpPost(url);  
        List<BasicNameValuePair> parameters = new ArrayList<>();  
        parameters.add(new BasicNameValuePair("xml", xmlData));  
        post.setEntity(new UrlEncodedFormEntity(parameters,"UTF-8"));  
        HttpResponse response = client.execute(post);  
        System.out.println(response.toString());  
        HttpEntity entity = response.getEntity();  
        String result = EntityUtils.toString(entity, "UTF-8");  
        return result;  
    }  
}
