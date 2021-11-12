package com.txp.auctionbooking.domain.services;

import com.txp.auctionbooking.domain.provider.KeepingProvider;
import com.txp.auctionbooking.infrastructure.externalservice.AuctionManagerSystem;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.ResourceAccessException;

import java.util.Timer;
import java.util.TimerTask;

@Getter
@Slf4j
public class AuctionManagerSystemStatusChecker {

    private final KeepingProvider auctionManagerSystem;
    private CheckerStatus status = CheckerStatus.INIT;
    private long count;
    private final Timer timer;

    public AuctionManagerSystemStatusChecker(KeepingProvider auctionManagerSystem) {
        this.auctionManagerSystem = auctionManagerSystem;
        this.timer = new Timer();
    }


    public void start() {
        if (this.status == CheckerStatus.START) {
            return;
        }

        this.status = CheckerStatus.START;
        log.info("AuctionManagerSystemStatusChecker start");
        this.timer.schedule(new Task(),0, 2000);
        FeatureConfig.APPLY_KEEPING_TOGGLE = false;
    }

    public void close() {
        this.timer.cancel();
        status = CheckerStatus.STOP;
        count = 0;
        FeatureConfig.APPLY_KEEPING_TOGGLE = true;
        log.info("AuctionManagerSystemStatusChecker stop");
    }

    private class Task extends TimerTask {

        @Override
        public void run() {
            try {
                count++;
                auctionManagerSystem.keepPlaceApply(null);
                close();
            } catch (ResourceAccessException e) {
                log.error(e.getLocalizedMessage());
            }

        }
    }
}
