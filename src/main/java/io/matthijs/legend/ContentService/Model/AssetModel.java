package io.matthijs.legend.ContentService.Model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AssetModel(
    List<Object> assets
) {
    
}
