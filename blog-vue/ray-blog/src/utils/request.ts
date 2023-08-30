import axios, { AxiosError } from "axios";
import { getToken, token_prefix } from "./token";

const requests = axios.create({
    baseURL: '/api',//主机地址
    timeout: 10000,//超时时间 超时之后会有一次默认的重试
    headers: { "Content-Type": "application/json;charset=UTF-8", }//统一token
});

// 添加请求拦截器
requests.interceptors.request.use((config) => {
    // 在发送请求之前做些什么
    //登录时 请求头要带token，后面处理
    if (getToken()) {
        config.headers["token"] = token_prefix + getToken();
    }
    return config;
}, (error: AxiosError) => {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
requests.interceptors.response.use(function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    switch (response.data.code) {
        case -1:
            window.$message?.error(response.data.msg);
            break;
        case 401:
            window.$message?.error(response.data.msg);
            break;
        case 402:
            window.$message?.error(response.data.msg);
            break;
        case 403:
            window.$message?.error(response.data.msg);
            break;
        case 500:
            window.$message?.error(response.data.msg);
            break;
    }
    return response;
}, function (error: AxiosError) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    let { message } = error;
    if (message == "Network Error") {
        message = "后端接口连接异常";
    } else if (message.includes("timeout")) {
        message = "系统接口请求超时";
    } else if (message.includes("Request failed with status code")) {
        message = "系统接口" + message.substring(message.length - 3) + "异常";
    }
    window.$message?.error(message, { duration: 5000 });
    return Promise.reject(error);
});

// 对外暴露
export default requests;