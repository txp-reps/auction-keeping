package com.txp.auctionbooking.infrastructure.dao;

import com.txp.auctionbooking.domain.entities.KeepingEntity;
import com.txp.auctionbooking.domain.repository.KeepingRepository;
import org.springframework.stereotype.Repository;

@Repository
public class KeepingDao implements KeepingRepository {

    @Override
    public KeepingEntity keepPlaceApply(KeepingEntity keepingEntity) {
        return null;
    }
}
