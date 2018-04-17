package com.appvengers.db;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class DogLikeEntity {

    @Id(autoincrement = true)
    private Long _id;

    //Perro haciendo matches ahora mismo
    private String dogIdLiked;
    //Perro que hizo like previamente a dogIdLiked
    private String dogIdWhoLikes;

    @NotNull
    private String name;

    @Generated(hash = 623598221)
    public DogLikeEntity(Long _id, String dogIdLiked, String dogIdWhoLikes,
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

    public String getDogIdLiked() {
        return this.dogIdLiked;
    }

    public void setDogIdLiked(String dogIdLiked) {
        this.dogIdLiked = dogIdLiked;
    }

    public String getDogIdWhoLikes() {
        return this.dogIdWhoLikes;
    }

    public void setDogIdWhoLikes(String dogIdWhoLikes) {
        this.dogIdWhoLikes = dogIdWhoLikes;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
