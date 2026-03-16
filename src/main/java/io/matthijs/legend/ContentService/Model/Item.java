package io.matthijs.legend.ContentService.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Item(
        Field fields
) {
}
