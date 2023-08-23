import { AxiosPromise } from "axios"
import request from "@/utils/request";
import { Result, PageQuery, PageResult } from "@/model"
import { ArticleRecommend } from "./types"
import { Article } from "./types";
import { ArticleInfo } from "./types";



/**
 * 查看文章列表
 * @param params 查询条件
 * @returns 文章列表
 */
export function getArticleList(params: PageQuery): AxiosPromise<Result<PageResult<Article[]>>> {
    return request({
        url: "/article/articleList",
        method: "get",
        params,
    });
}

/**
 * 查看文章
 * @param articleId 文章id
 */
export function getArticle(articleId: number): AxiosPromise<Result<ArticleInfo>> {
    return request({
        url: `/article/${articleId}`,
        method: "get",
    });
}

/**
 * 查看推荐文章
 * @returns 推荐文章
 */
export function getArticleRecommend(): AxiosPromise<Result<ArticleRecommend[]>> {
    return request({
        url: "/article/hotArticleList",
        method: "get",
    });
}
/**
 * 查看文章归档
 * 和查询所有文章一样就是多了个按时间排序
 * @param params 查询条件
 * @returns 文章归档
 */
export function getArchivesList(params: PageQuery): AxiosPromise<Result<PageResult<ArticleRecommend[]>>> {
    return request({
        url: "/article/archivesList",
        method: "get",
        params,
    });
}