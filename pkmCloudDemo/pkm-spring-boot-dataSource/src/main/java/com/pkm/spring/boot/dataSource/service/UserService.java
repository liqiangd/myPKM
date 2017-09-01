package com.pkm.spring.boot.dataSource.service;

import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pkm.spring.boot.dataSource.common.ds.TargetDataSource;
import com.pkm.spring.boot.dataSource.common.pojo.AjaxResult;
import com.pkm.spring.boot.dataSource.common.pojo.PageAjax;
import com.pkm.spring.boot.dataSource.common.utils.AppUtil;
import com.pkm.spring.boot.dataSource.model.AuthUser;


@Service
public class UserService extends AbstratService<AuthUser> {

	/**
	 * 使用默认数据源
	 * @param page
	 * @param user
	 * @return
	 */
	public PageAjax<AuthUser> queryUserDefPage(PageAjax<AuthUser> page, AuthUser user){
		return queryPage(page, user);
	}
	
	@TargetDataSource("ds")
	public PageAjax<AuthUser> queryUserDsPage(PageAjax<AuthUser> page, AuthUser user){
		return queryPage(page, user);
	}
	
	@TargetDataSource("ds1")
	public PageAjax<AuthUser> queryUserDs1Page(PageAjax<AuthUser> page, AuthUser user){
		return queryPage(page, user);
	}
	
	@TargetDataSource("ds2")
	public PageAjax<AuthUser> queryUserDs2Page(PageAjax<AuthUser> page, AuthUser user){
		return queryPage(page, user);
	}

	/**
	 * 事务测试
	 * @param user
	 * @return
	 */
	@Transactional//添加事务
	@TargetDataSource("ds1")
	public AjaxResult addUser(AuthUser user) {
		String str = AppUtil.getRandomString(5);
		user.setUsername("user" + str);
		user.setPassword("123456");
		user.setEmail(str + "@test.com");
		user.setRoleid(5);
		user.setUseable(1);
		user.setAddtime(new Date());
		int ret = insert(user);
		if(ret > 0){
			System.out.println(1/0);
			user = new AuthUser();
			str = AppUtil.getRandomString(5);
			user.setUsername("user" + str);
			user.setPassword("123456");
			user.setEmail(str + "@test.com");
			user.setRoleid(5);
			user.setUseable(1);
			user.setAddtime(new Date());
		}
		return save(user);
	}
}
