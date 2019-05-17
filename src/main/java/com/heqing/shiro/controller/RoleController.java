package com.heqing.shiro.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.heqing.shiro.base.BaseController;
import com.heqing.shiro.entity.MenuEntity;
import com.heqing.shiro.entity.RoleEntity;
import com.heqing.shiro.service.IMenuService;
import com.heqing.shiro.service.IRoleService;
import com.heqing.shiro.utils.PageUtil;
import com.heqing.shiro.utils.ResultUtil;

/**
 * 角色相关
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Autowired
	private IRoleService roleService;
	@Autowired
	private IMenuService menuService;
	
	/**
	 * 角色列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:role:list")
	public ResultUtil list(String roleName, Integer page, Integer rows){
		System.out.println("--->"+roleName);
		RoleEntity role = new RoleEntity();
		if(!StringUtils.isBlank(roleName)) role.setRoleName(roleName);
		//查询列表数据
		List<RoleEntity> list = roleService.getPageBeanByCondition(role, page, rows);
		int total = roleService.getListByCondition(role).size();		
		PageUtil pageUtil = new PageUtil(list, total, rows, page);
		return ResultUtil.ok().put("page", pageUtil);
	}
	
	/**
	 * 角色列表
	 */
	@ResponseBody
	@RequestMapping("/select")
	@RequiresPermissions("sys:role:select")
	public ResultUtil select(){
		//查询列表数据
		List<RoleEntity> list = roleService.getAllList();
		return ResultUtil.ok().put("list", list);
	}
	
	/**
	 * 角色信息
	 */
	@ResponseBody
	@RequestMapping("/info/{roleId}")
	@RequiresPermissions("sys:role:info")
	public ResultUtil info(@PathVariable("roleId") Long roleId){
		RoleEntity role = roleService.getById(roleId);
		List<MenuEntity> menuList = menuService.getMenuListByRoleId(roleId);
		//查询角色对应的菜单
		List<Long> menuIdList = new ArrayList<>();
		for(MenuEntity menu : menuList) {
			menuIdList.add(menu.getMenuId());
		}
		role.setMenuIdList(menuIdList);
		
		return ResultUtil.ok().put("role", role);
	}
	
	/**
	 * 保存角色
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("sys:role:save")
	public ResultUtil save(@RequestBody RoleEntity role){
		if(StringUtils.isBlank(role.getRoleName())) return ResultUtil.error("角色名称不能为空");
		role.setRoleId(System.currentTimeMillis());
		role.setCreateTime(new Date());
		roleService.save(role);
		roleService.saveUserRole(role.getRoleId(), role.getMenuIdList());
		return ResultUtil.ok();
	}
	
	/**
	 * 修改角色
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:role:update")
	public ResultUtil update(@RequestBody RoleEntity role){
		if(StringUtils.isBlank(role.getRoleName())) return ResultUtil.error("角色名称不能为空");
		roleService.update(role);
		roleService.deleteRoleMenuByRoleId(role.getRoleId());
		roleService.saveUserRole(role.getRoleId(), role.getMenuIdList());
		return ResultUtil.ok();
	}
	
	/**
	 * 删除角色
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("sys:role:delete")
	public ResultUtil delete(@RequestBody Long[] roleIds){
		roleService.deleteBatch(roleIds);
		return ResultUtil.ok();
	}
}
