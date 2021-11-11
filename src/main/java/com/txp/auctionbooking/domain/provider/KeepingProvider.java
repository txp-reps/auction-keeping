package com.txp.auctionbooking.domain.provider;

import com.txp.auctionbooking.application.GoodsType;

public interface KeepingProvider {
    boolean keepPlaceApply(GoodsType goodsType);
}
