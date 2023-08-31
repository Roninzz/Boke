import { Result } from "@/model";
import request from "@/utils/request";
import { AxiosPromise } from "axios";
import { UserInfo } from "./types";


/**
 * 修改用户头像
 * @param data 头像
 */
export function updateUserAvatar(data: FormData): AxiosPromise<Result<string>> {
    return request({
        url: "/upload",
        method: "post",
        headers: { "content-type": "multipart/form-data" },
        data,
    });
}

/**
 * 修改用户信息
 * @param data 用户信息
 */
export function updateUserInfo(data: UserInfo): AxiosPromise<Result<null>> {
    return request({
        url: "/user/userInfo",
        method: "put",
        data,
    });
}