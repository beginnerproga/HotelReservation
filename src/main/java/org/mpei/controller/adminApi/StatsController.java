package org.mpei.controller.adminApi;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Endpoint(id = "stats")
public class StatsController {
    private final AtomicInteger requestCount = new AtomicInteger();
    private final Map<Integer, Integer> responseCodes = new ConcurrentHashMap<>();
    @Value("${app.version}")
    private String version;
    private String startTime;

    @PostConstruct
    public void init() {
        startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void recordRequest(int statusCode) {
        requestCount.incrementAndGet();
        responseCodes.merge(statusCode, 1, Integer::sum);
    }

    @ReadOperation
    public Map<String, Object> stats() {
        return Map.of(
                "version", version,
                "start-date", startTime,
                "requestCount", requestCount.get(),
                "responseCodes", responseCodes
        );
    }
}