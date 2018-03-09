package com.appvengers.repository.models;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class QueryEntity {

    @Id
    private Long dogId;
    private Double ageFrom;
    private Double ageTo;
    private Double maxKms;
    private Boolean reproductive;
    private String breed;
    @Generated(hash = 1189157516)
    public QueryEntity(Long dogId, Double ageFrom, Double ageTo, Double maxKms,
            Boolean reproductive, String breed) {
        this.dogId = dogId;
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
        this.maxKms = maxKms;
        this.reproductive = reproductive;
        this.breed = breed;
    }
    @Generated(hash = 2053696084)
    public QueryEntity() {
    }
    public Long getDogId() {
        return this.dogId;
    }
    public void setDogId(Long dogId) {
        this.dogId = dogId;
    }
    public Double getAgeFrom() {
        return this.ageFrom;
    }
    public void setAgeFrom(Double ageFrom) {
        this.ageFrom = ageFrom;
    }
    public Double getAgeTo() {
        return this.ageTo;
    }
    public void setAgeTo(Double ageTo) {
        this.ageTo = ageTo;
    }
    public Double getMaxKms() {
        return this.maxKms;
    }
    public void setMaxKms(Double maxKms) {
        this.maxKms = maxKms;
    }
    public Boolean getReproductive() {
        return this.reproductive;
    }
    public void setReproductive(Boolean reproductive) {
        this.reproductive = reproductive;
    }
    public String getBreed() {
        return this.breed;
    }
    public void setBreed(String breed) {
        this.breed = breed;
    }


}
