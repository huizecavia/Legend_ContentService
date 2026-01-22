package io.matthijs.legend.ContentService;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.matthijs.Item;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Pagemodel(
        List<Item> items

) {
}