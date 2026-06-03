package com.ossrep.servicepoint.api;

import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import io.quarkus.security.identity.AuthenticationRequestContext;
import io.quarkus.security.identity.SecurityIdentity;
import io.quarkus.security.identity.SecurityIdentityAugmentor;
import io.quarkus.security.runtime.QuarkusSecurityIdentity;
import io.smallrye.mutiny.Uni;

@ApplicationScoped
public class RoleMappingAugmentor implements SecurityIdentityAugmentor {

    @Inject
    RoleMappingConfig config;

    @Override
    public Uni<SecurityIdentity> augment(SecurityIdentity identity,
                                         AuthenticationRequestContext context) {
        if (identity.isAnonymous()) {
            return Uni.createFrom().item(identity);
        }

        Map<String, String> mappings = config.roleMapping();
        if (mappings.isEmpty()) {
            return Uni.createFrom().item(identity);
        }

        QuarkusSecurityIdentity.Builder builder = QuarkusSecurityIdentity.builder(identity);

        for (String role : identity.getRoles()) {
            String canonicalRole = mappings.get(role);
            if (canonicalRole != null) {
                builder.addRole(canonicalRole);
            }
        }

        return Uni.createFrom().item(builder.build());
    }
}
