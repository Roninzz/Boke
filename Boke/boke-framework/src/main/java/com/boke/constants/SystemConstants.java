package com.boke.constants;
/*
* 公共常量
* */
public class SystemConstants {
    /*
    * 文章公开
    * */
    public static final int ARTICLE_STATUS_PUBLIC = 1;
    /*
    * 文章私密
    * */
    public static final int ARTICLE_STATUS_PRIVATE = 2;
    /*
     * 文章评论可见
     * */
    public static final int ARTICLE_STATUS_NORMAL = 3;
    /*
     * 分类信息正常
     * */
    public static final String CATEGORY_STATE_NORMAL = "0";
    /*
     * 分类信息禁用
     * */
    public static final String CATEGORY_STATE_BAN = "1";

    /*
     * 分类信息正常
     * */
    public static final int IS_TOP_FALSE = 0;
    /*
     * 分类信息禁用
     * */
    public static final int IS_TOP_TRUE = 1;

    /*
     * 评论类型 1文章 2友链 3说说
     * */
    public static final int ARTICLE_COMMENT = 1;

    /*
     * 评论类型 1文章 2友链 3说说
     * */
    public static final int FRIEND_COMMENT = 2;

    /*
     * 评论类型 1文章 2友链 3说说
     * */
    public static final int TALK_COMMENT = 3;

    /*
     * 评论类型 是否有父评论，-1没有,其他数字有
     * */
    public static final int HAVE_PARENT_COMMENT = -1;

    /**
     * 默认用户昵称
     */
    public static final String USER_NICKNAME = "用户";
}
