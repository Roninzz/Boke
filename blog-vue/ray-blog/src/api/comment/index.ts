import { Result } from "@/model";
import { AxiosPromise } from "axios";
import request from "@/utils/request";
import { RecentComment } from "./types";

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