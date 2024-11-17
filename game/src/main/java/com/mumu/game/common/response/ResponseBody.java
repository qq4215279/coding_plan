/*
 * Copyright 2020-2024, 木木996.
 * All Right Reserved.
 */

package com.mumu.game.common.response;

import com.mumu.game.common.ErrorCode;

/**
 * ResponseBody
 * 结果返
 * @author liuzhen
 * @version 1.0.0 2024/11/17 16:36
 */
public class ResponseBody<T> {
    /** 错误码 */
    ErrorCode errorCode = ErrorCode.SUCCESS;
    /** cmd */
    Cmd cmd;
    /** res */
    T resMsg = null;

    /**
     * 创建简单操作成功返回
     * @param cmd cmd
     * @return com.mumu.game.common.response.ResponseBody
     * @date 2024/11/17 16:51
     */
    public static ResponseBody NewSuccessResponseBody(Cmd cmd) {
        return of(ErrorCode.SUCCESS, cmd, null);
    }

    /**
     * 创建成功返回
     * @param cmd cmd
     * @param resMsg resMsg
     * @return com.mumu.game.common.response.ResponseBody
     * @date 2024/11/17 16:52
     */
    public static <T> ResponseBody NewSuccessResponseBody(Cmd cmd, T resMsg) {
        return of(ErrorCode.SUCCESS, cmd, resMsg);
    }

    /**
     * 创建自定义异常
     * @param errorCode errorCode
     * @param cmd cmd
     * @return com.mumu.game.common.response.ResponseBody
     * @date 2024/11/17 16:52
     */
    public static ResponseBody NewErrorResponseBody(ErrorCode errorCode, Cmd cmd) {
        return of(errorCode, cmd, null);
    }

    /**
     * 创建空参数异常返回
     * @param cmd cmd
     * @return com.mumu.game.common.response.ResponseBody
     * @date 2024/11/17 16:52
     */
    public static ResponseBody NewParamEmptyResponseBody(Cmd cmd) {
        return NewErrorResponseBody(ErrorCode.FAIL_PARAM_EMPTY, cmd);
    }

    /**
     * 创建空参数异常返回
     * @param cmd cmd
     * @return com.mumu.game.common.response.ResponseBody
     * @date 2024/11/17 16:52
     */
    public static ResponseBody NewParamErrorResponseBody(Cmd cmd) {
        return NewErrorResponseBody(ErrorCode.FAIL_PARAM_ERROR, cmd);
    }

    /**
     * 创建重新登陆异常返回
     * @param cmd cmd
     * @return com.mumu.game.common.response.ResponseBody
     * @date 2024/11/17 16:52
     */
    public static ResponseBody NewReLoginErrorResponseBody(Cmd cmd) {
        return NewErrorResponseBody(ErrorCode.FAIL_RE_LOGIN, cmd);
    }

    /**
     * 创建重新登陆异常返回
     * @param errorCode errorCode
     * @param cmd cmd
     * @param resMsg resMsg
     * @return com.mumu.game.common.response.ResponseBody
     * @date 2024/11/17 16:52
     */
    private static <T> ResponseBody of(ErrorCode errorCode, Cmd cmd, T resMsg) {
        ResponseBody responseBody = new ResponseBody();
        responseBody.errorCode = errorCode;
        responseBody.cmd = cmd;
        responseBody.resMsg = resMsg;
        return responseBody;
    }
}