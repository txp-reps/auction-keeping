package com.txp.auctionbooking.infrastructure.externalservice;

import com.txp.auctionbooking.application.GoodsType;
import com.txp.auctionbooking.domain.provider.KeepingProvider;
import com.txp.auctionbooking.infrastructure.externalservice.dto.ApplyResponse;
import com.txp.auctionbooking.infrastructure.externalservice.dto.ApplyRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Repository
public class AuctionManagerSystem implements KeepingProvider {

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public boolean keepPlaceApply(GoodsType goodsType) {
        String applyKeepingPlaceUrl ="http://localhost:8888/apply/keeping/ ";
        ApplyRequest applyKeepingRequest = ApplyRequest.builder().goodsType(goodsType.name()).build();

        ResponseEntity<ApplyResponse> applyKeepingResponse = restTemplate.postForEntity(applyKeepingPlaceUrl, applyKeepingRequest, ApplyResponse.class);

        if (applyKeepingResponse.getStatusCode() != HttpStatus.CREATED) {
            return false;
        }
        return Objects.requireNonNull(applyKeepingResponse.getBody()).getStatus();
    }
}
