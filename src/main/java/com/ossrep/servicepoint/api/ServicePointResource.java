package com.ossrep.servicepoint.api;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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

import com.ossrep.servicepoint.service.ServicePoint;
import com.ossrep.servicepoint.service.ServicePointService;

@Path("/api/service-points")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Service Points", description = "Service Point management endpoints")
public class ServicePointResource {

    @Inject
    ServicePointService servicePointService;

    @GET
    @RolesAllowed({Roles.ADMIN_ROLE, Roles.USER_ROLE})
    @Operation(summary = "List all service points")
    @APIResponse(responseCode = "200", description = "List of service points",
            content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = ServicePointResponse.class)))
    public Response listAll() {
        List<ServicePointResponse> responses = servicePointService.listAll().stream()
                .map(ServicePointResponse::from)
                .toList();
        return Response.ok(responses).build();
    }

    @GET
    @Path("/{servicePointId}")
    @RolesAllowed({Roles.ADMIN_ROLE, Roles.USER_ROLE})
    @Operation(summary = "Get a service point by ID")
    @APIResponse(responseCode = "200", description = "The service point",
            content = @Content(schema = @Schema(implementation = ServicePointResponse.class)))
    @APIResponse(responseCode = "404", description = "Service point not found")
    public Response getById(@PathParam("servicePointId") Long servicePointId) {
        return servicePointService.findById(servicePointId)
                .map(sp -> Response.ok(ServicePointResponse.from(sp)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @RolesAllowed({Roles.ADMIN_ROLE})
    @Operation(summary = "Create a new service point")
    @APIResponse(responseCode = "201", description = "Service point created",
            content = @Content(schema = @Schema(implementation = ServicePointResponse.class)))
    public Response create(@Valid CreateServicePointRequest request) {
        ServicePoint domain = toDomain(null, request.esiid(), request.street(), request.streetLine2(),
                request.city(), request.state(), request.zip(), request.county(), request.tdspDuns(),
                request.meterReadCycle(), request.status(), request.premiseType(), request.powerRegion(),
                request.stationCode(), request.stationName(), request.metered(), request.openServiceOrders(),
                request.polrCustomerClass(), request.settlementAmsIndicator(), request.tdspAmsIndicator(),
                request.switchHoldIndicator(), request.meteredServiceType(), request.meteredServiceTypeDesc());
        ServicePoint created = servicePointService.create(domain);
        return Response.status(Response.Status.CREATED)
                .entity(ServicePointResponse.from(created))
                .build();
    }

    @PUT
    @Path("/{servicePointId}")
    @RolesAllowed({Roles.ADMIN_ROLE})
    @Operation(summary = "Update an existing service point")
    @APIResponse(responseCode = "200", description = "Service point updated",
            content = @Content(schema = @Schema(implementation = ServicePointResponse.class)))
    @APIResponse(responseCode = "404", description = "Service point not found")
    public Response update(@PathParam("servicePointId") Long servicePointId,
                           @Valid UpdateServicePointRequest request) {
        ServicePoint domain = toDomain(servicePointId, request.esiid(), request.street(), request.streetLine2(),
                request.city(), request.state(), request.zip(), request.county(), request.tdspDuns(),
                request.meterReadCycle(), request.status(), request.premiseType(), request.powerRegion(),
                request.stationCode(), request.stationName(), request.metered(), request.openServiceOrders(),
                request.polrCustomerClass(), request.settlementAmsIndicator(), request.tdspAmsIndicator(),
                request.switchHoldIndicator(), request.meteredServiceType(), request.meteredServiceTypeDesc());
        return servicePointService.update(servicePointId, domain)
                .map(sp -> Response.ok(ServicePointResponse.from(sp)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{servicePointId}")
    @RolesAllowed({Roles.ADMIN_ROLE})
    @Operation(summary = "Delete a service point")
    @APIResponse(responseCode = "204", description = "Service point deleted")
    @APIResponse(responseCode = "404", description = "Service point not found")
    public Response delete(@PathParam("servicePointId") Long servicePointId) {
        if (servicePointService.delete(servicePointId)) {
            return Response.noContent().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    private ServicePoint toDomain(Long id, String esiid, String street, String streetLine2,
                                  String city, String state, String zip, String county, String tdspDuns,
                                  String meterReadCycle, String status, String premiseType, String powerRegion,
                                  String stationCode, String stationName, Boolean metered, String openServiceOrders,
                                  String polrCustomerClass, String settlementAmsIndicator, String tdspAmsIndicator,
                                  String switchHoldIndicator, String meteredServiceType, String meteredServiceTypeDesc) {
        return new ServicePoint(id, esiid, street, streetLine2, city, state, zip, county, tdspDuns,
                meterReadCycle, status, premiseType, powerRegion, stationCode, stationName, metered,
                openServiceOrders, polrCustomerClass, settlementAmsIndicator, tdspAmsIndicator,
                switchHoldIndicator, meteredServiceType, meteredServiceTypeDesc, null, null);
    }
}
