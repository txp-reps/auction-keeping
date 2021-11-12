package com.txp.auctionbooking.infrastructure.externalservice.dto;


import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ApplyRequest {
    private String goodsType;
}
