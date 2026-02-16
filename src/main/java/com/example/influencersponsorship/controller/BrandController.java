package com.example.influencersponsorship.controller;

import com.example.influencersponsorship.domain.Brand;
import com.example.influencersponsorship.dto.CreateBrandRequest;
import com.example.influencersponsorship.service.BrandService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping
    public Brand create(@RequestBody CreateBrandRequest request) {
        return brandService.create(request);
    }

    @GetMapping
    public Page<Brand> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return brandService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Brand getById(@PathVariable Long id) {
        return brandService.getById(id);
    }
}