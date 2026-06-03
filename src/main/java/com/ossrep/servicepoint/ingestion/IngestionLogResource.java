package com.ossrep.servicepoint.ingestion;

import java.time.Instant;
import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.ossrep.servicepoint.api.Roles;

@Path("/api/v1/ingestion-logs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Ingestion Logs", description = "ERCOT data ingestion tracking")
public class IngestionLogResource {

    @Inject
    IngestionLogRepository repository;

    @GET
    @RolesAllowed({Roles.ADMIN_ROLE})
    @Operation(summary = "List ingestion logs, optionally filtered by fileName")
    @APIResponse(responseCode = "200", description = "List of ingestion logs",
            content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = IngestionLogResponse.class)))
    public Response list(@QueryParam("fileName") String fileName) {
        List<IngestionLogResponse> responses;
        if (fileName != null && !fileName.isBlank()) {
            responses = repository.find("fileName", fileName).stream()
                    .map(IngestionLogResponse::from)
                    .toList();
        } else {
            responses = repository.listAll().stream()
                    .map(IngestionLogResponse::from)
                    .toList();
        }
        return Response.ok(responses).build();
    }

    @POST
    @Transactional
    @RolesAllowed({Roles.ADMIN_ROLE})
    @Operation(summary = "Create a new ingestion log entry")
    @APIResponse(responseCode = "201", description = "Ingestion log created",
            content = @Content(schema = @Schema(implementation = IngestionLogResponse.class)))
    public Response create(@Valid CreateIngestionLogRequest request) {
        IngestionLogEntity entity = new IngestionLogEntity();
        entity.tdspId = request.tdspId();
        entity.fileName = request.fileName();
        entity.fileType = request.fileType();
        entity.status = "PROCESSING";
        entity.startedAt = Instant.now();
        repository.persist(entity);
        return Response.status(Response.Status.CREATED)
                .entity(IngestionLogResponse.from(entity))
                .build();
    }

    @PUT
    @Path("/{ingestionLogId}")
    @Transactional
    @RolesAllowed({Roles.ADMIN_ROLE})
    @Operation(summary = "Update an ingestion log entry status")
    @APIResponse(responseCode = "200", description = "Ingestion log updated",
            content = @Content(schema = @Schema(implementation = IngestionLogResponse.class)))
    @APIResponse(responseCode = "404", description = "Ingestion log not found")
    public Response update(@PathParam("ingestionLogId") Long ingestionLogId,
                           @Valid UpdateIngestionLogRequest request) {
        return repository.findByIdOptional(ingestionLogId)
                .map(entity -> {
                    entity.status = request.status();
                    entity.recordCount = request.recordCount();
                    entity.errorMessage = request.errorMessage();
                    entity.completedAt = Instant.now();
                    return Response.ok(IngestionLogResponse.from(entity)).build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
