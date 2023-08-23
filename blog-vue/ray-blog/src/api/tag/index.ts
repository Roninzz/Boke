import { AxiosPromise } from "axios";
import { PageResult, Result } from "@/model";
import request from "@/utils/request";
import { Tag } from "./types";
import { Article } from "../article/types";
import { ArticleQuery } from "../article/types";

/**
 * 查看文章标签
 * @returns 文章标签
 */
export function getTagList(): AxiosPromise<Result<Tag[]>> {
    return request({
        url: "/tag/list",
        method: "get",
    });
}

/**
 * 查看标签文章
 * @returns 文章分类
 */
export function getTagArticleList(
    params: ArticleQuery
): AxiosPromise<Result<PageResult<Article[]>>> {
    return request({
        url: "/article/articleList",
        method: "get",
        params,
    });
}