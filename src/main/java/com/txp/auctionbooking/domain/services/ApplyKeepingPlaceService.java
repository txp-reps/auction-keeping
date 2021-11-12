package com.txp.auctionbooking.domain.services;

import com.txp.auctionbooking.application.GoodsType;
import com.txp.auctionbooking.domain.entities.KeepingEntity;
import com.txp.auctionbooking.domain.provider.KeepingProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.naming.ServiceUnavailableException;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Service
@Slf4j
public class ApplyKeepingPlaceService {

    private KeepingEntity keepingEntity;
    private AuctionManagerSystemStatusChecker checker;

    public ApplyKeepingPlaceService(KeepingEntity keepingEntity, KeepingProvider provider) {
        this.keepingEntity = keepingEntity;
        this.checker = new AuctionManagerSystemStatusChecker(provider);
    }

    public Optional<KeepingEntity> applyKeepingPlace(GoodsType type) throws ServiceUnavailableException {
        Optional<KeepingEntity> keepingEntity;
        try {
             keepingEntity = this.keepingEntity.applyKeepingPlace(type);
        } catch (ResourceAccessException  e) {
            log.error(e.getLocalizedMessage());
            checker.start();
            throw e;
        }
        return keepingEntity;
    }

    public String checkerStatus() {
        return this.checker.getStatus();
    }
}
