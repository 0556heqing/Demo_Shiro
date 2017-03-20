package com.heqing.shiro.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.heqing.shiro.base.BaseDao;
import com.heqing.shiro.entity.UserEntity;

public interface IUserDao extends BaseDao<UserEntity> {
	
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
	void saveUserRole(@Param(value="userId")Long userId, @Param(value="roleIdList")List<Long> roleIdList);
}
