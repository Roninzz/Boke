import { Result } from "@/model";
import request from "@/utils/request";
import { AxiosPromise } from "axios";
import { Friend } from "./types";


export function getFriendList(): AxiosPromise<Result<Friend[]>> {
    return request({
        url: "/friend/getAllFriend",
        method: "get"
    });
}