package com.zarah.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

public class BUtils {
	public static <T> T copyParamToBean(Map map,T bean) {
		try {
			/**
			 * populate把map的值注入到User对象中
			 * Map的值是请求的参数
			 * key=value
			 * 刚好是
			 * name=value
			 * 要求请求的参数名称必须和javaBean的属性名一致<br />
			 * 前面用EL表达式取bean对象的属性值的时候，走的是读方法getXxx<br>
			 * 现在使用BeanUtils工具类给bean对象赋值的时候，走的是setXxx方法
			 */
			BeanUtils.populate(bean, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return bean;
	}
	
	/**
	 * 
	 * @Description 将字符串类型的int数据转换成为Integer类型
	 * @author zarah
	 * @date 2020年2月17日上午6:57:30
	 * @param strInt
	 * @param defaultValue
	 * @return
	 */
	public static Integer parseInt(String strInt,Integer defaultValue) {
		//手动进行try...catch..
		try {
			return Integer.parseInt(strInt);
		} catch (NumberFormatException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return defaultValue;
	}
}
