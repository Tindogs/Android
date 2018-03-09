package com.appvengers.repository.models;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity
public class UserEntity {

    @Id
    private Long _id;
    @org.greenrobot.greendao.annotation.NotNull
    private String firstName;
    @org.greenrobot.greendao.annotation.NotNull
    private String lastName;
    private String phone;
    private String mobilePhone;
    @org.greenrobot.greendao.annotation.NotNull
    private String email;
    @org.greenrobot.greendao.annotation.NotNull
    private String userName;
    private Double latitude;
    private Double longitude;

    @ToMany(referencedJoinProperty = "userId")
    private List<DogEntity> dogs;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1814575071)
    private transient UserEntityDao myDao;

    @Generated(hash = 225746991)
    public UserEntity(Long _id, String firstName, String lastName, String phone,
            String mobilePhone, String email, String userName, Double latitude,
            Double longitude) {
        this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.userName = userName;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Generated(hash = 1433178141)
    public UserEntity() {
    }

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return this.mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 694477283)
    public List<DogEntity> getDogs() {
        if (dogs == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DogEntityDao targetDao = daoSession.getDogEntityDao();
            List<DogEntity> dogsNew = targetDao._queryUserEntity_Dogs(_id);
            synchronized (this) {
                if (dogs == null) {
                    dogs = dogsNew;
                }
            }
        }
        return dogs;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1473265060)
    public synchronized void resetDogs() {
        dogs = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 287999134)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserEntityDao() : null;
    }

  
}
