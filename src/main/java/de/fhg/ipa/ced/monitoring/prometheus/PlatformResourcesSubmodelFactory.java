package de.fhg.ipa.ced.monitoring.prometheus;

import com.google.gson.Gson;
import org.eclipse.basyx.submodel.metamodel.api.identifier.IdentifierType;
import org.eclipse.basyx.submodel.metamodel.map.Submodel;
import org.eclipse.basyx.submodel.metamodel.map.identifier.Identifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class PlatformResourcesSubmodelFactory {

    private final String prometheusHost;
    private final int prometheusPort;

    PlatformResourcesSubmodelFactory(
            @Value("${prometheus.host}") String prometheusHost,
            @Value("${prometheus.port}") int prometheusPort) {
        this.prometheusHost = prometheusHost;
        this.prometheusPort = prometheusPort;
    }

    public Submodel createSubmodel(String id) {
        RestTemplate restTemplate = new RestTemplate();
        URI queryUrl;
        try {
            queryUrl = new URI("http", null, prometheusHost, prometheusPort, "/api/v1/query", "query={slm_id=\""+id+"\"}", null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        VectorResult result = restTemplate.getForObject(queryUrl, VectorResult.class);
        PlatformResourcesSubmodel sm = new PlatformResourcesSubmodel(new Identifier(IdentifierType.CUSTOM, "PlatformResources-" + id));
        Map<String, String> metric;
        float memTotalBytes = 1;

        for (VectorResult.Data.Vector vector : result.data().result()) {
            metric = vector.metric();
            switch (metric.get("__name__")) {
                case "node_os_info" -> sm.setOS(metric.get("pretty_name"));
                case "node_uname_info" -> sm.setProArc(metric.get("machine"));
                case "node_memory_MemTotal_bytes" -> {
                    memTotalBytes = Float.parseFloat((String) vector.value().get(1));
                    sm.setMemTotal(memTotalBytes / 1000);
                }
            }
        }
        try {
            queryUrl = new URI("http", null, prometheusHost, prometheusPort, "/api/v1/query", "query=node_memory_MemAvailable_bytes{slm_id=\""+id+"\"}[1m]", null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        RangeVectorResult rangeResult = restTemplate.getForObject(queryUrl, RangeVectorResult.class);
        if (rangeResult.data().result().size() > 0) {
            RangeVectorResult.Data.Vector vector = rangeResult.data().result().get(0);
            List<Float> allocatedMemoryHistogram = new ArrayList<>();
            for (List<Object> value : vector.values()) {
                float availableMemoryBytes = Float.parseFloat((String) value.get(1));
                allocatedMemoryHistogram.add((1 - availableMemoryBytes / memTotalBytes) * 100);
            }
            sm.setAllocatedMemoryHistogram(new Gson().toJson(allocatedMemoryHistogram));
            sm.setAllocatedMemory(allocatedMemoryHistogram.get(allocatedMemoryHistogram.size()-1));
        }

        try {
            queryUrl = new URI("http", null, prometheusHost, prometheusPort, "/api/v1/query", "query=(100-(avg(irate(node_cpu_seconds_total{mode=\"idle\", slm_id=\""+id+"\"}[1m]))*100))[1m:1s]", null);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        rangeResult = restTemplate.getForObject(queryUrl, RangeVectorResult.class);
        if (rangeResult.data().result().size() > 0) {
            RangeVectorResult.Data.Vector vector = rangeResult.data().result().get(0);
            List<Float> utilizedCPUHistogram = new ArrayList<>();
            for (List<Object> value : vector.values()) {
                float utilizedCPU = Float.parseFloat((String) value.get(1));
                utilizedCPUHistogram.add(utilizedCPU);
            }
            sm.setCPULoadHistogram(new Gson().toJson(utilizedCPUHistogram));
            sm.setCPULoad(utilizedCPUHistogram.get(utilizedCPUHistogram.size() - 1));
        }

        return sm;
    }
}