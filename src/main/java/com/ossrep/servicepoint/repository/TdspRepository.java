package com.ossrep.servicepoint.repository;

import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class TdspRepository implements PanacheRepositoryBase<TdspEntity, Long> {

    public Optional<TdspEntity> findByDuns(String duns) {
        return find("duns", duns).firstResultOptional();
    }

    public Optional<TdspEntity> findByCode(String code) {
        return find("code", code).firstResultOptional();
    }
}
