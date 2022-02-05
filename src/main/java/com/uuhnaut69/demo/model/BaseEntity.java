package com.uuhnaut69.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Column(nullable = false, updatable = false)
    private Instant createdDate;

    private Instant updatedDate;

    @PrePersist
    public void prePersist() {
        this.createdDate = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedDate = Instant.now();
    }
}
