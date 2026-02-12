package com.example.influencersponsorship.repository;

import com.example.influencersponsorship.domain.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    Page<Offer> findByInfluencerId(Long influencerId, Pageable pageable);

    Page<Offer> findByBrandId(Long brandId, Pageable pageable);
}

