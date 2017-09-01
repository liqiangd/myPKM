package com.pkm.spring.boot.dataSource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pkm.spring.boot.dataSource.common.pojo.AjaxResult;
import com.pkm.spring.boot.dataSource.common.pojo.PageAjax;
import com.pkm.spring.boot.dataSource.model.AuthUser;
import com.pkm.spring.boot.dataSource.service.UserService;


@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/queryUserDefPage")
	public PageAjax<AuthUser> queryUserDefPage(PageAjax<AuthUser> page, AuthUser user){
		return userService.queryUserDefPage(page, user);
	}
	
	@ResponseBody
	@RequestMapping("/queryUserDsPage")
	public PageAjax<AuthUser> queryUserDsPage(PageAjax<AuthUser> page, AuthUser user){
		return userService.queryUserDsPage(page, user);
	}
	
	@ResponseBody
	@RequestMapping("/queryUserDs1Page")
	public PageAjax<AuthUser> queryUserDs1Page(PageAjax<AuthUser> page, AuthUser user){
		return userService.queryUserDs1Page(page, user);
	}
	
	@ResponseBody
	@RequestMapping("/queryUserDs2Page")
	public PageAjax<AuthUser> queryUserDs2Page(PageAjax<AuthUser> page, AuthUser user){
		return userService.queryUserDs2Page(page, user);
	}

	@ResponseBody
	@RequestMapping("/addUser")
	public AjaxResult addUser(AuthUser user){
		return userService.addUser(user);
	}

}
