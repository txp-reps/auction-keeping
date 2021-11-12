package com.txp.auctionbooking.infrastructure.externalservice;

import com.github.dreamhead.moco.HttpServer;
import com.txp.auctionbooking.application.GoodsType;
import com.txp.auctionbooking.infrastructure.externalservice.dto.ApplyResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import static com.github.dreamhead.moco.Moco.*;
import static com.github.dreamhead.moco.Runner.running;
import static org.junit.jupiter.api.Assertions.*;

class AuctionManagerSystemTest {
    HttpServer httpServer;

    @BeforeEach
    void setUp() {
        // given
        httpServer = httpServer(8888);
    }
    @Test
    void should_apply_succeed_from_auction_manager_system() throws Exception {
        // given
        httpServer.post(by(uri("/apply/keeping")))
                .response(status(HttpStatus.CREATED.value()), json(ApplyResponse.builder().status(true).build()));

        running(httpServer, () -> {
            // when
            AuctionManagerSystem auctionManagerSystem = new AuctionManagerSystem(new RestTemplate());
            boolean status = auctionManagerSystem.keepPlaceApply(GoodsType.Car);

            // then
            assertTrue(status);
        });
    }
}