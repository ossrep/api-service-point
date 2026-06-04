package com.ossrep.servicepoint.api;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Paged response wrapper")
public record PagedResponse<T>(
        @Schema(description = "Items in the current page")
        List<T> content,
        @Schema(description = "Current page number (0-based)")
        int page,
        @Schema(description = "Page size")
        int size,
        @Schema(description = "Total number of items")
        long totalElements,
        @Schema(description = "Total number of pages")
        int totalPages
) {

    public static <T> PagedResponse<T> of(List<T> content, int page, int size, long totalElements) {
        int totalPages = size > 0 ? (int) Math.ceil((double) totalElements / size) : 0;
        return new PagedResponse<>(content, page, size, totalElements, totalPages);
    }
}
