package com.appvengers.db;

import org.greenrobot.greendao.annotation.NotNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class PhotosEntity {

    @Id(autoincrement = true)
    private Long _id;

    private String dogId;
    @NotNull
    private String photo;
    @Generated(hash = 1424223146)
    public PhotosEntity(Long _id, String dogId, @NotNull String photo) {
        this._id = _id;
        this.dogId = dogId;
        this.photo = photo;
    }
    @Generated(hash = 1479617457)
    public PhotosEntity() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getDogId() {
        return this.dogId;
    }
    public void setDogId(String dogId) {
        this.dogId = dogId;
    }
    public String getPhoto() {
        return this.photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
