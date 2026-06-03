package com.ossrep.servicepoint.ingestion;

import jakarta.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class IngestionLogRepository implements PanacheRepositoryBase<IngestionLogEntity, Long> {

    public boolean existsByFileName(String fileName) {
        return count("fileName", fileName) > 0;
    }
}
