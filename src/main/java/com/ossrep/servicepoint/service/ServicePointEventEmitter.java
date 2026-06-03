package com.ossrep.servicepoint.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.logging.Logger;

@ApplicationScoped
public class ServicePointEventEmitter {

    @Inject
    Logger logger;

    @Inject
    @Channel("service-point-events")
    Emitter<ServicePointEvent> emitter;

    public void emit(ServicePointEvent event) {
        logger.infof("Emitting event: %s for servicePointId: %d",
                event.eventType(), event.servicePoint().servicePointId());
        emitter.send(event);
    }
}
