package com.ossrep.servicepoint.iso;

import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class IsoService {

    @Inject
    IsoRepository repository;

    public List<Iso> listAll() {
        return repository.listAll().stream()
                .map(this::toDomain)
                .toList();
    }

    public Optional<Iso> findById(Long isoId) {
        return repository.findByIdOptional(isoId)
                .map(this::toDomain);
    }

    private Iso toDomain(IsoEntity entity) {
        return new Iso(
                entity.isoId,
                entity.code,
                entity.name,
                entity.description,
                entity.createdAt,
                entity.updatedAt
        );
    }
}
