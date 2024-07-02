package com.crush.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Java 反射的工具类
 *
 * @author zhangxq
 * @date 2024/6/22
 */
@Slf4j
public class ReflectionUtils {

    public static Object getFieldValue(Object object, String propertyName) {
        try {
            if (object == null) return null;
            if (object instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) object;
                return map.get(propertyName);
            }
            Field field = getDeclaredField(object, propertyName);
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            Object result = null;
            try {
                result = field.get(object);
            }
            catch (IllegalAccessException illegalAccessException) {
            }
            field.setAccessible(accessible);
            return result;
        }
        catch (Exception e) {
            throw new RuntimeException("", e);
        }
    }

    public static void setFieldValue(Object object, String propertyName, Object newValue) {
        try {
            if (object == null) return;
            if (object instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) object;
                map.put(propertyName, newValue);
                return;
            }
            Field field = getDeclaredField(object, propertyName);
            boolean accessible = field.isAccessible();
            field.setAccessible(true);
            try {
                field.set(object, newValue);
            }
            catch (IllegalAccessException illegalAccessException) {
            }
            field.setAccessible(accessible);
        }
        catch (Exception e) {
            throw new RuntimeException("", e);
        }
    }

    public static Field getDeclaredField(Object object, String propertyName) throws NoSuchFieldException {
        return getDeclaredField(object.getClass(), propertyName);
    }

    private static Field getDeclaredField(Class<Object> clazz, String propertyName) throws NoSuchFieldException {
        for (Class<Object> superClass = clazz; superClass != Object.class; superClass = (Class) superClass.getSuperclass()) {
            try {
                return superClass.getDeclaredField(propertyName);
            }
            catch (NoSuchFieldException noSuchFieldException) {
            }
        }
        throw new NoSuchFieldException("No such field: " + clazz.getName() + '.' + propertyName);
    }

    public static Object invokePrivateMethod(Object object, String methodName, Object[] params) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Class[] types = new Class[params.length];
        for (int i = 0; i < params.length; i++)
            types[i] = params[i].getClass();
        Method method = object.getClass().getDeclaredMethod(methodName, types);
        method.setAccessible(true);
        return method.invoke(object, params);
    }

    public static Object invokePrivateMethod(Object object, String methodName, Object param) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        return invokePrivateMethod(object, methodName, new Object[]{param});
    }

    public static String fieldsToString(Object obj) {
        int levels = 0;
        Class<?> sourceClass = obj.getClass();
        while (!sourceClass.getName().equals("java.lang.Object")) {
            levels++;
            sourceClass = sourceClass.getSuperclass();
        }
        levels--;
        return p_fieldsToString(obj, levels);
    }

    private static String p_fieldsToString(Object obj, int superLevels) {
        String[] results = new String[superLevels + 1];
        StringBuffer result = new StringBuffer();
        try {
            Class<?> sourceClass = obj.getClass();
            for (int level = 0; level <= superLevels; level++) {
                Field[] fields = sourceClass.getDeclaredFields();
                for (int j = 0; j < fields.length; j++) {
                    Field f = fields[j];
                    int modifier = f.getModifiers();
                    if (!Modifier.isStatic(modifier)) {
                        f.setAccessible(true);
                        String name = f.getName();
                        if (name.indexOf("m_") == 0) name = name.substring(2);
                        Object tempObject = f.get(obj);
                        result.append(name);
                        result.append("[");
                        result.append(tempObject);
                        result.append("]");
                        if (j < fields.length - 1) result.append(",");
                        result.append(" ");
                    }
                }
                results[superLevels - level] = result.toString();
                result.setLength(0);
                sourceClass = sourceClass.getSuperclass();
            }
            for (int i = 0; i <= superLevels; i++)
                result.append(results[i]);
        }
        catch (Exception ex) {
            log.error("ReflectUtil: Problem reflecting fields", ex);
        }
        return result.toString();
    }

    public static Object forceGetProperty(Object src, String key) {
        Object o = new Object();
        try {
            Field declaredField = src.getClass().getDeclaredField(key);
            declaredField.setAccessible(true);
            o = declaredField.get(src);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

}
