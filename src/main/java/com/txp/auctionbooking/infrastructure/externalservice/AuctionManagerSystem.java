package com.txp.auctionbooking.infrastructure.externalservice;

import com.txp.auctionbooking.application.GoodsType;
import com.txp.auctionbooking.domain.provider.KeepingProvider;
import org.springframework.stereotype.Repository;

@Repository
public class AuctionManagerSystem implements KeepingProvider {

    @Override
    public boolean keepPlaceApply(GoodsType goodsType) {
        return false;
    }
}
