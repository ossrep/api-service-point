package com.ossrep.servicepoint.iso;

import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "Iso")
@Table(name = "iso")
public class IsoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iso_id", nullable = false)
    public Long isoId;

    @Column(name = "code", nullable = false, unique = true)
    public String code;

    @Column(name = "name", nullable = false)
    public String name;

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
        IsoEntity that = (IsoEntity) o;
        return Objects.equals(isoId, that.isoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isoId);
    }
}
