package com.example.influencersponsorship.controller;

import com.example.influencersponsorship.domain.Offer;
import com.example.influencersponsorship.dto.CreateOfferRequest;
import com.example.influencersponsorship.dto.PatchOfferRequest;
import com.example.influencersponsorship.service.OfferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    // POST /offers
    @PostMapping
    public Offer create(@RequestBody CreateOfferRequest request) {
        return offerService.create(request);
    }

    // PATCH /offers/{id}
    @PatchMapping("/{id}")
    public Offer patch(@PathVariable Long id, @RequestBody PatchOfferRequest request) {
        return offerService.patchOffer(id, request);
    }

    // GET /offers?influencerId=... or /offers?brandId=...
    // plus pagination/sorting: page, size, sort
    @GetMapping
    public Page<Offer> getOffers(
            @RequestParam(required = false) Long influencerId,
            @RequestParam(required = false) Long brandId,
            @PageableDefault(size = 10) Pageable pageable
    ) {
        return offerService.getOffers(influencerId, brandId, pageable);
    }
}