package com.ossrep.servicepoint.repository;

import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "Tdsp")
@Table(name = "tdsp")
public class TdspEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tdsp_id", nullable = false)
    public Long tdspId;

    @Column(name = "iso_id", nullable = false)
    public Long isoId;

    @Column(name = "code", nullable = false, unique = true)
    public String code;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "duns", unique = true)
    public String duns;

    @Column(name = "description")
    public String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    public Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    public Instant updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TdspEntity that = (TdspEntity) o;
        return Objects.equals(tdspId, that.tdspId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tdspId);
    }
}
