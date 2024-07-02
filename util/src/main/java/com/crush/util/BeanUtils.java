package com.crush.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author zhangxq
 * @since 2024/6/26
 */
@Slf4j
public class BeanUtils {
    
    public static <T> T copy(Object src, Class<T> clazz) {
        T t = null;
        try {
            if (src != null) {
                t = clazz.newInstance();
                BeanUtils.copyProperties(src, t);
            }
        }
        catch (Exception e) {
            log.error("copy object error", e);
        }
        return t;
    }
    
    public static <T> List<T> copyList(List<?> src, Class<T> clazz) {
        if (src != null) {
            List<T> list = new ArrayList<>();
            for (Object obj : src) {
                T t = copy(obj, clazz);
                list.add(t);
            }
            return list;
        }
        return new ArrayList<>();
    }
    
    public static void copy(Object src, Object to) {
        if (src == null || to == null) return;
        try {
            BeanUtils.copyProperties(src, to);
        }
        catch (Exception e) {
            log.error("copy object error", e);
        }
    }
    
    public static Map<String, Object> getObjectMapProperties(Object src) {
        if (src == null) return null;
        Map<String, Object> map = new HashMap<>();
        try {
            Class<?> clazz = src.getClass();
            Set<String> props = new HashSet<>();
            for (Class<?> superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
                Field[] fs = clazz.getDeclaredFields();
                for (int i = 0; i < fs.length; i++)
                    props.add(fs[i].getName());
            }
            for (String key : props)
                map.put(key, ReflectionUtils.forceGetProperty(src, key));
            return map;
        }
        catch (Exception e) {
            log.error("get ObjectMap Properties error", e);
            return map;
        }
    }
    
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }
    
    public static <T, V> List<V> copyPropertieses(List<T> sourceList, List<V> targetList, Class<?> clasz) {
        if (sourceList != null && sourceList.size() >= 1) {
            for (T t : sourceList) {
                V instance = null;
                try {
                    instance = (V) clasz.newInstance();
                }
                catch (InstantiationException e) {
                    e.printStackTrace();
                }
                catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                copyProperties(t, instance);
                targetList.add(instance);
            }
            return targetList;
        }
        return targetList;
    }
    
    public static <T> Set<T> copySet(Collection<?> src, Class<T> clazz) {
        if (src != null) {
            Set<T> list = new HashSet<>();
            for (Object obj : src) {
                T t = copy(obj, clazz);
                list.add(t);
            }
            return list;
        }
        return new HashSet<>();
    }
}
