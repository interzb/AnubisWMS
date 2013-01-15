package com.inventory.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {
	public static String geteMessage(HttpServletRequest r,String key){
		return r.getAttribute(key) == null ?" ": r.getAttribute(key).toString();
	}
}
