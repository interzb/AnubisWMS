package com.inventory.util;

import java.text.SimpleDateFormat;

public class ValidationUtil {
	public static boolean isValidString(String input){
		return input!=null && input.trim().length()>0;
	}
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
}
