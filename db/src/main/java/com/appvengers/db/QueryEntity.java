package com.appvengers.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class QueryEntity {

    @Id
    private String dogId;
    private Double age;

    private Double maxKms;
    private Boolean reproductive;
    private String breed;
    @Generated(hash = 964367373)
    public QueryEntity(String dogId, Double age, Double maxKms,
            Boolean reproductive, String breed) {
        this.dogId = dogId;
        this.age = age;
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
    public Double getAge() {
        return this.age;
    }
    public void setAge(Double age) {
        this.age = age;
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
