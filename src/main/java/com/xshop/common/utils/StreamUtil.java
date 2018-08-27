package com.xshop.common.utils;


import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  
 * @program: xshop
 * @Date: 2018/8/25 17:06 
 * @Author: xuhao 
 * @Description: 关于Stream的操作工具类
 **/

public class StreamUtil {



    /**
     * 获取List<Bean> 中某个参数的和
     * @param list List<Bean>
     * @param function Bean::getId
     * @param <T> Bean
     * @return int
     */
    public static <T> int listParamSum(List<T> list, Function<T, Integer> function) {
        return list.stream().map((t) -> function.apply(t)).reduce(0,Integer::sum);
    }


    /**
     * 数组转List
     * @param arrays 数组
     * @param <T> Bean
     * @return List<Bean>
     */
    public static <T> List<T> changeArrayToList(T[] arrays) {
        return (List)(Objects.isNull(arrays) ? new ArrayList() : (List) Stream.of(arrays).collect(Collectors.toList()));
    }

    /**
     * 将对象转换为其他对象
     * @param list List<Bean>
     * @param function 处理方法
     * @param <T>  <Bean>
     * @param <R>  <Bean1>
     * @return
     */
    public static <T, R> List<R> collectFieldToList(List<T> list, Function<T, R> function) {
        return (List)(CollectionUtils.isEmpty(list) ? new ArrayList() : (List)list.stream().
                map((t) -> function.apply(t))
                .collect(Collectors.toList()));
    }

    /**
     * 将List 转为 Map
     * @param list  原List<Bean>
     * @param key  处理Key的方法
     * @param value 处理Value的方法
     * @param <T>   <Bean>
     * @param <K>   <KeyBean>
     * @param <V>   <ValueBean>
     * @return
     */
    public static <T, K, V> Map<K, V> collectFieldToMap(List<T> list, Function<T, K> key, Function<T, V> value) {
        Map<K, V> map = new HashMap();
        if (CollectionUtils.isEmpty(list)) {
            return map;
        } else {
            list.forEach((t) -> {
                map.put(key.apply(t), value.apply(t));
            });
            return map;
        }
    }

    /**
     * 将List<Bean>转为Map<Bean1,Bean>
     * @param list List<Bean>
     * @param key Bean::getId
     * @param <T> Bean
     * @param <K> Bean1
     * @return Map<Bean1,Bean>
     */
    public static <T, K> Map<K, T> collectFieldToMap(List<T> list, Function<T, K> key) {
        Map<K, T> map = new HashMap();
        if (CollectionUtils.isEmpty(list)) {
            return map;
        } else {
            list.forEach((t) -> {
                map.put(key.apply(t), t);
            });
            return map;
        }
    }

    /**
     * 将List<Bean> 转为 Map<Bean1,List<Bean>>
     * @param list List<Bean>
     * @param key Bean::getId
     * @param <T> Bean
     * @param <K> Bean1
     * @return
     */
    public static <T, K> Map<K, List<T>> collectFieldToMapList(List<T> list, Function<T, K> key) {
        Map<K, List<T>> map = new HashMap();
        if (CollectionUtils.isEmpty(list)) {
            return map;
        } else {
            list.forEach((t) -> {
                List<T> valueList = (List)map.get(key.apply(t));
                if (CollectionUtils.isEmpty(valueList)) {
                    List<T> valueListx = new ArrayList();
                    valueListx.add(t);
                    map.put(key.apply(t), valueListx);
                } else {
                    valueList.add(t);
                }

            });
            return map;
        }
    }

    /**
     * 迭代
     * @param t 迭代初始值
     * @param function 迭代方法
     * @param num 迭代次数
     * @param <T> 类型
     * @return
     */
    public static <T> List<T> iterate(T t, Function<T, T> function, int num) {
        return Stream.iterate(t,(k) -> function.apply(k)).limit(num).collect(Collectors.toList());
    }


    /**
     * 生成随机数
     * @param limit 生成数量
     * @return
     */
    public static List<Double> generate(int limit) {
        return Stream.generate(Math::random).limit(limit).collect(Collectors.toList());
    }


}
