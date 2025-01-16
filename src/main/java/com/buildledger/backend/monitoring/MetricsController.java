package com.buildledger.backend.monitoring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class MetricsController {

    @Autowired
    private MetricsEndpoint metricsEndpoint;

    @GetMapping("/api/metrics")
    public Map<String, Object> getMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("jvmMemoryUsed", metricsEndpoint.metric("jvm.memory.used", null));
        metrics.put("httpRequests", metricsEndpoint.metric("http.server.requests", null));
        metrics.put("dbConnections", metricsEndpoint.metric("db.connections.active", null));
        metrics.put("systemCpuUsage", metricsEndpoint.metric("system.cpu.usage", null));
        metrics.put("processCpuUsage", metricsEndpoint.metric("process.cpu.usage", null));
        metrics.put("systemLoadAverage", metricsEndpoint.metric("system.load.average.1m", null));
        metrics.put("availableProcessors", Runtime.getRuntime().availableProcessors());
        metrics.put("freeDiskSpace", metricsEndpoint.metric("disk.free", null));
        metrics.put("totalDiskSpace", metricsEndpoint.metric("disk.total", null));
        return metrics;
    }


}
