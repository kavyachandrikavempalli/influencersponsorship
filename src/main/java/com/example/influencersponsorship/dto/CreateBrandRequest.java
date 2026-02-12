package com.example.influencersponsorship.dto;

public class CreateBrandRequest {
    private String name;
    private Double budget;

    public CreateBrandRequest() {}

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public Double getBudget(){
        return budget;
    }

    public void setBudget(Double budget){
        this.budget=budget;
    }
    
}
