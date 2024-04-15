package de.fhg.ipa.ced.monitoring.prometheus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties
public record VectorResult(String status, Data data, String errorType, String error, List<String> warnings) {
    public record Data(String resultType, List<Vector> result) {
        public record Vector (Map<String, String> metric, List<Object> value) {
        }
    }
}
