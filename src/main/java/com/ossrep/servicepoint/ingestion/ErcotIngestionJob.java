package com.ossrep.servicepoint.ingestion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import io.quarkus.logging.Log;
import io.quarkus.scheduler.Scheduled;

@ApplicationScoped
public class ErcotIngestionJob {

    @Inject
    ErcotIngestionConfig config;

    @Inject
    IngestionService ingestionService;

    @Scheduled(cron = "{app.ercot.cron}", concurrentExecution = Scheduled.ConcurrentExecution.SKIP)
    void run() {
        if (!config.enabled()) {
            Log.debug("ERCOT ingestion is disabled");
            return;
        }

        try {
            ingestionService.processArchives();
        } catch (Exception e) {
            Log.errorf(e, "ERCOT ingestion job failed");
        }
    }
}
