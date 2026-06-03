package com.ossrep.servicepoint.repository;

import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity(name = "ServicePoint")
@Table(name = "service_point")
public class ServicePointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_point_id", nullable = false)
    public Long servicePointId;

    @NotBlank(message = "{servicePoint.esiid.notBlank}")
    @Column(name = "esiid", nullable = false, unique = true)
    public String esiid;

    @Column(name = "street")
    public String street;

    @Column(name = "street_line2")
    public String streetLine2;

    @Column(name = "city")
    public String city;

    @Column(name = "state")
    public String state;

    @Column(name = "zip")
    public String zip;

    @Column(name = "county")
    public String county;

    @Column(name = "tdsp_duns")
    public String tdspDuns;

    @Column(name = "town_code")
    public String townCode;

    @NotNull(message = "{servicePoint.status.notNull}")
    @Column(name = "status", nullable = false)
    public String status;

    @Column(name = "premise_type")
    public String premiseType;

    @Column(name = "power_region")
    public String powerRegion;

    @Column(name = "station_code")
    public String stationCode;

    @Column(name = "station_name")
    public String stationName;

    @NotNull(message = "{servicePoint.metered.notNull}")
    @Column(name = "metered", nullable = false)
    public Boolean metered;

    @Column(name = "pending_transaction")
    public String pendingTransaction;

    @NotNull(message = "{servicePoint.polr.notNull}")
    @Column(name = "polr", nullable = false)
    public Boolean polr;

    @Column(name = "meter_type")
    public String meterType;

    @Column(name = "created_at", nullable = false, updatable = false)
    public Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    public Instant updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServicePointEntity that = (ServicePointEntity) o;
        return Objects.equals(servicePointId, that.servicePointId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(servicePointId);
    }

    @Override
    public String toString() {
        return "ServicePointEntity{" +
                "servicePointId=" + servicePointId +
                ", esiid='" + esiid + '\'' +
                ", status='" + status + '\'' +
                ", premiseType='" + premiseType + '\'' +
                ", stationCode='" + stationCode + '\'' +
                '}';
    }
}
