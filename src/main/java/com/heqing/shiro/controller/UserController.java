package com.heqing.shiro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.heqing.shiro.base.BaseController;
import com.heqing.shiro.entity.RoleEntity;
import com.heqing.shiro.entity.UserEntity;
import com.heqing.shiro.service.IRoleService;
import com.heqing.shiro.service.IUserService;
import com.heqing.shiro.utils.PageUtil;
import com.heqing.shiro.utils.ResultUtil;
import com.heqing.shiro.utils.ShiroUtil;

/**
 * 用户表相关
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IRoleService roleService;

	@ResponseBody
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	@RequiresPermissions("sys:user:test")
	public void test() {
		System.out.println("--->this is error");
	}
	
	/**
	 * 登录
	 */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResultUtil login(String username, String password, String captcha)throws IOException {
		String kaptcha = ShiroUtil.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
		if(!captcha.equalsIgnoreCase(kaptcha)) return ResultUtil.error("验证码不正确");
		
		try{
			password = new Sha256Hash(password).toHex();	//sha256加密
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			ShiroUtil.login(token);
		}catch (UnknownAccountException e) {
			return ResultUtil.error(e.getMessage());
		}catch (IncorrectCredentialsException e) {
			return ResultUtil.error(e.getMessage());
		}catch (LockedAccountException e) {
			return ResultUtil.error(e.getMessage());
		}catch (AuthenticationException e) {
			return ResultUtil.error("账户验证失败");
		}
	    
		return ResultUtil.ok();
	}
	
	/**
	 * 退出
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		ShiroUtil.logout();
		return "redirect:login.html";
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@ResponseBody
	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public ResultUtil userInfo(){
		return ResultUtil.ok().put("user", getUser());
	}
	
	/**
	 * 修改登录用户密码
	 */
	@ResponseBody
	@RequestMapping(value = "/password", method = RequestMethod.POST)
	public ResultUtil password(String password, String newPassword){
		if(StringUtils.isBlank(newPassword)) return ResultUtil.error("新密码不为能空");	
		password = new Sha256Hash(password).toHex();	
		//sha256加密
		newPassword = new Sha256Hash(newPassword).toHex();			
		//更新密码
		UserEntity user = getUser();
		if(!user.getPassword().equals(password)) return ResultUtil.error("原密码不正确");
		user.setPassword(newPassword);
		userService.update(user);
		//退出
		ShiroUtil.logout();
		return ResultUtil.ok();
	}
	
	/**
	 * 所有用户列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public ResultUtil list(String userName, Integer page, Integer rows){
		UserEntity user = new UserEntity();
		if(!StringUtils.isBlank(userName)) user.setUserName(userName);
		//查询列表数据
		List<UserEntity> userList = userService.getPageBeanByCondition(user, page, rows);
		int total = userService.getListByCondition(user).size();
		
		PageUtil pageUtil = new PageUtil(userList, total, rows, page);	
		return ResultUtil.ok().put("page", pageUtil);
	}
	
	/**
	 * 保存用户
	 */
	@ResponseBody
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public ResultUtil save(@RequestBody UserEntity user){
		if(StringUtils.isBlank(user.getUserName())) return ResultUtil.error("用户名不能为空");
		if(StringUtils.isBlank(user.getPassword())) return ResultUtil.error("密码不能为空");
		user.setUserId(System.currentTimeMillis());
		user.setCreateTime(new Date());
		//sha256加密
		user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		userService.save(user);
		userService.saveUserRole(user.getUserId(), user.getRoleIdList());
		return ResultUtil.ok();
	}
	
	/**
	 * 修改用户
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public ResultUtil update(@RequestBody UserEntity user){
		if(StringUtils.isBlank(user.getUserName())) return ResultUtil.error("用户名不能为空");
		if(StringUtils.isBlank(user.getPassword())) return ResultUtil.error("密码不能为空");
		user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		userService.update(user);
		userService.saveUserRole(user.getUserId(), user.getRoleIdList());
		return ResultUtil.ok();
	}
	
	/**
	 * 删除用户
	 */
	@ResponseBody
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public ResultUtil delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)) return ResultUtil.error("系统管理员不能删除");
		if(ArrayUtils.contains(userIds, getUserId())) return ResultUtil.error("当前用户不能删除");
		userService.deleteBatch(userIds);		
		return ResultUtil.ok();
	}
	
	/**
	 * 用户信息
	 */
	@ResponseBody
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public ResultUtil info(@PathVariable("userId") Long userId){
		UserEntity user = userService.getById(userId);	
		//获取用户所属的角色列表
		List<RoleEntity> roleList = roleService.getRoleListByUserId(userId);
		List<Long> roleIdList = new ArrayList<>();
		for(RoleEntity role : roleList) {
			roleIdList.add(role.getRoleId());
		}
		user.setRoleIdList(roleIdList);	
		return ResultUtil.ok().put("user", user);
	}
}
