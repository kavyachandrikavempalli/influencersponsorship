package com.example.influencersponsorship.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.influencersponsorship.domain.Brand;
import com.example.influencersponsorship.dto.CreateBrandRequest;
import com.example.influencersponsorship.exception.BadRequestException;
import com.example.influencersponsorship.exception.ResourceNotFoundException;
import com.example.influencersponsorship.repository.BrandRepository;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository){
        this.brandRepository=brandRepository;
    }

    public Brand create(CreateBrandRequest req){
        validate(req);

        Brand brand=new Brand();
        brand.setName(req.getName());
        brand.setBudget(req.getBudget());

        return brandRepository.save(brand);
    }

    public Page<Brand> getAll(Pageable pageable){
        return brandRepository.findAll(pageable);
    }

    public Brand getById(Long id){
        return brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id: "+id));
    }

    public void validate(CreateBrandRequest req){
        if(req.getName()==null || req.getName().isBlank()){
            throw new BadRequestException("Brand name is required.");
        }
        if(req.getBudget()==null || req.getBudget()<0){
            throw new BadRequestException("budget must be >=0");
        }
    }
}
