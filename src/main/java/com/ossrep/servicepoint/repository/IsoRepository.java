package com.ossrep.servicepoint.repository;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class IsoRepository implements PanacheRepositoryBase<IsoEntity, Long> {
}
