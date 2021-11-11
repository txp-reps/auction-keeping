package com.txp.auctionbooking.domain.entities;

import com.txp.auctionbooking.application.GoodsType;
import com.txp.auctionbooking.domain.provider.KeepingProvider;
import com.txp.auctionbooking.domain.repository.KeepingRepository;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "keeping")
public class KeepingEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long applyId;

    @Column(nullable = false)
    private String applyStatus;

    @Column(nullable = false)
    private String applyType;

    @Column(nullable = false)
    private String placeNumber;


    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;


    @Transient
    private KeepingRepository repository;

    @Transient
    private KeepingProvider provider;

    public Optional<KeepingEntity> applyKeepingPlace(GoodsType goodsType) {
        if (!provider.keepPlaceApply(goodsType)) {
           return Optional.empty();
        }

        KeepingEntity keepingEntity = KeepingEntity.builder()
                .applyType("keeping")
                .applyStatus("applying")
                .placeNumber(null)
                .applyId(System.currentTimeMillis())
                .build();

        return Optional.of(repository.keepPlaceApply(keepingEntity));
    }


}
