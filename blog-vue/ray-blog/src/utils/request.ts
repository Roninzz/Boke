import axios, { AxiosError } from "axios";

const requests = axios.create({
    baseURL: '/api',//主机地址
    timeout: 10000,//超时时间 超时之后会有一次默认的重试
    // headers: { 'token': 'Bearer Token' }//统一token
});

// 添加请求拦截器
requests.interceptors.request.use((config) => {
    // 在发送请求之前做些什么
    //登录时 请求头要带token，后面处理
    return config;
}, (error: AxiosError) => {
    // 对请求错误做些什么
    return Promise.reject(error);
});

// 添加响应拦截器
requests.interceptors.response.use(function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    return response;
}, function (error: AxiosError) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error);
});

// 对外暴露
export default requests;