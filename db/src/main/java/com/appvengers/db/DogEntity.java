package com.appvengers.db;

import org.greenrobot.greendao.annotation.NotNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(

        // Flag to make an entity "active": Active entities have update,
        // delete, and refresh methods.
        active = true


)
public class DogEntity {
    @Id
    String _id;

    @NotNull
    private String userId;
    @NotNull
    private String name;
    private Double age;
    private String breed;
    private Boolean pureBreed;
    private String color;
    private String description;

    @ToMany(referencedJoinProperty = "dogId")
    private
    List<PhotosEntity> photos;

    @ToOne(joinProperty = "_id")
    private QueryEntity query;

    @ToMany(referencedJoinProperty = "dogIdLiked")
    private List<DogLikeEntity> likesFromOthers;

/** Used to resolve relations */
@Generated(hash = 2040040024)
private transient DaoSession daoSession;

/** Used for active entity operations. */
@Generated(hash = 774985955)
private transient DogEntityDao myDao;

@Generated(hash = 1705888025)
public DogEntity(String _id, @NotNull String userId, @NotNull String name,
        Double age, String breed, Boolean pureBreed, String color,
        String description) {
    this._id = _id;
    this.userId = userId;
    this.name = name;
    this.age = age;
    this.breed = breed;
    this.pureBreed = pureBreed;
    this.color = color;
    this.description = description;
}

@Generated(hash = 1300499357)
public DogEntity() {
}

public String get_id() {
    return this._id;
}

public void set_id(String _id) {
    this._id = _id;
}

public String getUserId() {
    return this.userId;
}

public void setUserId(String userId) {
    this.userId = userId;
}

public String getName() {
    return this.name;
}

public void setName(String name) {
    this.name = name;
}

public Double getAge() {
    return this.age;
}

public void setAge(Double age) {
    this.age = age;
}

public String getBreed() {
    return this.breed;
}

public void setBreed(String breed) {
    this.breed = breed;
}

public Boolean getPureBreed() {
    return this.pureBreed;
}

public void setPureBreed(Boolean pureBreed) {
    this.pureBreed = pureBreed;
}

public String getColor() {
    return this.color;
}

public void setColor(String color) {
    this.color = color;
}

public String getDescription() {
    return this.description;
}

public void setDescription(String description) {
    this.description = description;
}

@Generated(hash = 910160324)
private transient String query__resolvedKey;

/** To-one relationship, resolved on first access. */
@Generated(hash = 283225595)
public QueryEntity getQuery() {
    String __key = this._id;
    if (query__resolvedKey == null || query__resolvedKey != __key) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        QueryEntityDao targetDao = daoSession.getQueryEntityDao();
        QueryEntity queryNew = targetDao.load(__key);
        synchronized (this) {
            query = queryNew;
            query__resolvedKey = __key;
        }
    }
    return query;
}

/** called by internal mechanisms, do not call yourself. */
@Generated(hash = 1262011069)
public void setQuery(QueryEntity query) {
    synchronized (this) {
        this.query = query;
        _id = query == null ? null : query.getDogId();
        query__resolvedKey = _id;
    }
}

/**
 * To-many relationship, resolved on first access (and after reset).
 * Changes to to-many relations are not persisted, make changes to the target entity.
 */
@Generated(hash = 1532552242)
public List<PhotosEntity> getPhotos() {
    if (photos == null) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        PhotosEntityDao targetDao = daoSession.getPhotosEntityDao();
        List<PhotosEntity> photosNew = targetDao._queryDogEntity_Photos(_id);
        synchronized (this) {
            if (photos == null) {
                photos = photosNew;
            }
        }
    }
    return photos;
}

/** Resets a to-many relationship, making the next get call to query for a fresh result. */
@Generated(hash = 781103891)
public synchronized void resetPhotos() {
    photos = null;
}

/**
 * To-many relationship, resolved on first access (and after reset).
 * Changes to to-many relations are not persisted, make changes to the target entity.
 */
@Generated(hash = 953597484)
public List<DogLikeEntity> getLikesFromOthers() {
    if (likesFromOthers == null) {
        final DaoSession daoSession = this.daoSession;
        if (daoSession == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        DogLikeEntityDao targetDao = daoSession.getDogLikeEntityDao();
        List<DogLikeEntity> likesFromOthersNew = targetDao
                ._queryDogEntity_LikesFromOthers(_id);
        synchronized (this) {
            if (likesFromOthers == null) {
                likesFromOthers = likesFromOthersNew;
            }
        }
    }
    return likesFromOthers;
}

/** Resets a to-many relationship, making the next get call to query for a fresh result. */
@Generated(hash = 1176434628)
public synchronized void resetLikesFromOthers() {
    likesFromOthers = null;
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
@Generated(hash = 1374779750)
public void __setDaoSession(DaoSession daoSession) {
    this.daoSession = daoSession;
    myDao = daoSession != null ? daoSession.getDogEntityDao() : null;
}


 }
