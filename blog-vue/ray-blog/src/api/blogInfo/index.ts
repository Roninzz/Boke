import { Result } from "@/model";
import request from "@/utils/request";
import { AxiosPromise } from "axios";
import { BlogInfo } from "./types";


/**
 * 获取博客信息
 * @returns 博客信息
 */
export function getBlogInfo(): AxiosPromise<Result<BlogInfo>> {
    return request({
        url: "/",
        method: "get",
    });
}