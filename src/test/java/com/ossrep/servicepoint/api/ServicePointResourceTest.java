package com.ossrep.servicepoint.api;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ServicePointResourceTest {

    private static final String BASE_PATH = "/api/service-points";
    private static Long createdServicePointId;

    // ========================================================================
    // GET /api/service-points - List all
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
                .body("$", hasSize(greaterThanOrEqualTo(3)))
                .body("[0].servicePointId", notNullValue())
                .body("[0].esiid", notNullValue())
                .body("[0].status", notNullValue());
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
                .body("$", hasSize(greaterThanOrEqualTo(3)));
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
    // GET /api/service-points/{servicePointId} - Get by ID
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
                .body("status", equalTo("Active"))
                .body("street", equalTo("123 MAIN ST"))
                .body("city", equalTo("HOUSTON"))
                .body("state", equalTo("TX"))
                .body("zip", equalTo("77002"))
                .body("county", equalTo("HARRIS"))
                .body("tdspDuns", equalTo("957877905"))
                .body("townCode", equalTo("01"))
                .body("premiseType", equalTo("Residential"))
                .body("powerRegion", equalTo("ERCOT"))
                .body("stationCode", equalTo("FAN"))
                .body("stationName", equalTo("FANNIN REIT"))
                .body("metered", equalTo(true))
                .body("polr", equalTo(false))
                .body("meterType", equalTo("AMSM"))
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
                .body("premiseType", equalTo("Small Non-Residential"))
                .body("stationCode", equalTo("KL"));
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
    // POST /api/service-points - Create
    // ========================================================================

    @Test
    @Order(3)
    @TestSecurity(user = "admin", roles = "admin")
    void create_validRequest_returnsCreated() {
        String requestBody = """
                {
                    "esiid": "1008901099999999999999",
                    "street": "999 TEST BLVD",
                    "city": "HUMBLE",
                    "state": "TX",
                    "zip": "77346",
                    "county": "HARRIS",
                    "tdspDuns": "957877905",
                    "townCode": "02",
                    "status": "Active",
                    "premiseType": "Residential",
                    "powerRegion": "ERCOT",
                    "stationCode": "_HB",
                    "stationName": "HUMBLE OLD",
                    "metered": true,
                    "polr": false,
                    "meterType": "AMSM"
                }
                """;

        createdServicePointId = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post(BASE_PATH)
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("servicePointId", notNullValue())
                .body("esiid", equalTo("1008901099999999999999"))
                .body("street", equalTo("999 TEST BLVD"))
                .body("city", equalTo("HUMBLE"))
                .body("state", equalTo("TX"))
                .body("zip", equalTo("77346"))
                .body("county", equalTo("HARRIS"))
                .body("tdspDuns", equalTo("957877905"))
                .body("townCode", equalTo("02"))
                .body("status", equalTo("Active"))
                .body("premiseType", equalTo("Residential"))
                .body("powerRegion", equalTo("ERCOT"))
                .body("stationCode", equalTo("_HB"))
                .body("stationName", equalTo("HUMBLE OLD"))
                .body("metered", equalTo(true))
                .body("polr", equalTo(false))
                .body("meterType", equalTo("AMSM"))
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
                    "metered": false,
                    "polr": false
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
                .body("polr", equalTo(false))
                .body("street", nullValue())
                .body("meterType", nullValue());
    }

    @Test
    @Order(3)
    @TestSecurity(user = "admin", roles = "admin")
    void create_missingEsiid_returnsBadRequest() {
        String requestBody = """
                {
                    "status": "Active",
                    "metered": true,
                    "polr": false
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
                    "metered": true,
                    "polr": false
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
                    "metered": true,
                    "polr": false
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
                    "status": "Active",
                    "polr": false
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
    void create_missingPolr_returnsBadRequest() {
        String requestBody = """
                {
                    "esiid": "1008901077777777777777",
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
    @TestSecurity(user = "testuser", roles = "user")
    void create_asUser_returnsForbidden() {
        String requestBody = """
                {
                    "esiid": "1008901066666666666666",
                    "status": "Active",
                    "metered": true,
                    "polr": false
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
                    "metered": true,
                    "polr": false
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
    // PUT /api/service-points/{servicePointId} - Update
    // ========================================================================

    @Test
    @Order(4)
    @TestSecurity(user = "admin", roles = "admin")
    void update_existingId_returnsOk() {
        String requestBody = """
                {
                    "esiid": "1008901012345678901234",
                    "street": "123 MAIN ST UPDATED",
                    "city": "HOUSTON",
                    "state": "TX",
                    "zip": "77002",
                    "county": "HARRIS",
                    "tdspDuns": "957877905",
                    "townCode": "01",
                    "status": "De-Energized",
                    "premiseType": "Residential",
                    "powerRegion": "ERCOT",
                    "stationCode": "FAN",
                    "stationName": "FANNIN REIT",
                    "metered": true,
                    "polr": true,
                    "meterType": "AMSR"
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
                .body("esiid", equalTo("1008901012345678901234"))
                .body("street", equalTo("123 MAIN ST UPDATED"))
                .body("status", equalTo("De-Energized"))
                .body("polr", equalTo(true))
                .body("meterType", equalTo("AMSR"));
    }

    @Test
    @Order(4)
    @TestSecurity(user = "admin", roles = "admin")
    void update_nonExistingId_returnsNotFound() {
        String requestBody = """
                {
                    "esiid": "1008901000000000000000",
                    "status": "Active",
                    "metered": true,
                    "polr": false
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
                    "metered": true,
                    "polr": false
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
                    "metered": true,
                    "polr": false
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
                    "metered": true,
                    "polr": false
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
                    "metered": true,
                    "polr": false
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
    // DELETE /api/service-points/{servicePointId} - Delete
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
                .body("polr", equalTo(true))
                .body("meterType", equalTo("AMSR"));
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
                .when().get(BASE_PATH)
                .then()
                .statusCode(200)
                .body("$", hasSize(greaterThanOrEqualTo(4)))
                .body("find { it.servicePointId == 1 }.status", equalTo("De-Energized"))
                .body("find { it.servicePointId == 3 }", nullValue());
    }

    // ========================================================================
    // Role mapping - external IdP roles mapped to canonical roles
    // ========================================================================

    @Test
    @Order(10)
    @TestSecurity(user = "cp-admin", roles = "centerpoint-service-point-admin")
    void listAll_asMappedExternalAdmin_returnsOk() {
        given()
                .when().get(BASE_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(greaterThanOrEqualTo(1)));
    }

    @Test
    @Order(10)
    @TestSecurity(user = "cp-user", roles = "centerpoint-service-point-user")
    void listAll_asMappedExternalUser_returnsOk() {
        given()
                .when().get(BASE_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("$", hasSize(greaterThanOrEqualTo(1)));
    }

    @Test
    @Order(10)
    @TestSecurity(user = "exa-admin", roles = "exarep-service-point-admin")
    void create_asMappedExternalAdmin_returnsCreated() {
        String requestBody = """
                {
                    "esiid": "1008901011111111111111",
                    "status": "Active",
                    "metered": true,
                    "polr": false
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
    @TestSecurity(user = "cp-user", roles = "centerpoint-service-point-user")
    void create_asMappedExternalUser_returnsForbidden() {
        String requestBody = """
                {
                    "esiid": "1008901022222222222222",
                    "status": "Active",
                    "metered": true,
                    "polr": false
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
    @TestSecurity(user = "unknown-role", roles = "unmapped-external-role")
    void listAll_asUnmappedExternalRole_returnsForbidden() {
        given()
                .when().get(BASE_PATH)
                .then()
                .statusCode(403);
    }
}
