package com.heqing.shiro.service;

import java.util.List;

import com.heqing.shiro.base.BaseService;
import com.heqing.shiro.entity.RoleEntity;

public interface IRoleService extends BaseService<RoleEntity> {

	/**
	 * 根据用户ID，获取菜单列表
	 * @param userId 用户ID
	 * @return List<T> 角色列表
	 */
	List<RoleEntity> getRoleListByUserId(Long userId);
	
	/**
	 * 根据角色ID，删除角色菜单关联表
	 * @param roleId 角色ID
	 */
	void deleteRoleMenuByRoleId(Long roleId);
	
	/**
	 * 保存角色菜单关联表
	 * @param roleId 角色ID
	 * @param menuIdList 菜单Id集合
	 */
	void saveUserRole(Long roleId, List<Long> menuIdList);

	/**
	 * 保存或修改角色菜单关联表
	 * @param roleId 角色ID
	 * @param menuIdList 菜单Id集合
	 */
	void saveOrUpdateUserRole(Long roleId, List<Long> menuIdList);
}
