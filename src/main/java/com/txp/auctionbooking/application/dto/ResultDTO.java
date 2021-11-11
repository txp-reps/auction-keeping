package com.txp.auctionbooking.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResultDTO<T> {
    private ResultCode resultCode;
    private String resultMessage;
    private T body;
}
