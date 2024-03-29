package com.stylefeng.guns.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * 对象转换工具类
 * @date 2016年8月11日
 * @Description
 */
public class BeanUtil {
	//BeanUtilsBean
	@SuppressWarnings("rawtypes")
	public static void populate(Object bean, Map properties) throws IllegalAccessException, InvocationTargetException {

		// Do nothing unless both arguments have been specified
		if ((bean == null) || (properties == null)) {
			return;
		}
		// Loop through the property name/value pairs to be set
		Iterator entries = properties.entrySet().iterator();
		while (entries.hasNext()) {

			// Identify the property name and value(s) to be assigned
			Map.Entry entry = (Map.Entry) entries.next();
			if (entry.getValue() instanceof Long) {
				String name = (String) entry.getKey();
				if (name == null) {
					continue;
				}
				Date valueDate = new Date((Long) entry.getValue());
				// Perform the assignment for this property
				BeanUtilsBean.getInstance().setProperty(bean, name, valueDate);
			} else {
				String name = (String) entry.getKey();
				if (name == null) {
					continue;
				}
				// Perform the assignment for this property
				if (entry.getValue() != null) {
					BeanUtilsBean.getInstance().setProperty(bean, name, entry.getValue());
				}
			}

		}
	}
	/**
	 * 验证对象属性是否为空,不支持子属性
	 * @param obj
	 * @param notNullFields 属性名集合,不支持子属性
	 * @return
	 */
	public static String checkEntity(final Object obj,String... notNullFields){
		if (obj==null) {
			return "信息异常";
		}else {
			if (!ArrayUtils.isEmpty(notNullFields)) {
				Class<?> clazz = obj.getClass();
				for (int i = 0; i < notNullFields.length; i++) {
					String f = notNullFields[i];
					if (StringUtils.isNotBlank(f)) {
						Field field = ReflectionUtil.findField(clazz, f);
						Object value = ReflectionUtil.getFieldAndSetAccessible(field, obj);
						boolean res = checkFieldNotNull(value);
						if (!res) {
							return f+"不能为空";
						}
					}
				}
			}
		}
		return null;
	}
	/**
	 * 验证对象属性是否为空,为空则返回spel表达式
	 * @param obj 被验证的对象
	 * @param notNullFields spel表达式集合
	 * @return 如全部不为空,返回null
	 * "#pk","#sysUsers?.![!this]"
	 */
	public static String checkEntityBySpel(final Object obj,String... notNullFields){
		if (obj==null) {
			return "信息异常";
		}else {
			ExpressionParser parser = new SpelExpressionParser();
	        //SPEL上下文
	        StandardEvaluationContext context = new StandardEvaluationContext();
	        context.setVariable("obj", obj);
			if (!ArrayUtils.isEmpty(notNullFields)) {
				for (int i = 0; i < notNullFields.length; i++) {
					String f = notNullFields[i];
					if (StringUtils.isNotBlank(f)) {
						Object value = parser.parseExpression(f).getValue();
						boolean res = checkFieldNotNull(value);
						if (!res) {
							return f;
						}
					}
				}
			}
		}
		return null;
	}
	/**
	 * 支持子属性,用.分割
	 * @param fieldName
	 * @param obj
	 * @param parser 
	 * @param context 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static boolean checkFieldNotNull(Object value) {
		if (value == null) {
			return false;
		}
		if (value instanceof String) {
			return value.toString().length() > 0;
		}
		if (value instanceof Number || value instanceof Date || value instanceof Boolean) {
			return true;
		}
		if (value instanceof Map) {
			return !((Map) value).isEmpty();
		}
		if (value instanceof List) {
			return !((List) value).isEmpty();
		}
		if (value instanceof Set) {
			return !((List) value).isEmpty();
		}
		if (value instanceof Collection) {
			return !((Collection) value).isEmpty();
		}
		Class<?> clazz = value.getClass();
		if (clazz.isArray()) {
			return ((Object[]) value).length > 0;
		}
		return true;
	}
	
	public static boolean checkFieldValueNull(Object bean) {
        boolean result = true;
        if (bean == null) {
            return true;
        }
        Class<?> cls = bean.getClass();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                String fieldGetName = parGetName(field.getName());
                if (!checkGetMet(methods, fieldGetName)) {
                    continue;
                }
                Method fieldGetMet = cls.getMethod(fieldGetName);
                Object fieldVal = fieldGetMet.invoke(bean);
                if (fieldVal != null) {
					result = "".equals(fieldVal);
                }
            } catch (Exception e) {
                continue;
            }
        }
        return result;
    }

	/**
	 * 如果对象字段都为空，返回true,否则返回false
	 * 父类字段不作校验
	 * @param bean
	 * @return
	 */
	public static boolean checkFieldIsAllNull(Object bean) {
        boolean result = true;
        if (bean == null) {
            return true;
        }
        Class<?> cls = bean.getClass();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                String fieldGetName = parGetName(field.getName());
                if (!checkGetMet(methods, fieldGetName)) {
                    continue;
                }
                Method fieldGetMet = cls.getMethod(fieldGetName);
                Object fieldVal = fieldGetMet.invoke(bean);
                if (fieldVal != null) {
                    if ("".equals(fieldVal)) {
                        result = true;
                    } else {
                        result = false;
                        break;
                    }
                }
            } catch (Exception e) {
                continue;
            }
        }
        return result;
    }
	

    /**
     * 拼接某属性的 get方法
     *
     * @param fieldName
     * @return String
     */
    public static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 判断是否存在某属性的 get方法
     *
     * @param methods
     * @param fieldGetMet
     * @return boolean
     */
    public static boolean checkGetMet(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }

}