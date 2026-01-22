package io.matthijs;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.matthijs.legend.ContentService.Field;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Item(
        Field fields
) {
}
