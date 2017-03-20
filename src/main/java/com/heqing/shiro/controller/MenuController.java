package com.heqing.shiro.controller;

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
import com.heqing.shiro.service.IMenuService;
import com.heqing.shiro.utils.PageUtil;
import com.heqing.shiro.utils.RRExceptionUtil;
import com.heqing.shiro.utils.ResultUtil;

/**
 * 系统菜单
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

	@Autowired
	private IMenuService menuService;
	
	/**
	 * 用户菜单列表
	 */
	@ResponseBody
	@RequestMapping("/getMenuListByCatalog")
	public ResultUtil getMenuListByCatalog(){
		List<MenuEntity> menuList = menuService.getMenuListByUserId(getUserId());
		return ResultUtil.ok().put("menuList", menuList);
	}
	
	/**
	 * 用户所有菜单列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:menu:list")
	public ResultUtil list(Integer page, Integer rows){
		List<MenuEntity> menuList = menuService.getPageBean(page, rows);
		int total = menuService.getAllList().size();
		PageUtil pageUtil = new PageUtil(menuList, total, rows, page);	
		return ResultUtil.ok().put("page", pageUtil);
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@ResponseBody
	@RequestMapping("/select")
	@RequiresPermissions("sys:menu:select")
	public ResultUtil select(){
		//查询列表数据
		List<MenuEntity> menuList = menuService.getMenuListNotButton();
		//添加顶级菜单
		MenuEntity root = new MenuEntity();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		menuList.add(root);
		return ResultUtil.ok().put("menuList", menuList);
	}
	
	/**
	 * 角色授权菜单
	 */
	@ResponseBody
	@RequestMapping("/perms")
	@RequiresPermissions("sys:menu:perms")
	public ResultUtil perms(){
		//查询列表数据
		List<MenuEntity> menuList = menuService.getAllList();	
		return ResultUtil.ok().put("menuList", menuList);
	}
	
	/**
	 * 菜单信息
	 */
	@ResponseBody
	@RequestMapping("/info/{menuId}")
	@RequiresPermissions("sys:menu:info")
	public ResultUtil info(@PathVariable("menuId") Long menuId){
		MenuEntity menu = menuService.getById(menuId);
		return ResultUtil.ok().put("menu", menu);
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("sys:menu:save")
	public ResultUtil save(@RequestBody MenuEntity menu){
		//数据校验
		verifyForm(menu);
		menuService.save(menu);
		return ResultUtil.ok();
	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:menu:update")
	public ResultUtil update(@RequestBody MenuEntity menu){
		//数据校验
		verifyForm(menu);
		menuService.update(menu);	
		return ResultUtil.ok();
	}
	
	/**
	 * 删除
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("sys:menu:delete")
	public ResultUtil delete(@RequestBody Long[] menuIds){
		for(Long menuId : menuIds){
			if(menuId.longValue() <= 26){
				return ResultUtil.error("系统菜单，不能删除");
			}
		}
		menuService.deleteBatch(menuIds);
		return ResultUtil.ok();
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(MenuEntity menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new RRExceptionUtil("菜单名称不能为空");
		}
		
		if(menu.getParentId() == null){
			throw new RRExceptionUtil("上级菜单不能为空");
		}
		
		//菜单
		if(menu.getType() == MenuEntity.MENU){
			if(StringUtils.isBlank(menu.getUrl())){
				throw new RRExceptionUtil("菜单URL不能为空");
			}
		}
		
		//上级菜单类型
		int parentType = MenuEntity.CATALOG;
		if(menu.getParentId() != 0){
			MenuEntity parentMenu = menuService.getById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		
		//目录、菜单
		if(menu.getType() == MenuEntity.CATALOG || menu.getType() == MenuEntity.MENU){
			if(parentType != MenuEntity.CATALOG){
				throw new RRExceptionUtil("上级菜单只能为目录类型");
			}
			return ;
		}
		
		//按钮
		if(menu.getType() == MenuEntity.BUTTON){
			if(parentType != MenuEntity.MENU){
				throw new RRExceptionUtil("上级菜单只能为菜单类型");
			}
			return ;
		}
	}
}
