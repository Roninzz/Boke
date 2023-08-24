package com.boke.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author DreamRay
 * @date 2023/8/24 17:26
 * @mood happy
 */
public class PathUtils {
    public static String generateFilePath(String fileName){
        //根据日期生成路径 2022/1/14/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
        String datePath = sdf.format(new Date());
        //uuid作为文件名
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        //后缀名和文件后缀一致
        int index = fileName.lastIndexOf(".");
        //test.jpg -> .jpg
        String fileType = fileName.substring(index);
        return new StringBuilder().append(datePath).append(uuid).append(fileType).toString();

    }
}
