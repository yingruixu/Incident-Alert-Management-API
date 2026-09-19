package com.shaun.monitor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class PrometheusClient {

    @Value("${prometheus.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> query(String query) {
        String url = baseUrl + "/api/v1/query?query="
                + URLEncoder.encode(query, StandardCharsets.UTF_8);
        return restTemplate.getForObject(url, Map.class);
    }

    public Map<String, Object> queryRange(String query, long start, long end, String step) {
        String url = baseUrl + "/api/v1/query_range?query="
                + URLEncoder.encode(query, StandardCharsets.UTF_8)
                + "&start=" + start + "&end=" + end + "&step=" + step;
        return restTemplate.getForObject(url, Map.class);
    }
}