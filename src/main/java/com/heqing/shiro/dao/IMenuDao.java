package com.heqing.shiro.dao;

import java.util.List;

import com.heqing.shiro.base.BaseDao;
import com.heqing.shiro.entity.MenuEntity;

public interface IMenuDao extends BaseDao<MenuEntity> {

	/**
	 * 根据用户ID，获取菜单列表
	 * @param userId 用户ID
	 * @return List<T> 菜单列表
	 */
	List<MenuEntity> getMenuListByUserId(Long userId);
	
	/**
	 * 根据角色ID，获取菜单列表
	 * @param roleId 角色ID
	 * @return List<T> 菜单列表
	 */
	List<MenuEntity> getMenuListByRoleId(Long roleId);
	
	/**
	 * 根据父角色ID，获取菜单列表
	 * @param roleId 父角色ID
	 * @return List<T> 菜单列表
	 */
	List<MenuEntity> getMenuListByParentId(Long parentId);
	
	/**
	 * 获取不包含按钮的菜单列表
	 * @return List<T> 菜单列表
	 */
	List<MenuEntity> getMenuListNotButton();

}
