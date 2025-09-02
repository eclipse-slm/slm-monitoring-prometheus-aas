package org.eclipse.slm.monitoring.prometheus.aas.submodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.digitaltwin.aas4j.v3.model.Submodel;
import org.eclipse.digitaltwin.basyx.submodelservice.SubmodelService;
import org.eclipse.slm.monitoring.prometheus.aas.query.RangeVectorResult;
import org.eclipse.slm.monitoring.prometheus.aas.query.VectorResult;
import org.eclipse.slm.monitoring.prometheus.aas.exceptions.SubmodelNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger LOG = LoggerFactory.getLogger(PlatformResourcesSubmodelFactory.class);

    private final String prometheusHost;
    private final int prometheusPort;

    PlatformResourcesSubmodelFactory(
            @Value("${prometheus.host}") String prometheusHost,
            @Value("${prometheus.port}") int prometheusPort) {
        this.prometheusHost = prometheusHost;
        this.prometheusPort = prometheusPort;
    }

    public Submodel createSubmodel(String resourceId) {
        var objectMapper = new ObjectMapper();
        var submodel = new PlatformResourcesSubmodel("PlatformResources-" + resourceId);

        try {
            var restTemplate = new RestTemplate();
            var queryUrl = new URI("http", null, prometheusHost, prometheusPort, "/api/v1/query", "query={resource_id=\"" + resourceId + "\"}", null);
            var result = restTemplate.getForObject(queryUrl, VectorResult.class);

            if (result.data().result().size() == 0) {
                LOG.info("No prometheus metrics found for resource '{}', returning empty submodel: ", resourceId);
                return submodel;
            }

            float memTotalBytes = 1;
            for (VectorResult.Data.Vector vector : result.data().result()) {
                var metric = vector.metric();
                switch (metric.get("__name__")) {
                    case "node_os_info" -> submodel.setOS(metric.get("pretty_name"));
                    case "node_uname_info" -> submodel.setProArc(metric.get("machine"));
                    case "node_memory_MemTotal_bytes" -> {
                        memTotalBytes = Float.parseFloat((String) vector.value().get(1));
                        submodel.setMemTotal(memTotalBytes / 1000);
                    }
                }
            }

            queryUrl = new URI("http", null, prometheusHost, prometheusPort, "/api/v1/query", "query=node_memory_MemAvailable_bytes{resource_id=\"" + resourceId + "\"}[1m]", null);
            var rangeResult = restTemplate.getForObject(queryUrl, RangeVectorResult.class);
            if (rangeResult.data().result().size() > 0) {
                RangeVectorResult.Data.Vector vector = rangeResult.data().result().get(0);
                List<Float> allocatedMemoryHistogram = new ArrayList<>();
                for (List<Object> value : vector.values()) {
                    float availableMemoryBytes = Float.parseFloat((String) value.get(1));
                    allocatedMemoryHistogram.add((1 - availableMemoryBytes / memTotalBytes) * 100);
                }
                var graphDefinition = Map.of("AllocatedMemory", allocatedMemoryHistogram);
                submodel.setAllocatedMemoryHistogram(objectMapper.writeValueAsString(graphDefinition));
                submodel.setAllocatedMemory(allocatedMemoryHistogram.get(allocatedMemoryHistogram.size() - 1));
            }

            queryUrl = new URI("http", null, prometheusHost, prometheusPort, "/api/v1/query", "query=(100-(avg(irate(node_cpu_seconds_total{mode=\"idle\", resource_id=\"" + resourceId + "\"}[1m]))*100))[1m:1s]", null);
            rangeResult = restTemplate.getForObject(queryUrl, RangeVectorResult.class);
            if (rangeResult.data().result().size() > 0) {
                RangeVectorResult.Data.Vector vector = rangeResult.data().result().get(0);
                List<Float> utilizedCPUHistogram = new ArrayList<>();
                for (List<Object> value : vector.values()) {
                    float utilizedCPU = Float.parseFloat((String) value.get(1));
                    utilizedCPUHistogram.add(utilizedCPU);
                }
                var graphDefinition = Map.of("CPULoad", utilizedCPUHistogram);
                submodel.setCPULoadHistogram(objectMapper.writeValueAsString(graphDefinition));
                submodel.setCPULoad(utilizedCPUHistogram.get(utilizedCPUHistogram.size() - 1));
            }

            return submodel;
        } catch (JsonProcessingException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public SubmodelService getSubmodelService(String resourceId) {
        var submodel = this.createSubmodel(resourceId);
        var submodelService = new PlatformResourcesSubmodelService(submodel);

        return submodelService;
    }
}
