package com.heqing.shiro.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.heqing.shiro.base.BaseServiceImpl;
import com.heqing.shiro.dao.IRoleDao;
import com.heqing.shiro.entity.RoleEntity;
import com.heqing.shiro.service.IRoleService;

@Service
public class RoleService extends BaseServiceImpl<RoleEntity> implements IRoleService {

	@Resource
	private IRoleDao roleDao;
	
	@Override
	public List<RoleEntity> getRoleListByUserId(Long userId) {
		// TODO Auto-generated method stub
		return roleDao.getRoleListByUserId(userId);
	}

	@Override
	public void deleteRoleMenuByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		roleDao.deleteRoleMenuByRoleId(roleId);
	}

	@Override
	public void saveUserRole(Long roleId, List<Long> menuIdList) {
		// TODO Auto-generated method stub
		if(menuIdList.size() == 0) return;
		roleDao.saveUserRole(roleId, menuIdList);
	}

	@Override
	public void saveOrUpdateUserRole(Long roleId, List<Long> menuIdList) {
		// TODO Auto-generated method stub
		if(menuIdList.size() == 0) return;
		deleteRoleMenuByRoleId(roleId);
		saveUserRole(roleId, menuIdList);
	}

}
