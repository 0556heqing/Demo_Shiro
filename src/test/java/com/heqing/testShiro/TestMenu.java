package com.heqing.testShiro;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.heqing.shiro.entity.MenuEntity;
import com.heqing.shiro.service.IMenuService;

@RunWith(SpringJUnit4ClassRunner.class)		// 表示继承了 SpringJUnit4ClassRunner 类
@ContextConfiguration(locations = {"classpath*:spring-core.xml"})
public class TestMenu {

	@Resource
	IMenuService menuService;
	
//	@Test
	public void getMenuListByUserId() {
		List<MenuEntity> menus = menuService.getMenuListByUserId(3l, 0);
		for(MenuEntity menu : menus) {
			System.out.println(menu.getName());
		}
	}
	
//	@Test
	public void getMenuListByRoleId() {
		List<MenuEntity> menus = menuService.getMenuListByRoleId(2l);
		for(MenuEntity menu : menus) {
			System.out.println(menu.getName());
		}
	}
	
//	@Test
	public void getMenuListNotButton() {
		List<MenuEntity> menus = menuService.getMenuListNotButton();
		for(MenuEntity menu : menus) {
			System.out.println(menu.getName());
		}
	}
	
	@Test
	public void getAllList() {
		List<MenuEntity> menuList = menuService.getAllList();
		for(MenuEntity menu : menuList){
			System.out.println("---->"+menu.getName());
		}
	}
}