package com.ossrep.servicepoint.api;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
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

import com.ossrep.servicepoint.service.TdspService;

@Path("/api/v1/tdsps")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "TDSPs", description = "Transmission/Distribution Service Provider reference data")
public class TdspResource {

    @Inject
    TdspService tdspService;

    @GET
    @RolesAllowed({Roles.ADMIN_ROLE, Roles.USER_ROLE})
    @Operation(summary = "List all TDSPs, optionally filtered by code")
    @APIResponse(responseCode = "200", description = "List of TDSPs",
            content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = TdspResponse.class)))
    public Response listAll(@QueryParam("code") String code) {
        List<TdspResponse> responses;
        if (code != null && !code.isBlank()) {
            responses = tdspService.findByCode(code)
                    .map(tdsp -> List.of(TdspResponse.from(tdsp)))
                    .orElse(List.of());
        } else {
            responses = tdspService.listAll().stream()
                    .map(TdspResponse::from)
                    .toList();
        }
        return Response.ok(responses).build();
    }

    @GET
    @Path("/{tdspId}")
    @RolesAllowed({Roles.ADMIN_ROLE, Roles.USER_ROLE})
    @Operation(summary = "Get a TDSP by ID")
    @APIResponse(responseCode = "200", description = "The TDSP",
            content = @Content(schema = @Schema(implementation = TdspResponse.class)))
    @APIResponse(responseCode = "404", description = "TDSP not found")
    public Response getById(@PathParam("tdspId") Long tdspId) {
        return tdspService.findById(tdspId)
                .map(tdsp -> Response.ok(TdspResponse.from(tdsp)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
