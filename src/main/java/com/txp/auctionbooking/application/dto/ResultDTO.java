package com.txp.auctionbooking.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultDTO<T> {
    private ResultCode resultCode;
    private String resultMessage;
    private T body;

    public static<T> ResultDTO<T> ok(T t) {
        return ResultDTO.<T>builder()
                .resultCode(ResultCode.CREATE_SUCCEED)
                .resultMessage("创建成功")
                .body(t)
                .build();
    }

    public static<T> ResultDTO<T> ok(T t, ResultCode code) {
        return ResultDTO.<T>builder()
                .resultCode(code)
                .resultMessage("创建成功")
                .body(t)
                .build();
    }

    public static<T> ResultDTO<T> fail(T t, ResultCode code) {
        return ResultDTO.<T>builder()
                .resultCode(code)
                .resultMessage("创建成功")
                .body(t)
                .build();
    }
}
