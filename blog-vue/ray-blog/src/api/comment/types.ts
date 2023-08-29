

/**
 * 最近评论
 */
export interface RecentComment {
    /**
    * 评论id
    */
    id: number;
    /**
     * 昵称
     */
    nickname: string;
    /**
     * 头像
     */
    avatar: string;
    /**
     * 评论内容
     */
    commentContent: string;
    /**
     * 评论时间
     */
    createTime: string;
}