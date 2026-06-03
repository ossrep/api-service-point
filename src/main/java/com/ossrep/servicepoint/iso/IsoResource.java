package com.ossrep.servicepoint.iso;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.ossrep.servicepoint.api.Roles;

@Path("/api/v1/isos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "ISOs", description = "Independent System Operator reference data")
public class IsoResource {

    @Inject
    IsoService isoService;

    @GET
    @RolesAllowed({Roles.ADMIN_ROLE, Roles.USER_ROLE})
    @Operation(summary = "List all ISOs")
    @APIResponse(responseCode = "200", description = "List of ISOs",
            content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = IsoResponse.class)))
    public Response listAll() {
        List<IsoResponse> responses = isoService.listAll().stream()
                .map(IsoResponse::from)
                .toList();
        return Response.ok(responses).build();
    }

    @GET
    @Path("/{isoId}")
    @RolesAllowed({Roles.ADMIN_ROLE, Roles.USER_ROLE})
    @Operation(summary = "Get an ISO by ID")
    @APIResponse(responseCode = "200", description = "The ISO",
            content = @Content(schema = @Schema(implementation = IsoResponse.class)))
    @APIResponse(responseCode = "404", description = "ISO not found")
    public Response getById(@PathParam("isoId") Long isoId) {
        return isoService.findById(isoId)
                .map(iso -> Response.ok(IsoResponse.from(iso)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
