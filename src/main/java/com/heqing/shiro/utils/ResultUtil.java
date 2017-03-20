package com.heqing.shiro.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 */
public class ResultUtil extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public ResultUtil() {
		put("code", 0);
	}
	
	public static ResultUtil error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static ResultUtil error(String msg) {
		return error(500, msg);
	}
	
	public static ResultUtil error(int code, String msg) {
		ResultUtil r = new ResultUtil();
		r.put("code", code);
		r.put("msg", msg);
		return r;
	}

	public static ResultUtil ok(String msg) {
		ResultUtil r = new ResultUtil();
		r.put("msg", msg);
		return r;
	}
	
	public static ResultUtil ok(Map<String, Object> map) {
		ResultUtil r = new ResultUtil();
		r.putAll(map);
		return r;
	}
	
	public static ResultUtil ok() {
		return new ResultUtil();
	}

	public ResultUtil put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
