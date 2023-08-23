package com.boke.utils;

import com.boke.domain.entity.Article;
import com.boke.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils(){}

    public static <V> V copyBean(Object source,Class<V> clazz) {
        //创建目标对象
        V result;
        try {
            result = clazz.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source, result);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        //返回结果
        return result;

    }
    public static <O,V> List<V> copyBeanList(List<O> list,Class<V> clazz) {
        //stream流
        return list.stream()
                .map(o -> copyBean(o,clazz))
                .collect(Collectors.toList());

//        for (O article : list) {
//            copyBean(article,clazz);
//            list.add(article);
//        }
//        return list;
    }
}
