package com.heqing.testShiro;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.heqing.shiro.entity.UserEntity;
import com.heqing.shiro.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)		// 表示继承了 SpringJUnit4ClassRunner 类
@ContextConfiguration(locations = {"classpath*:spring-core.xml"})
public class TestUser {

	@Resource
	IUserService userService;
	
//	@Test
	public void testCreate() {
		UserEntity user = new UserEntity();
		user.setUserName("test1");
		user.setPassword("123");
		userService.save(user);
	}
	
//	@Test
	public void testUpdate() {
		UserEntity user = userService.getById(4l);
		user.setPassword("321");
		userService.update(user);
	}
	
//	@Test
	public void testDelete() {
		userService.delete(4L);
	}
	
	@Test
	public void testGet() {
		UserEntity user = new UserEntity();
		user.setUserName("admin");
		List<UserEntity> userList = userService.getPageBeanByCondition(null, 1,10);
		System.out.println(userList.size());
	}
}