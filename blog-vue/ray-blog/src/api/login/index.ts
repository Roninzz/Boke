import { Result } from "@/model";
import request from "@/utils/request";
import { AxiosPromise } from "axios";
import { UserInfo } from "./types";






/**
 * 获取登录用户信息
 * @returns 登录用户信息
 */
export function getUserInfo(): AxiosPromise<Result<UserInfo>> {
    return request({
        url: "/user/userInfo",
        method: "get",
    });
}

/**
 * 用户退出
 */
export function logout(): AxiosPromise<Result<null>> {
    return request({
        url: "/logout",
        method: "post",
    });
}