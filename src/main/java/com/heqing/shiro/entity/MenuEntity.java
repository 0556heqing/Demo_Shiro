package com.heqing.shiro.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单管理
 */
public class MenuEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final Integer CATALOG = 0;	//目录
	public static final Integer MENU 	= 1;	//菜单
	public static final Integer BUTTON 	= 2;	//按钮

	private Long    menuId;		//菜单ID
	private Long    parentId;	//父菜单ID，一级菜单为0
	private String  name;		//名称
	private String  url;		//地址
	private String  perms;		//授权(多个用逗号分隔，如：user:list,user:create)
	private Integer type;		//类型     0：目录   1：菜单   2：按钮
	private String  icon;		//图标
	private Integer orderNum;	//排序
	private List<?> menuList;	//子菜单
	
	/**
	 * 设置：菜单ID
	 * @param parentId 菜单ID
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * 获取：菜单ID
	 * @return Long
	 */
	public Long getMenuId() {
		return menuId;
	}
	
	/**
	 * 设置：父菜单ID，一级菜单为0
	 * @param parentId 父菜单ID，一级菜单为0
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 获取：父菜单ID，一级菜单为0
	 * @return Long
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * 设置：菜单名称
	 * @param name 菜单名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取：菜单名称
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 设置：菜单URL
	 * @param url 菜单URL
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获取：菜单URL
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	
	public String getPerms() {
		return perms;
	}

	public void setPerms(String perms) {
		this.perms = perms;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 设置：菜单图标
	 * @param icon 菜单图标
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * 获取：菜单图标
	 * @return String
	 */
	public String getIcon() {
		return icon;
	}
	
	/**
	 * 设置：排序
	 * @param orderNum 排序
	 */
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * 获取：排序
	 * @return Integer
	 */
	public Integer getOrderNum() {
		return orderNum;
	}

	/**
	 * 获取：菜单列表
	 * @return List<?>
	 */
	public List<?> getMenuList() {
		return menuList;
	}

	public void setList(List<?> menuList) {
		this.menuList = menuList;
	}

}
