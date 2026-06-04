package com.ossrep.servicepoint.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ServicePointResourceTest {

    private static final String BASE_PATH = "/api/v1/service-points";

    // ========================================================================
    // GET /api/v1/service-points - List all
    // ========================================================================

    @Test
    @Order(1)
    @TestSecurity(user = "testuser", roles = "user")
    void listAll_asUser_returnsOkWithTestData() {
        given()
                .when().get(BASE_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("content", hasSize(greaterThanOrEqualTo(3)))
                .body("content[0].servicePointId", notNullValue())
                .body("content[0].esiid", notNullValue())
                .body("content[0].status", notNullValue())
                .body("page", equalTo(0))
                .body("size", equalTo(20))
                .body("totalElements", greaterThanOrEqualTo(3))
                .body("totalPages", greaterThanOrEqualTo(1));
    }

    @Test
    @Order(1)
    @TestSecurity(user = "admin", roles = "admin")
    void listAll_asAdmin_returnsOkWithTestData() {
        given()
                .when().get(BASE_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("content", hasSize(greaterThanOrEqualTo(3)));
    }

    @Test
    @Order(1)
    @TestSecurity(user = "testuser", roles = "user")
    void listAll_withPageParams_returnsRequestedPage() {
        given()
                .queryParam("page", 0)
                .queryParam("size", 2)
                .when().get(BASE_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("content", hasSize(2))
                .body("page", equalTo(0))
                .body("size", equalTo(2))
                .body("totalElements", greaterThanOrEqualTo(3))
                .body("totalPages", greaterThanOrEqualTo(2));
    }

    @Test
    @Order(1)
    void listAll_unauthenticated_returnsUnauthorized() {
        given()
                .when().get(BASE_PATH)
                .then()
                .statusCode(401);
    }

    // ========================================================================
    // GET /api/v1/service-points/{servicePointId} - Get by ID
    // ========================================================================

    @Test
    @Order(2)
    @TestSecurity(user = "testuser", roles = "user")
    void getById_existingId_returnsOk() {
        given()
                .when().get(BASE_PATH + "/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("servicePointId", equalTo(1))
                .body("esiid", equalTo("1008901012345678901234"))
                .body("tdspId", equalTo(1))
                .body("status", equalTo("Active"))
                .body("street", equalTo("123 MAIN ST"))
                .body("city", equalTo("HOUSTON"))
                .body("state", equalTo("TX"))
                .body("zip", equalTo("77002"))
                .body("county", equalTo("HARRIS"))
                .body("meterReadCycle", equalTo("01"))
                .body("premiseType", equalTo("Residential"))
                .body("stationCode", equalTo("FAN"))
                .body("stationName", equalTo("FANNIN REIT"))
                .body("metered", equalTo(true))
                .body("polrCustomerClass", equalTo("Residential"))
                .body("settlementAmsIndicator", equalTo("Y"))
                .body("tdspAmsIndicator", equalTo("AMSM"))
                .body("switchHoldIndicator", equalTo("N"))
                .body("createdAt", notNullValue())
                .body("updatedAt", notNullValue());
    }

    @Test
    @Order(2)
    @TestSecurity(user = "testuser", roles = "user")
    void getById_nonExistingId_returnsNotFound() {
        given()
                .when().get(BASE_PATH + "/999999")
                .then()
                .statusCode(404);
    }

    @Test
    @Order(2)
    @TestSecurity(user = "admin", roles = "admin")
    void getById_asAdmin_returnsOk() {
        given()
                .when().get(BASE_PATH + "/2")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("servicePointId", equalTo(2))
                .body("esiid", equalTo("1008901098765432109876"))
                .body("tdspId", equalTo(1))
                .body("premiseType", equalTo("Small Non-Residential"))
                .body("stationCode", equalTo("KL"))
                .body("tdspAmsIndicator", equalTo("AMSR"));
    }

    @Test
    @Order(2)
    void getById_unauthenticated_returnsUnauthorized() {
        given()
                .when().get(BASE_PATH + "/1")
                .then()
                .statusCode(401);
    }

    // ========================================================================
    // POST /api/v1/service-points - Create
    // ========================================================================

    @Test
    @Order(3)
    @TestSecurity(user = "admin", roles = "admin")
    void create_validRequest_returnsCreated() {
        String requestBody = """
                {
                    "tdspId": 1,
                    "esiid": "1008901099999999999999",
                    "street": "999 TEST BLVD",
                    "city": "HUMBLE",
                    "state": "TX",
                    "zip": "77346",
                    "county": "HARRIS",
                    "meterReadCycle": "02",
                    "status": "Active",
                    "premiseType": "Residential",
                    "stationCode": "_HB",
                    "stationName": "HUMBLE OLD",
                    "metered": true,
                    "polrCustomerClass": "Residential",
                    "settlementAmsIndicator": "Y",
                    "tdspAmsIndicator": "AMSM",
                    "switchHoldIndicator": "N"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(BASE_PATH)
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("servicePointId", notNullValue())
                .body("tdspId", equalTo(1))
                .body("esiid", equalTo("1008901099999999999999"))
                .body("street", equalTo("999 TEST BLVD"))
                .body("city", equalTo("HUMBLE"))
                .body("meterReadCycle", equalTo("02"))
                .body("status", equalTo("Active"))
                .body("polrCustomerClass", equalTo("Residential"))
                .body("settlementAmsIndicator", equalTo("Y"))
                .body("tdspAmsIndicator", equalTo("AMSM"))
                .body("switchHoldIndicator", equalTo("N"))
                .body("createdAt", notNullValue())
                .body("updatedAt", notNullValue())
                .extract().jsonPath().getLong("servicePointId");
    }

    @Test
    @Order(3)
    @TestSecurity(user = "admin", roles = "admin")
    void create_minimalRequest_returnsCreated() {
        String requestBody = """
                {
                    "esiid": "1008901088888888888888",
                    "status": "Inactive",
                    "metered": false
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(BASE_PATH)
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("servicePointId", notNullValue())
                .body("esiid", equalTo("1008901088888888888888"))
                .body("status", equalTo("Inactive"))
                .body("metered", equalTo(false))
                .body("street", nullValue())
                .body("tdspId", nullValue())
                .body("tdspAmsIndicator", nullValue());
    }

    @Test
    @Order(3)
    @TestSecurity(user = "admin", roles = "admin")
    void create_missingEsiid_returnsBadRequest() {
        String requestBody = """
                {
                    "status": "Active",
                    "metered": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(BASE_PATH)
                .then()
                .statusCode(400);
    }

    @Test
    @Order(3)
    @TestSecurity(user = "admin", roles = "admin")
    void create_blankEsiid_returnsBadRequest() {
        String requestBody = """
                {
                    "esiid": "",
                    "status": "Active",
                    "metered": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(BASE_PATH)
                .then()
                .statusCode(400);
    }

    @Test
    @Order(3)
    @TestSecurity(user = "admin", roles = "admin")
    void create_missingStatus_returnsBadRequest() {
        String requestBody = """
                {
                    "esiid": "1008901077777777777777",
                    "metered": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(BASE_PATH)
                .then()
                .statusCode(400);
    }

    @Test
    @Order(3)
    @TestSecurity(user = "admin", roles = "admin")
    void create_missingMetered_returnsBadRequest() {
        String requestBody = """
                {
                    "esiid": "1008901077777777777777",
                    "status": "Active"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(BASE_PATH)
                .then()
                .statusCode(400);
    }

    @Test
    @Order(3)
    @TestSecurity(user = "testuser", roles = "user")
    void create_asUser_returnsForbidden() {
        String requestBody = """
                {
                    "esiid": "1008901066666666666666",
                    "status": "Active",
                    "metered": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(BASE_PATH)
                .then()
                .statusCode(403);
    }

    @Test
    @Order(3)
    void create_unauthenticated_returnsUnauthorized() {
        String requestBody = """
                {
                    "esiid": "1008901066666666666666",
                    "status": "Active",
                    "metered": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(BASE_PATH)
                .then()
                .statusCode(401);
    }

    // ========================================================================
    // PUT /api/v1/service-points/{servicePointId} - Update
    // ========================================================================

    @Test
    @Order(4)
    @TestSecurity(user = "admin", roles = "admin")
    void update_existingId_returnsOk() {
        String requestBody = """
                {
                    "tdspId": 1,
                    "esiid": "1008901012345678901234",
                    "street": "123 MAIN ST UPDATED",
                    "city": "HOUSTON",
                    "state": "TX",
                    "zip": "77002",
                    "county": "HARRIS",
                    "meterReadCycle": "01",
                    "status": "De-Energized",
                    "premiseType": "Residential",
                    "stationCode": "FAN",
                    "stationName": "FANNIN REIT",
                    "metered": true,
                    "polrCustomerClass": "Residential",
                    "settlementAmsIndicator": "Y",
                    "tdspAmsIndicator": "AMSR",
                    "switchHoldIndicator": "N"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().put(BASE_PATH + "/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("servicePointId", equalTo(1))
                .body("tdspId", equalTo(1))
                .body("street", equalTo("123 MAIN ST UPDATED"))
                .body("status", equalTo("De-Energized"))
                .body("tdspAmsIndicator", equalTo("AMSR"));
    }

    @Test
    @Order(4)
    @TestSecurity(user = "admin", roles = "admin")
    void update_nonExistingId_returnsNotFound() {
        String requestBody = """
                {
                    "esiid": "1008901000000000000000",
                    "status": "Active",
                    "metered": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().put(BASE_PATH + "/999999")
                .then()
                .statusCode(404);
    }

    @Test
    @Order(4)
    @TestSecurity(user = "admin", roles = "admin")
    void update_missingEsiid_returnsBadRequest() {
        String requestBody = """
                {
                    "status": "Active",
                    "metered": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().put(BASE_PATH + "/1")
                .then()
                .statusCode(400);
    }

    @Test
    @Order(4)
    @TestSecurity(user = "admin", roles = "admin")
    void update_missingStatus_returnsBadRequest() {
        String requestBody = """
                {
                    "esiid": "1008901012345678901234",
                    "metered": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().put(BASE_PATH + "/1")
                .then()
                .statusCode(400);
    }

    @Test
    @Order(4)
    @TestSecurity(user = "testuser", roles = "user")
    void update_asUser_returnsForbidden() {
        String requestBody = """
                {
                    "esiid": "1008901012345678901234",
                    "status": "Active",
                    "metered": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().put(BASE_PATH + "/1")
                .then()
                .statusCode(403);
    }

    @Test
    @Order(4)
    void update_unauthenticated_returnsUnauthorized() {
        String requestBody = """
                {
                    "esiid": "1008901012345678901234",
                    "status": "Active",
                    "metered": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().put(BASE_PATH + "/1")
                .then()
                .statusCode(401);
    }

    // ========================================================================
    // DELETE /api/v1/service-points/{servicePointId} - Delete
    // ========================================================================

    @Test
    @Order(5)
    @TestSecurity(user = "testuser", roles = "user")
    void delete_asUser_returnsForbidden() {
        given()
                .when().delete(BASE_PATH + "/3")
                .then()
                .statusCode(403);
    }

    @Test
    @Order(5)
    void delete_unauthenticated_returnsUnauthorized() {
        given()
                .when().delete(BASE_PATH + "/3")
                .then()
                .statusCode(401);
    }

    @Test
    @Order(6)
    @TestSecurity(user = "admin", roles = "admin")
    void delete_existingId_returnsNoContent() {
        given()
                .when().delete(BASE_PATH + "/3")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(7)
    @TestSecurity(user = "admin", roles = "admin")
    void delete_alreadyDeletedId_returnsNotFound() {
        given()
                .when().delete(BASE_PATH + "/3")
                .then()
                .statusCode(404);
    }

    @Test
    @Order(7)
    @TestSecurity(user = "admin", roles = "admin")
    void delete_nonExistingId_returnsNotFound() {
        given()
                .when().delete(BASE_PATH + "/999999")
                .then()
                .statusCode(404);
    }

    // ========================================================================
    // GET after mutations - verify state
    // ========================================================================

    @Test
    @Order(8)
    @TestSecurity(user = "testuser", roles = "user")
    void getById_afterUpdate_returnsUpdatedData() {
        given()
                .when().get(BASE_PATH + "/1")
                .then()
                .statusCode(200)
                .body("street", equalTo("123 MAIN ST UPDATED"))
                .body("status", equalTo("De-Energized"))
                .body("tdspAmsIndicator", equalTo("AMSR"));
    }

    @Test
    @Order(8)
    @TestSecurity(user = "testuser", roles = "user")
    void getById_afterDelete_returnsNotFound() {
        given()
                .when().get(BASE_PATH + "/3")
                .then()
                .statusCode(404);
    }

    @Test
    @Order(9)
    @TestSecurity(user = "admin", roles = "admin")
    void listAll_afterMutations_reflectsChanges() {
        given()
                .queryParam("size", 500)
                .when().get(BASE_PATH)
                .then()
                .statusCode(200)
                .body("content", hasSize(greaterThanOrEqualTo(4)))
                .body("content.find { it.servicePointId == 1 }.status", equalTo("De-Energized"))
                .body("content.find { it.servicePointId == 3 }", nullValue());
    }

    // ========================================================================
    // Role mapping - external IdP roles mapped to canonical roles
    // ========================================================================

    @Test
    @Order(10)
    @TestSecurity(user = "cp-admin", roles = "centerpoint-service-point-admin", augmentors = RoleMappingAugmentor.class)
    void listAll_asMappedExternalAdmin_returnsOk() {
        given()
                .when().get(BASE_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("content", hasSize(greaterThanOrEqualTo(1)));
    }

    @Test
    @Order(10)
    @TestSecurity(user = "cp-user", roles = "centerpoint-service-point-user", augmentors = RoleMappingAugmentor.class)
    void listAll_asMappedExternalUser_returnsOk() {
        given()
                .when().get(BASE_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("content", hasSize(greaterThanOrEqualTo(1)));
    }

    @Test
    @Order(10)
    @TestSecurity(user = "exa-admin", roles = "exarep-service-point-admin", augmentors = RoleMappingAugmentor.class)
    void create_asMappedExternalAdmin_returnsCreated() {
        String requestBody = """
                {
                    "esiid": "1008901011111111111111",
                    "status": "Active",
                    "metered": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(BASE_PATH)
                .then()
                .statusCode(201)
                .body("esiid", equalTo("1008901011111111111111"));
    }

    @Test
    @Order(10)
    @TestSecurity(user = "cp-user", roles = "centerpoint-service-point-user", augmentors = RoleMappingAugmentor.class)
    void create_asMappedExternalUser_returnsForbidden() {
        String requestBody = """
                {
                    "esiid": "1008901022222222222222",
                    "status": "Active",
                    "metered": true
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(BASE_PATH)
                .then()
                .statusCode(403);
    }

    @Test
    @Order(10)
    @TestSecurity(user = "unknown-role", roles = "unmapped-external-role", augmentors = RoleMappingAugmentor.class)
    void listAll_asUnmappedExternalRole_returnsForbidden() {
        given()
                .when().get(BASE_PATH)
                .then()
                .statusCode(403);
    }

    // ========================================================================
    // ISO endpoints - /api/v1/isos
    // ========================================================================

    @Test
    @Order(1)
    @TestSecurity(user = "testuser", roles = "user")
    void listIsos_asUser_returnsOk() {
        given()
                .when().get("/api/v1/isos")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(greaterThanOrEqualTo(1)))
                .body("[0].isoId", equalTo(1))
                .body("[0].code", equalTo("ERCOT"))
                .body("[0].name", equalTo("Electric Reliability Council of Texas"));
    }

    @Test
    @Order(1)
    @TestSecurity(user = "testuser", roles = "user")
    void getIsoById_existingId_returnsOk() {
        given()
                .when().get("/api/v1/isos/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("isoId", equalTo(1))
                .body("code", equalTo("ERCOT"))
                .body("name", equalTo("Electric Reliability Council of Texas"))
                .body("createdAt", notNullValue())
                .body("updatedAt", notNullValue());
    }

    @Test
    @Order(1)
    @TestSecurity(user = "testuser", roles = "user")
    void getIsoById_nonExistingId_returnsNotFound() {
        given()
                .when().get("/api/v1/isos/999")
                .then()
                .statusCode(404);
    }

    @Test
    @Order(1)
    void listIsos_unauthenticated_returnsUnauthorized() {
        given()
                .when().get("/api/v1/isos")
                .then()
                .statusCode(401);
    }

    // ========================================================================
    // TDSP endpoints - /api/v1/tdsps
    // ========================================================================

    @Test
    @Order(1)
    @TestSecurity(user = "testuser", roles = "user")
    void listTdsps_asUser_returnsOk() {
        given()
                .when().get("/api/v1/tdsps")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(greaterThanOrEqualTo(11)))
                .body("find { it.code == 'CENTERPOINT' }.name", equalTo("CenterPoint Energy"))
                .body("find { it.code == 'CENTERPOINT' }.duns", equalTo("957877905"))
                .body("find { it.code == 'ONCOR_ELEC' }.name", equalTo("Oncor Electric Delivery"));
    }

    @Test
    @Order(1)
    @TestSecurity(user = "testuser", roles = "user")
    void getTdspById_existingId_returnsOk() {
        given()
                .when().get("/api/v1/tdsps/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("tdspId", equalTo(1))
                .body("isoId", equalTo(1))
                .body("code", equalTo("CENTERPOINT"))
                .body("name", equalTo("CenterPoint Energy"))
                .body("duns", equalTo("957877905"))
                .body("createdAt", notNullValue())
                .body("updatedAt", notNullValue());
    }

    @Test
    @Order(1)
    @TestSecurity(user = "testuser", roles = "user")
    void getTdspById_nonExistingId_returnsNotFound() {
        given()
                .when().get("/api/v1/tdsps/999")
                .then()
                .statusCode(404);
    }

    @Test
    @Order(1)
    void listTdsps_unauthenticated_returnsUnauthorized() {
        given()
                .when().get("/api/v1/tdsps")
                .then()
                .statusCode(401);
    }
}
