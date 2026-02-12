package com.example.influencersponsorship.repository;

import com.example.influencersponsorship.domain.Influencer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfluencerRepository extends JpaRepository<Influencer, Long> {
}
