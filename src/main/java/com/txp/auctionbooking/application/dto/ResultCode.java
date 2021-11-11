package com.txp.auctionbooking.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ResultCode {

    CREATE_SUCCEED(1),
    CREATE_FAIL(-1),
    FIND_SUCCEED(2),
    TIME_OUT_FAIL(-2),
    SERVICE_UNAVAILABLE_FAIL(-3);

    int code;
}
