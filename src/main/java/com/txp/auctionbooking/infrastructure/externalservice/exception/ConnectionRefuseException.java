package com.txp.auctionbooking.infrastructure.externalservice.exception;

public class ConnectionRefuseException extends RuntimeException{
    public ConnectionRefuseException(String msg) {
        super(msg);
    }
}
