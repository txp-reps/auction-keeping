package com.txp.auctionbooking.infrastructure.dao;

import com.txp.auctionbooking.domain.entities.KeepingEntity;
import org.springframework.data.repository.CrudRepository;

public interface KeepingJpaDao extends CrudRepository<KeepingEntity, Long> {
}
