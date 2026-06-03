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

    @Column(name = "tdsp_id")
    public Long tdspId;

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

    @Column(name = "meter_read_cycle")
    public String meterReadCycle;

    @NotNull(message = "{servicePoint.status.notNull}")
    @Column(name = "status", nullable = false)
    public String status;

    @Column(name = "premise_type")
    public String premiseType;

    @Column(name = "station_code")
    public String stationCode;

    @Column(name = "station_name")
    public String stationName;

    @NotNull(message = "{servicePoint.metered.notNull}")
    @Column(name = "metered", nullable = false)
    public Boolean metered;

    @Column(name = "open_service_orders")
    public String openServiceOrders;

    @Column(name = "polr_customer_class")
    public String polrCustomerClass;

    @Column(name = "settlement_ams_indicator")
    public String settlementAmsIndicator;

    @Column(name = "tdsp_ams_indicator")
    public String tdspAmsIndicator;

    @Column(name = "switch_hold_indicator")
    public String switchHoldIndicator;

    @Column(name = "metered_service_type")
    public String meteredServiceType;

    @Column(name = "metered_service_type_desc")
    public String meteredServiceTypeDesc;

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
                ", tdspId=" + tdspId +
                ", status='" + status + '\'' +
                ", premiseType='" + premiseType + '\'' +
                '}';
    }
}
