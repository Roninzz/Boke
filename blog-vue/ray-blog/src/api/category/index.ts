import { Result, PageResult } from "@/model";
import request from "@/utils/request";
import { AxiosPromise } from "axios";
import { Category } from "./types";
import { Article } from "../article/types";
import { ArticleQuery } from "../article/types";
/**
 * 查看文章分类
 * @returns 文章分类
 */
export function getCategoryList(): AxiosPromise<Result<Category[]>> {
    return request({
        url: "/category/getCategoryList",
        method: "get",
    });
}

/**
 * 查看标签文章
 * @returns 文章分类
 */
export function getCategoryArticleList(
    params: ArticleQuery
): AxiosPromise<Result<PageResult<Article[]>>> {
    return request({
        url: "/article/articleList",
        method: "get",
        params,
    });
}