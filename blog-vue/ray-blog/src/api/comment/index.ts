import { PageQuery, PageResult, Result } from "@/model";
import { AxiosPromise } from "axios";
import request from "@/utils/request";
import { CommentForm, CommentQuery, RecentComment, Reply } from "./types";
import { Comment } from "./types";

/**
 * 查看最新评论
 * @returns 最新评论
 */
export function getRecentComment(): AxiosPromise<Result<RecentComment[]>> {
    return request({
        url: "/comment/newComment",
        method: "get",
    });
}

/**
 * 添加评论
 */
export function addComment(data: CommentForm): AxiosPromise<Result<null>> {
    return request({
        url: "/comment/add",
        method: "post",
        data,
    });
}

/**
 * 查看评论列表
 * @returns 评论列表
 */
export function getCommentList(params: CommentQuery): AxiosPromise<Result<PageResult<Comment[]>>> {
    return request({
        url: "/comment/commentList",
        method: "get",
        params,
    });
}

/**
 * 查看回复评论
 * @param commentId 评论id
 * @param params 分页参数
 * @returns 回复评论列表
 */
export function getReplyList(commentId: number, params: PageQuery): AxiosPromise<Result<Reply[]>> {
    return request({
        url: `/comment/${commentId}/reply`,
        method: "get",
        params,
    });
}