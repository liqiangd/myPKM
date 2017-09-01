package com.pkm.cloud.producer;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloControl {

	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass()); 
	
	@Value("${server.port}")
	private String port;

	/**
	 * 提供简单的hello接口
	 * */
	@RequestMapping("/hello")
	@ResponseBody
	public String index(@RequestParam String name) {

		return "hello " + name + "，this is port="+port+" messge";
	}
	
	
	//TODO 多参数  可以用Map
	@RequestMapping("/hello1")
	@ResponseBody
    public User hello(@RequestHeader("name") String name, @RequestHeader("age") int age) {
        try {
            name= URLDecoder.decode(name,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        User user1=new User();
		user1.setAge(age);
		user1.setID(1);
		user1.setName(name);
		LOG.info("========");
		return user1;
    }
	//TODO 返回对象
	
	//TODO 入参为对象？
	@RequestMapping("/hello2")
	@ResponseBody
	public User index2(@RequestBody User user) {
		System.out.println("传入的userID="+user.getID()+";age="+user.getAge()+";name="+user.getName());
		User user1=new User();
		user1.setAge(11);
		user1.setID(1);
		user1.setName("哈哈");
		LOG.info("========");
		return user1;
	}
}
