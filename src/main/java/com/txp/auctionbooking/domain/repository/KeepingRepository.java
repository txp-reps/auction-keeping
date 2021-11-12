package com.txp.auctionbooking.domain.repository;

import com.txp.auctionbooking.domain.entities.KeepingEntity;

public interface KeepingRepository {
    KeepingEntity keepPlaceApply(KeepingEntity keepingEntity);

    long countApplies() ;
}
