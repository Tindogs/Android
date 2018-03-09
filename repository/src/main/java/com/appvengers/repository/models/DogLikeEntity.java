package com.appvengers.repository.models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DogLikeEntity {

    @Id
    private Long _id;

    private Long dogId;

    @NotNull
    private String name;

    @Generated(hash = 1762202631)
    public DogLikeEntity(Long _id, Long dogId, @NotNull String name) {
        this._id = _id;
        this.dogId = dogId;
        this.name = name;
    }

    @Generated(hash = 1909763012)
    public DogLikeEntity() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getDogId() {
        return this.dogId;
    }

    public void setDogId(Long dogId) {
        this.dogId = dogId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
