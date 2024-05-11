package me.myblog.framework.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;

public class BeanCopyUtils {
    private BeanCopyUtils() {
    }

    public static <T> T copyBean(Object source, Class<T> clazz) {
        // 创建目标对象
        T target = null;
        try {
            target = clazz.getDeclaredConstructor().newInstance();
            // 实现属性copy
            BeanUtils.copyProperties(source, target);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回结果
        return target;
    }

    public static <S, T> List<T> copyBeanList(List<S> sources, Class<T> clazz) {
        return sources.stream().
                map(source -> BeanCopyUtils.copyBean(source, clazz))
                .toList();
    }
}
