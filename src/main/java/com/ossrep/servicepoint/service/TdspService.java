package com.ossrep.servicepoint.service;

import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.ossrep.servicepoint.repository.TdspEntity;
import com.ossrep.servicepoint.repository.TdspRepository;

@ApplicationScoped
public class TdspService {

    @Inject
    TdspRepository repository;

    public List<Tdsp> listAll() {
        return repository.listAll().stream()
                .map(this::toDomain)
                .toList();
    }

    public Optional<Tdsp> findById(Long tdspId) {
        return repository.findByIdOptional(tdspId)
                .map(this::toDomain);
    }

    public Optional<Tdsp> findByDuns(String duns) {
        return repository.findByDuns(duns)
                .map(this::toDomain);
    }

    public Optional<Tdsp> findByCode(String code) {
        return repository.findByCode(code)
                .map(this::toDomain);
    }

    private Tdsp toDomain(TdspEntity entity) {
        return new Tdsp(
                entity.tdspId,
                entity.isoId,
                entity.code,
                entity.name,
                entity.duns,
                entity.description,
                entity.createdAt,
                entity.updatedAt
        );
    }
}
