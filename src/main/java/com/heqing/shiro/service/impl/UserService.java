package com.heqing.shiro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.heqing.shiro.base.BaseServiceImpl;
import com.heqing.shiro.dao.IUserDao;
import com.heqing.shiro.entity.UserEntity;
import com.heqing.shiro.service.IUserService;

@Service
public class UserService extends BaseServiceImpl<UserEntity> implements IUserService {

	@Resource
	private IUserDao userDao;

	@Override
	@Cacheable(value="shiro", key="#root.targetClass+#root.methodName") 
	public List<String> getMenuPermsByUserId(Long userId) {
		// TODO Auto-generated method stub
		return userDao.getMenuPermsByUserId(userId);
	}

	@Override
	public UserEntity geUserByName(String userName) {
		// TODO Auto-generated method stub
		return userDao.geUserByName(userName);
	}

	@Override
	public void deleteUserRoleByUserId(Long userId) {
		// TODO Auto-generated method stub
		userDao.deleteUserRoleByUserId(userId);
	}

	@Override
	public void saveUserRole(Long userId, List<Long> roleIdList) {
		// TODO Auto-generated method stub
		if(roleIdList.size() == 0) return;
		userDao.saveUserRole(userId, roleIdList);
	}

	@Override
	public void saveOrUpdateUserRole(Long userId, List<Long> roleIdList) {
		// TODO Auto-generated method stub
		if(roleIdList.size() == 0) return;
		deleteUserRoleByUserId(userId);
		userDao.saveUserRole(userId, roleIdList);
	}

}
