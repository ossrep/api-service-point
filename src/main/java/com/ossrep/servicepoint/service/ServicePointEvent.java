package com.ossrep.servicepoint.service;

import java.time.Instant;

public record ServicePointEvent(
        EventType eventType,
        Instant timestamp,
        ServicePoint servicePoint
) {

    public enum EventType {
        SERVICE_POINT_CREATED,
        SERVICE_POINT_UPDATED,
        SERVICE_POINT_DELETED
    }
}
