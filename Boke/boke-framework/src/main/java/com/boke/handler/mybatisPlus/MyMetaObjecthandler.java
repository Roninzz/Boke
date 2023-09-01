package com.boke.handler.mybatisPlus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.boke.utils.SecurityUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author DreamRay
 * @date 2023/8/23 18:26
 * @mood happy
 */
@Component
public class MyMetaObjecthandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = null;
        try{
            userId = Long.valueOf(SecurityUtils.getUserId());
        }catch (Exception e){
            e.printStackTrace();
            userId = -1L;//表示自己创建
        }
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("createBy",userId,metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("updateBy",userId,metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
        this.setFieldValByName("",SecurityUtils.getUserId(),metaObject);
    }
}
