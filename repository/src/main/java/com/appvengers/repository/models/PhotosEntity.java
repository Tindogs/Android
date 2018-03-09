package com.appvengers.repository.models;

import org.greenrobot.greendao.annotation.NotNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PhotosEntity {
    @Id
    private Long dogId;
    @NotNull
    private String photo;
    @Generated(hash = 1927961985)
    public PhotosEntity(Long dogId, @NotNull String photo) {
        this.dogId = dogId;
        this.photo = photo;
    }
    @Generated(hash = 1479617457)
    public PhotosEntity() {
    }
    public Long getDogId() {
        return this.dogId;
    }
    public void setDogId(Long dogId) {
        this.dogId = dogId;
    }
    public String getPhoto() {
        return this.photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }


}
