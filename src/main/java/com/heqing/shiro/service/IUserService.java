package com.heqing.shiro.service;

import java.util.List;

import com.heqing.shiro.base.BaseService;
import com.heqing.shiro.entity.UserEntity;

public interface IUserService extends BaseService<UserEntity> {

	/**
	 * 根据用户ID，获取菜单权限
	 * @param userId 用户ID
	 * @return List<T> 细粒度权限
	 */
	List<String> getMenuPermsByUserId(Long userId);
	
	/**
	 * 根据用户登陆名，获取用户信息
	 * @param userName 用户登陆名
	 * @return UserEntity 用户信息
	 */
	UserEntity geUserByName(String userName);
	
	/**
	 * 根据用户ID，删除用户角色关联表
	 * @param userId 用户ID
	 */
	void deleteUserRoleByUserId(Long userId);
	
	/**
	 * 保存用户角色关联表
	 * @param userId 用户ID
	 * @param roleIdList 角色ID
	 */
	void saveUserRole(Long userId, List<Long> roleIdList);
	
	/**
	 * 保存或修改用户关联表
	 * @param userId 用户ID
	 * @param roleIdList 角色ID
	 */
	void saveOrUpdateUserRole(Long userId, List<Long> roleIdList);
}
