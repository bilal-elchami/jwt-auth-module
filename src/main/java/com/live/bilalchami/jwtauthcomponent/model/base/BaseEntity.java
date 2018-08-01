package com.live.bilalchami.jwtauthcomponent.model.base;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private boolean isActive;

    @Temporal(TemporalType.DATE)
    private Date createdOn;

    @Temporal(TemporalType.DATE)
    private Date modifiedOn;

    @PrePersist
    public void initTimeStamps() {
        if (createdOn == null) {
            createdOn = new Date();
        }
        modifiedOn = createdOn;
    }

    @PreUpdate
    public void updateTimeStamp() {
        modifiedOn = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        BaseEntity other = (BaseEntity) obj;
        return Objects.equals(this.getId(), other.getId());
    }

}
