package com.heqing.shiro.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.heqing.shiro.entity.UserEntity;
import com.heqing.shiro.utils.ShiroUtil;

public abstract class BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected UserEntity getUser() {
		return (UserEntity) ShiroUtil.getUserEntity();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
}
