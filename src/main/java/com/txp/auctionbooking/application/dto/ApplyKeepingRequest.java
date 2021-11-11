package com.txp.auctionbooking.application.dto;


import com.txp.auctionbooking.application.GoodsType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyKeepingRequest {
    private GoodsType goodsType;
}
