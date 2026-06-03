package com.ossrep.servicepoint.ingestion;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.quarkus.logging.Log;

@ApplicationScoped
public class ErcotApiClient {

    @Inject
    ErcotIngestionConfig config;

    @Inject
    ObjectMapper objectMapper;

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String authenticate() {
        String username = config.username().orElseThrow(() -> new IllegalStateException("ERCOT username not configured"));
        String password = config.password().orElseThrow(() -> new IllegalStateException("ERCOT password not configured"));

        String body = "username=" + username
                + "&password=" + password
                + "&grant_type=password"
                + "&scope=openid+fec253ea-0d06-4272-a5e6-b478baeecd70+offline_access"
                + "&client_id=fec253ea-0d06-4272-a5e6-b478baeecd70"
                + "&response_type=id_token";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(config.authUrl()))
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("ERCOT auth failed with status " + response.statusCode());
            }

            JsonNode json = objectMapper.readTree(response.body());
            return json.get("access_token").asText();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("ERCOT auth interrupted", e);
        } catch (Exception e) {
            throw new RuntimeException("ERCOT auth failed", e);
        }
    }

    public List<ArchiveEntry> listArchives(String accessToken) {
        String subscriptionKey = config.subscriptionKey()
                .orElseThrow(() -> new IllegalStateException("ERCOT subscription key not configured"));

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(config.archiveUrl()))
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Ocp-Apim-Subscription-Key", subscriptionKey)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("ERCOT archive list failed with status " + response.statusCode());
            }

            return parseArchiveResponse(response.body());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("ERCOT archive list interrupted", e);
        } catch (Exception e) {
            throw new RuntimeException("ERCOT archive list failed", e);
        }
    }

    public InputStream downloadArchive(String downloadUrl, String accessToken) {
        String subscriptionKey = config.subscriptionKey()
                .orElseThrow(() -> new IllegalStateException("ERCOT subscription key not configured"));

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(downloadUrl))
                    .header("Authorization", "Bearer " + accessToken)
                    .header("Ocp-Apim-Subscription-Key", subscriptionKey)
                    .GET()
                    .build();

            HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() != 200) {
                throw new RuntimeException("ERCOT download failed with status " + response.statusCode());
            }

            return response.body();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("ERCOT download interrupted", e);
        } catch (Exception e) {
            throw new RuntimeException("ERCOT download failed", e);
        }
    }

    private List<ArchiveEntry> parseArchiveResponse(String responseBody) throws Exception {
        List<ArchiveEntry> entries = new ArrayList<>();
        JsonNode root = objectMapper.readTree(responseBody);
        JsonNode archives = root.path("archives");

        if (archives.isArray()) {
            for (JsonNode archive : archives) {
                String fileName = archive.path("fileName").asText("");
                String docId = archive.path("docId").asText("");
                String postDatetime = archive.path("postDatetime").asText("");

                JsonNode linksNode = archive.path("_links").path("endpoint").path("href");
                String downloadUrl = linksNode.isMissingNode() ? "" : linksNode.asText("");

                if (!fileName.isEmpty() && !downloadUrl.isEmpty()) {
                    String fileType = fileName.contains("_FUL") ? "FUL" : "DAILY";
                    String tdsp = extractTdsp(fileName);
                    entries.add(new ArchiveEntry(fileName, downloadUrl, fileType, tdsp, postDatetime));
                }
            }
        }

        Log.infof("Found %d archive entries from ERCOT", entries.size());
        return entries;
    }

    private String extractTdsp(String fileName) {
        String upper = fileName.toUpperCase();
        for (String tdsp : List.of("CENTERPOINT", "ONCOR_ELEC", "AEP_CENTRAL", "AEP_NORTH",
                "AEP_TEXAS_SP", "TNMP", "LUBBOCK", "NUECES_ELEC", "ENTERGY_GULF",
                "SHARYLAND", "SWEPCO_ENERG")) {
            if (upper.contains(tdsp)) {
                return tdsp;
            }
        }
        return "UNKNOWN";
    }

    public record ArchiveEntry(
            String fileName,
            String downloadUrl,
            String fileType,
            String tdsp,
            String postDatetime
    ) {
    }
}
