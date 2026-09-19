package com.shaun.incident_api.controller;

import com.shaun.monitor.PrometheusClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/monitor")
public class MonitorController {

    @Autowired
    private PrometheusClient prometheusClient;

    @GetMapping("/query")
    public Map<String, Object> query(@RequestParam String query) {
        return prometheusClient.query(query);
    }

    @GetMapping("/query_range")
    public Map<String, Object> queryRange(
            @RequestParam String query,
            @RequestParam long start,
            @RequestParam long end,
            @RequestParam(defaultValue = "60s") String step) {
        return prometheusClient.queryRange(query, start, end, step);
    }

    @GetMapping("/overview")
    public Map<String, Object> overview() {
        Map<String, Object> result = new HashMap<>();
        result.put("up", prometheusClient.query("up{job=\"incident-backend\"}"));
        result.put("qps", prometheusClient.query(
                "sum(rate(http_server_requests_seconds_count[5m]))"));
        result.put("cpu", prometheusClient.query("process_cpu_usage"));
        result.put("memory", prometheusClient.query(
                "sum(jvm_memory_used_bytes{area=\"heap\"})"));
        result.put("threads", prometheusClient.query("jvm_threads_live_threads"));
        return result;
    }

    @GetMapping("/timeseries")
    public Map<String, Object> timeseries(@RequestParam(defaultValue = "1h") String range) {
        long end = System.currentTimeMillis() / 1000;
        long start = end - parseRange(range);
        Map<String, Object> result = new HashMap<>();
        result.put("qps", prometheusClient.queryRange(
                "sum(rate(http_server_requests_seconds_count[1m]))",
                start, end, "30s"));
        result.put("cpu", prometheusClient.queryRange(
                "process_cpu_usage",
                start, end, "30s"));
        result.put("memory", prometheusClient.queryRange(
                "sum(jvm_memory_used_bytes{area=\"heap\"})",
                start, end, "30s"));
        result.put("responseTime", prometheusClient.queryRange(
                "sum(rate(http_server_requests_seconds_sum[1m])) / sum(rate(http_server_requests_seconds_count[1m]))",
                start, end, "30s"));
        return result;
    }

    private long parseRange(String range) {
        if (range.endsWith("m"))
            return Long.parseLong(range.replace("m", "")) * 60;
        if (range.endsWith("h"))
            return Long.parseLong(range.replace("h", "")) * 3600;
        if (range.endsWith("d"))
            return Long.parseLong(range.replace("d", "")) * 86400;
        return 3600;
    }
}