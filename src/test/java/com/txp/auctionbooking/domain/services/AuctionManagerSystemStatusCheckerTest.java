package com.txp.auctionbooking.domain.services;

import com.txp.auctionbooking.infrastructure.externalservice.AuctionManagerSystem;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.ResourceAccessException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuctionManagerSystemStatusCheckerTest {

    @Test
    public void should_execute_repeated() throws InterruptedException {
        AuctionManagerSystem auctionManagerSystem = mock(AuctionManagerSystem.class);
        doThrow(ResourceAccessException.class).when(auctionManagerSystem).keepPlaceApply(any());

        AuctionManagerSystemStatusChecker checker = new AuctionManagerSystemStatusChecker(auctionManagerSystem);
        checker.start();

        Thread.sleep(3000);
        assertTrue(checker.getCount() >= 1);
        assertEquals(CheckerStatus.START, checker.getStatus());

        reset(auctionManagerSystem);
        when(auctionManagerSystem.keepPlaceApply(any())).thenReturn(true);

        Thread.sleep(3000);
        assertEquals(CheckerStatus.STOP, checker.getStatus());

    }

}