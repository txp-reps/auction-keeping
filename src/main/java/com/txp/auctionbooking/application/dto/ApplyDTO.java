package com.txp.auctionbooking.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApplyDTO {
    private Integer applyId;
    private String applyType;
    private String applyStatus;
    private String placeNumber;
}
