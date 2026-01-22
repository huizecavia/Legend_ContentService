package io.matthijs.legend.ContentService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Pagemodel(
        int total,
        int limit,
        List<Object> items

) {
}