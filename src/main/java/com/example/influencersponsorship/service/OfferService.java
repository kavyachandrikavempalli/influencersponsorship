package com.example.influencersponsorship.service;


import org.antlr.v4.runtime.BaseErrorListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.influencersponsorship.domain.Brand;
import com.example.influencersponsorship.domain.Influencer;
import com.example.influencersponsorship.domain.Offer;
import com.example.influencersponsorship.domain.OfferStatus;
import com.example.influencersponsorship.dto.CreateOfferRequest;
import com.example.influencersponsorship.dto.PatchOfferRequest;
import com.example.influencersponsorship.exception.BadRequestException;
import com.example.influencersponsorship.exception.ResourceNotFoundException;
import com.example.influencersponsorship.repository.BrandRepository;
import com.example.influencersponsorship.repository.InfluencerRepository;
import com.example.influencersponsorship.repository.OfferRepository;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final InfluencerRepository influencerRepository;
    private final BrandRepository brandRepository;

    public OfferService(OfferRepository offerRepository, InfluencerRepository influencerRepository, BrandRepository brandRepository){
        this.offerRepository=offerRepository;
        this.influencerRepository=influencerRepository;
        this.brandRepository=brandRepository;
    }

    public Offer create(CreateOfferRequest req){
        validateCreate(req);

        Influencer influencer=influencerRepository.findById(req.getInfluencerId())
                                .orElseThrow(() -> new ResourceNotFoundException("Influencer not found with id: "+req.getInfluencerId()));
        
        Brand brand=brandRepository.findById(req.getBrandId())
                                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: "+ req.getBrandId()));
        
        Offer offer=new Offer();
        offer.setInfluencer(influencer);
        offer.setBrand(brand);
        offer.setMoneyAmount(req.getMoneyAmount());
        offer.setStatus(OfferStatus.PENDING);

        return offerRepository.save(offer);
    }

    public Page<Offer> getOffers(Long influencerId, Long brandId, Pageable pageable) {
        if(influencerId!=null && brandId!=null){
            throw new BadRequestException("Provide either influencerId or brandId, not both.");
        }
        if(influencerId!=null){
            return offerRepository.findByInfluencerId(influencerId, pageable);
        }
        if(brandId!=null){
            return offerRepository.findByBrandId(brandId, pageable);
        }

        throw new BadRequestException("Provide influencerId or brandId.");
    }

    @Transactional
    public Offer patchOffer(Long offerId, PatchOfferRequest req){
        if(req.getAction() == null || req.getAction().isBlank()){
            throw new BadRequestException("action is required (ACCEPT or REJECT).");
        }

        Offer offer=offerRepository.findById(offerId)
                        .orElseThrow(() -> new ResourceNotFoundException("Offer not found with id: "+offerId));
                        
        if(offer.getStatus()!=OfferStatus.PENDING){
            throw new BadRequestException("Only PENDING offers can be updated. Current status: "+offer.getStatus());
        }

        String action=req.getAction().trim().toUpperCase();

        if("REJECT".equals(action)){
            offer.setStatus(OfferStatus.REJECTED);
            return offerRepository.save(offer);
        }

        if("ACCEPT".equals(action)) {
            Brand brand=offer.getBrand();
            Influencer influencer=offer.getInfluencer();

            double amount=offer.getMoneyAmount() == null ? 0.0 : offer.getMoneyAmount();

            if(amount<=0){
                throw new BadRequestException("moneyAmount must be > 0 to accept an offer.");
            }
            if (brand.getBudget() == null) brand.setBudget(0.0);
            if (brand.getBudget() < amount) {
                throw new BadRequestException("Brand does not have enough budget. Remaining: "+brand.getBudget());
            }

            brand.setBudget(brand.getBudget() - amount);

            if(influencer.getTotalEarnings() == null) influencer.setTotalEarnings(0.0);
            influencer.setTotalEarnings(influencer.getTotalEarnings() + amount);

            offer.setStatus(OfferStatus.ACCEPTED);

            brandRepository.save(brand);
            influencerRepository.save(influencer);
            return offerRepository.save(offer);
        }
        throw new BadRequestException("Invalid action. Use ACCEPT or REJECT.");
    }
    
    private void validateCreate(CreateOfferRequest req) {
        if (req.getInfluencerId() == null) {
            throw new BadRequestException("influencerId is required.");
        }
        if (req.getBrandId() == null) {
            throw new BadRequestException("brandId is required.");
        }
        if (req.getMoneyAmount() == null || req.getMoneyAmount() <= 0) {
            throw new BadRequestException("moneyAmount must be > 0.");
        }
    }
}
