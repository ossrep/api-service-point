package com.ossrep.servicepoint.ingestion;

import java.util.Optional;

import io.smallrye.config.ConfigMapping;

@ConfigMapping(prefix = "app.ercot")
public interface ErcotIngestionConfig {

    String authUrl();

    Optional<String> username();

    Optional<String> password();

    Optional<String> subscriptionKey();

    String archiveUrl();

    String cron();

    boolean enabled();
}
