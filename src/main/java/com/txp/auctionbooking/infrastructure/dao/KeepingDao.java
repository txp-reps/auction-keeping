package com.txp.auctionbooking.infrastructure.dao;

import com.txp.auctionbooking.domain.entities.KeepingEntity;
import com.txp.auctionbooking.domain.repository.KeepingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class KeepingDao implements KeepingRepository {

    private final KeepingJpaDao keepingJpaDao;

    @Override
    public KeepingEntity keepPlaceApply(KeepingEntity keepingEntity) {
        return keepingJpaDao.save(keepingEntity);
    }

    @Override
    public long countApplies() {
        return keepingJpaDao.count();
    }
}
