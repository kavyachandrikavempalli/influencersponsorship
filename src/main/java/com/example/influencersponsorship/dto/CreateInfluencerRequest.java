package com.example.influencersponsorship.dto;

public class CreateInfluencerRequest {
    private String name;
    private String socialMediaPlatform;
    private Integer followers;
    private Double engagementRate;

    public CreateInfluencerRequest() {}

    public String getName() { 
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getSocialMediaPlatform() {
        return socialMediaPlatform;
    }

    public void setSocialMediaPlatform(String socialMediaPlatform){
        this.socialMediaPlatform=socialMediaPlatform;
    }

    public Integer getFollowers() {
        return followers;
    }
    
    public void setFollowers(Integer followers){
        this.followers=followers;
    }

    public Double getEngagementRate(){
        return engagementRate;
    }

    public void setEngagementRate(Double engagementRate){
        this.engagementRate=engagementRate;
    } 
}
