package com.ossrep.servicepoint.service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import com.ossrep.servicepoint.repository.ServicePointEntity;
import com.ossrep.servicepoint.repository.ServicePointRepository;

@ApplicationScoped
public class ServicePointService {

    @Inject
    ServicePointRepository repository;

    @Inject
    ServicePointEventEmitter eventEmitter;

    public List<ServicePoint> listAll() {
        return repository.listAll().stream()
                .map(this::toDomain)
                .toList();
    }

    public Optional<ServicePoint> findById(Long servicePointId) {
        return repository.findByIdOptional(servicePointId)
                .map(this::toDomain);
    }

    @Transactional
    public ServicePoint create(@Valid ServicePoint servicePoint) {
        ServicePointEntity entity = toEntity(servicePoint);
        entity.createdAt = Instant.now();
        entity.updatedAt = entity.createdAt;
        repository.persist(entity);

        ServicePoint created = toDomain(entity);
        eventEmitter.emit(new ServicePointEvent(
                ServicePointEvent.EventType.SERVICE_POINT_CREATED,
                Instant.now(),
                created
        ));
        return created;
    }

    @Transactional
    public Optional<ServicePoint> update(Long servicePointId, @Valid ServicePoint servicePoint) {
        return repository.findByIdOptional(servicePointId)
                .map(entity -> {
                    entity.esiid = servicePoint.esiid();
                    entity.street = servicePoint.street();
                    entity.streetLine2 = servicePoint.streetLine2();
                    entity.city = servicePoint.city();
                    entity.state = servicePoint.state();
                    entity.zip = servicePoint.zip();
                    entity.county = servicePoint.county();
                    entity.tdspDuns = servicePoint.tdspDuns();
                    entity.meterReadCycle = servicePoint.meterReadCycle();
                    entity.status = servicePoint.status();
                    entity.premiseType = servicePoint.premiseType();
                    entity.powerRegion = servicePoint.powerRegion();
                    entity.stationCode = servicePoint.stationCode();
                    entity.stationName = servicePoint.stationName();
                    entity.metered = servicePoint.metered();
                    entity.openServiceOrders = servicePoint.openServiceOrders();
                    entity.polrCustomerClass = servicePoint.polrCustomerClass();
                    entity.settlementAmsIndicator = servicePoint.settlementAmsIndicator();
                    entity.tdspAmsIndicator = servicePoint.tdspAmsIndicator();
                    entity.switchHoldIndicator = servicePoint.switchHoldIndicator();
                    entity.meteredServiceType = servicePoint.meteredServiceType();
                    entity.meteredServiceTypeDesc = servicePoint.meteredServiceTypeDesc();
                    entity.updatedAt = Instant.now();

                    ServicePoint updated = toDomain(entity);
                    eventEmitter.emit(new ServicePointEvent(
                            ServicePointEvent.EventType.SERVICE_POINT_UPDATED,
                            Instant.now(),
                            updated
                    ));
                    return updated;
                });
    }

    @Transactional
    public boolean delete(Long servicePointId) {
        return repository.findByIdOptional(servicePointId)
                .map(entity -> {
                    ServicePoint deleted = toDomain(entity);
                    repository.delete(entity);
                    eventEmitter.emit(new ServicePointEvent(
                            ServicePointEvent.EventType.SERVICE_POINT_DELETED,
                            Instant.now(),
                            deleted
                    ));
                    return true;
                })
                .orElse(false);
    }

    private ServicePoint toDomain(ServicePointEntity entity) {
        return new ServicePoint(
                entity.servicePointId,
                entity.esiid,
                entity.street,
                entity.streetLine2,
                entity.city,
                entity.state,
                entity.zip,
                entity.county,
                entity.tdspDuns,
                entity.meterReadCycle,
                entity.status,
                entity.premiseType,
                entity.powerRegion,
                entity.stationCode,
                entity.stationName,
                entity.metered,
                entity.openServiceOrders,
                entity.polrCustomerClass,
                entity.settlementAmsIndicator,
                entity.tdspAmsIndicator,
                entity.switchHoldIndicator,
                entity.meteredServiceType,
                entity.meteredServiceTypeDesc,
                entity.createdAt,
                entity.updatedAt
        );
    }

    private ServicePointEntity toEntity(ServicePoint domain) {
        ServicePointEntity entity = new ServicePointEntity();
        entity.esiid = domain.esiid();
        entity.street = domain.street();
        entity.streetLine2 = domain.streetLine2();
        entity.city = domain.city();
        entity.state = domain.state();
        entity.zip = domain.zip();
        entity.county = domain.county();
        entity.tdspDuns = domain.tdspDuns();
        entity.meterReadCycle = domain.meterReadCycle();
        entity.status = domain.status();
        entity.premiseType = domain.premiseType();
        entity.powerRegion = domain.powerRegion();
        entity.stationCode = domain.stationCode();
        entity.stationName = domain.stationName();
        entity.metered = domain.metered();
        entity.openServiceOrders = domain.openServiceOrders();
        entity.polrCustomerClass = domain.polrCustomerClass();
        entity.settlementAmsIndicator = domain.settlementAmsIndicator();
        entity.tdspAmsIndicator = domain.tdspAmsIndicator();
        entity.switchHoldIndicator = domain.switchHoldIndicator();
        entity.meteredServiceType = domain.meteredServiceType();
        entity.meteredServiceTypeDesc = domain.meteredServiceTypeDesc();
        return entity;
    }
}
