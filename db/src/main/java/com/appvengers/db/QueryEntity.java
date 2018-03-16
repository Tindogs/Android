package com.appvengers.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class QueryEntity {

    @Id
    private String dogId;
    private Double ageFrom;
    private Double ageTo;
    private Double maxKms;
    private Boolean reproductive;
    private String breed;
    @Generated(hash = 682104673)
    public QueryEntity(String dogId, Double ageFrom, Double ageTo, Double maxKms,
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
    public String getDogId() {
        return this.dogId;
    }
    public void setDogId(String dogId) {
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
