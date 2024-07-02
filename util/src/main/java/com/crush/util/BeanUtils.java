package com.crush.util;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author zhangxq
 * @since 2024/6/26
 */
@Slf4j
public class BeanUtils {

    /**
     * 根据源对象复制一个目标对象的实例
     *
     * @param source 源对象
     * @param targetClass 目标对象 class
     * @return 目标对象 class 的实例
     * @param <T> 目标对象类型
     */
    public static <T> T copy(Object source, Class<T> targetClass) {
        T t = null;
        try {
            if (source != null) {
                t = targetClass.newInstance();
                BeanUtils.copyProperties(source, t);
            }
        }
        catch (Exception e) {
            log.error("copy object error", e);
        }
        return t;
    }

    /**
     * 根据源对象 List 数组复制一个目标对象 List 数组，返回的类型为 ArrayList
     *
     * @param sourceList 源对象数组
     * @param targetClass 目标对象 class
     * @return 目标对象 List 实例，类型为 ArrayList
     * @param <T> 目标对象类型
     */
    public static <T> List<T> copyList(List<?> sourceList, Class<T> targetClass) {
        if (sourceList != null) {
            List<T> list = new ArrayList<>();
            for (Object obj : sourceList) {
                T t = copy(obj, targetClass);
                list.add(t);
            }
            return list;
        }
        return new ArrayList<>();
    }

    /**
     * 对象拷贝
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copy(Object source, Object target) {
        if (source == null || target  == null) return;
        try {
            BeanUtils.copyProperties(source, target );
        }
        catch (Exception e) {
            log.error("copy object error", e);
        }
    }

    /**
     * 将 Object 对象将内部的字段转换成 Map 类型
     *
     * @param obj 实体对象
     * @return HasMap
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if (obj == null) return null;
        var map = new HashMap<String, Object>();
        try {
            var clazz = obj.getClass();
            var props = new HashSet<String>();
            for (var superClass = clazz; superClass != Object.class; superClass = superClass.getSuperclass()) {
                var fs = clazz.getDeclaredFields();
                for (var f : fs) {
                    props.add(f.getName());
                }
            }
            for (var key : props) {
                map.put(key, ReflectionUtils.forceGetProperty(obj, key));
            }
            return map;
        }
        catch (Exception e) {
            log.error("get ObjectMap Properties error", e);
            return map;
        }
    }

    /**
     * 将源对象中的值复制到目标对象
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        // BeanUtils.copyProperties(source, target);
    }

    /**
     * 将源类型的 List 复制成目标类型的 List
     *
     * @param sourceList 源类型 List
     * @param targetList 目标类型 List
     * @param tergetClass 目标类型 class
     * @return 目标类型 List 实体
     * @param <T> 源类型
     * @param <V> 目标类型
     */
    public static <T, V> List<V> copyList(List<T> sourceList, List<V> targetList, Class<?> tergetClass) {
        if (sourceList != null && !sourceList.isEmpty()) {
            for (T t : sourceList) {
                V instance = null;
                try {
                    instance = (V) tergetClass.newInstance();
                }
                catch (InstantiationException | IllegalAccessException e) {
                    // e.printStackTrace();
                    log.error("Crete instance exception!", e);
                }
                copyProperties(t, instance);
                targetList.add(instance);
            }
            return targetList;
        }
        return targetList;
    }

    /**
     * 将源 Set 复制到目标 Set 中
     *
     * @param sourceSet 源 Set 实体
     * @param targetClass 目标 class
     * @return 目标 Set 实体
     * @param <T> 目标类型
     */
    public static <T> Set<T> copySet(Collection<?> sourceSet, Class<T> targetClass) {
        if (sourceSet != null) {
            var list = new HashSet<T>();
            for (var obj : sourceSet) {
                var t = copy(obj, targetClass);
                list.add(t);
            }
            return list;
        }
        return new HashSet<>();
    }
}
