package com.example.influencersponsorship.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Influencer{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String socialMediaPlatform;
    private Integer followers;
    private Double engagementRate;
    private Double totalEarnings=0.0;

    public Influencer(){

    }
    public Influencer(String name, String socialMediaplatform, Integer followers, Double engagementRate){
        this.name=name;
        this.socialMediaPlatform=socialMediaplatform;
        this.followers=followers;
        this.engagementRate=engagementRate;
        this.totalEarnings=0.0;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getSocialMediaPlatform(){
        return socialMediaPlatform;
    }

    public void setSocialMediaPlatform(String socialMediaPlatform){
        this.socialMediaPlatform=socialMediaPlatform;
    }

    public Integer getFollowers(){
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public Double getEngagementRate() {
        return engagementRate;
    }

    public void setEngagementRate(Double engagementRate) {
        this.engagementRate = engagementRate;
    }

    public Double getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(Double totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

}