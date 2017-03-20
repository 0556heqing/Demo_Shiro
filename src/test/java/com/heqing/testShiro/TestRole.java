package com.heqing.testShiro;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.heqing.shiro.entity.RoleEntity;
import com.heqing.shiro.service.IRoleService;

@RunWith(SpringJUnit4ClassRunner.class)		// 表示继承了 SpringJUnit4ClassRunner 类
@ContextConfiguration(locations = {"classpath*:spring-core.xml"})
public class TestRole {

	@Resource
	IRoleService roleService;
	
	@Test
	public void getRoleListByUserId() {
		List<RoleEntity> roles = roleService.getRoleListByUserId(3l);
		for(RoleEntity role : roles) {
			System.out.println("---->"+role.getRoleName());
		}
	}
	
}