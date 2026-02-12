package com.example.influencersponsorship.dto;

public class CreateOfferRequest {

    private Long influencerId;
    private Long brandId;
    private Double moneyAmount;

    public CreateOfferRequest(){

    }

    public Long getInfluencerId() {
        return influencerId;
    }

    public void setInfluencerId(Long influencerId){
        this.influencerId=influencerId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId=brandId;
    }

    public Double getMoneyAmount(){
        return moneyAmount;
    }

    public void setMoneyAmount(){
        this.moneyAmount=moneyAmount;
    }
    
}
