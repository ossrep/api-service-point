package com.ossrep.servicepoint.repository;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ServicePointRepository implements PanacheRepositoryBase<ServicePointEntity, Long> {
}
