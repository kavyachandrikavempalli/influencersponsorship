package com.example.influencersponsorship.controller;

import com.example.influencersponsorship.domain.Influencer;
import com.example.influencersponsorship.dto.CreateInfluencerRequest;
import com.example.influencersponsorship.service.InfluencerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/influencers")
public class InfluencerController {

    private final InfluencerService influencerService;

    public InfluencerController(InfluencerService influencerService) {
        this.influencerService = influencerService;
    }

    // POST /influencers
    @PostMapping
    public Influencer create(@RequestBody CreateInfluencerRequest request) {
        return influencerService.create(request);
    }

    // GET /influencers?page=&size=&sort=
    @GetMapping
    public Page<Influencer> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return influencerService.getAll(pageable);
    }

    // Optional but useful:
    // GET /influencers/{id}
    @GetMapping("/{id}")
    public Influencer getById(@PathVariable Long id) {
        return influencerService.getById(id);
    }
}

