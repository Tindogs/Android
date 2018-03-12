package com.appvengers.repository.models;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DogLikeEntity {

    @Id(autoincrement = true)
    private Long _id;

    private Long dogIdLiked;
    private Long dogIdWhoLikes;

    @NotNull
    private String name;

    @Generated(hash = 1350332157)
    public DogLikeEntity(Long _id, Long dogIdLiked, Long dogIdWhoLikes,
            @NotNull String name) {
        this._id = _id;
        this.dogIdLiked = dogIdLiked;
        this.dogIdWhoLikes = dogIdWhoLikes;
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

    public Long getDogIdWhoLikes() {
        return this.dogIdWhoLikes;
    }

    public void setDogIdWhoLikes(Long dogIdWhoLikes) {
        this.dogIdWhoLikes = dogIdWhoLikes;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDogIdLiked() {
        return this.dogIdLiked;
    }

    public void setDogIdLiked(Long dogIdLiked) {
        this.dogIdLiked = dogIdLiked;
    }
}
