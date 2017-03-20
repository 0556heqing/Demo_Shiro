package com.heqing.shiro.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.heqing.shiro.base.BaseServiceImpl;
import com.heqing.shiro.dao.IMenuDao;
import com.heqing.shiro.entity.MenuEntity;
import com.heqing.shiro.service.IMenuService;

@Service
public class MenuService extends BaseServiceImpl<MenuEntity> implements IMenuService {

	@Resource
	private IMenuDao menuDao;
	
	@Override
	public List<MenuEntity> getMenuListByUserId(Long userId) {
		// TODO Auto-generated method stub
		List<MenuEntity> menus = new ArrayList<MenuEntity>();
		if(userId == 1) {
			menus =  menuDao.getAllList();
		} else {
			menus = menuDao.getMenuListByUserId(userId);
		}
		List<Long> menusIds = new ArrayList<>();
		for(MenuEntity menu : menus) {
			menusIds.add(menu.getMenuId());
		}
		return getAllMenuList(menusIds);
	}
	
	@Cacheable(value="shiro", key="#root.targetClass+#root.methodName") 
	public List<MenuEntity> getMenuListByUserId(Long userId, Integer menuType) {
		List<MenuEntity> menuList = new ArrayList<>();
		List<MenuEntity> tempMenuList = getMenuListByUserId(userId);
		for(MenuEntity menu : tempMenuList) {
			if(menu.getType() == menuType) menuList.add(menu);
		}
		return menuList;
	}

	@Override
	@Cacheable(value="shiro", key="#root.targetClass+#root.methodName") 
	public List<MenuEntity> getMenuListByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		return menuDao.getMenuListByRoleId(roleId);
	}

	@Override
	@Cacheable(value="shiro", key="#root.targetClass+#root.methodName") 
	public List<MenuEntity> getMenuListByParentId(Long parentId) {
		// TODO Auto-generated method stub
		return menuDao.getMenuListByParentId(parentId);
	}
	
	@Override
	@Cacheable(value="shiro", key="#root.targetClass+#root.methodName") 
	public List<MenuEntity> getMenuListNotButton() {
		// TODO Auto-generated method stub
		return menuDao.getMenuListNotButton();
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<MenuEntity> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<MenuEntity> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		return menuList;
	}
	
	/**
	 * 递归
	 */
	private List<MenuEntity> getMenuTreeList(List<MenuEntity> menuList, List<Long> menuIdList){
		List<MenuEntity> subMenuList = new ArrayList<MenuEntity>();
		
		for(MenuEntity entity : menuList){
			if(entity.getType() == MenuEntity.CATALOG){//目录
				entity.setList(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		
		return subMenuList;
	}
	
	public List<MenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<MenuEntity> menuList = menuDao.getMenuListByParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<MenuEntity> userMenuList = new ArrayList<>();
		for(MenuEntity menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}
}
