package com.example.influencersponsorship.service;

import com.example.influencersponsorship.exception.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.influencersponsorship.domain.Influencer;
import com.example.influencersponsorship.dto.CreateInfluencerRequest;
import com.example.influencersponsorship.exception.ResourceNotFoundException;
import com.example.influencersponsorship.repository.InfluencerRepository;

@Service
public class InfluencerService {

    private final InfluencerRepository influencerRepository;

    public InfluencerService(InfluencerRepository influencerRepository){
        this.influencerRepository=influencerRepository;
    }
    
    public Influencer create(CreateInfluencerRequest req){
        validate(req);

        Influencer influencer=new Influencer();
        influencer.setName(req.getName());
        influencer.setSocialMediaPlatform(req.getSocialMediaPlatform());
        influencer.setFollowers(req.getFollowers());
        influencer.setEngagementRate(req.getEngagementRate());
        influencer.setTotalEarnings(0.0);

        return influencerRepository.save(influencer);

    }

    public Page<Influencer> getAll(Pageable pageable){
        return influencerRepository.findAll(pageable);
    }

    public Influencer getById(Long id){
        return influencerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Influencer not found with id: "+id));
    }

    private void validate(CreateInfluencerRequest req){
        if(req.getName() == null || req.getName().isBlank()){
            throw new BadRequestException("Influencer name is required.");
        }

        if(req.getSocialMediaPlatform()==null || req.getSocialMediaPlatform().isBlank()){
            throw new BadRequestException("socialMediaPlatform is required.");
        }

        if(req.getFollowers()==null || req.getFollowers()<0){
            throw new BadRequestException("followers must be>=0.");
        }

        if(req.getEngagementRate()==null || req.getEngagementRate()<0 || req.getEngagementRate()>100){
            throw new BadRequestException("engagementRate must be between 0 and 100.");
        }
    }
}
