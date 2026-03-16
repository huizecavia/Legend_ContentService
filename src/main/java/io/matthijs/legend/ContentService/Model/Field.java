package io.matthijs.legend.ContentService.Model;

import java.util.List;

public record Field(
        String titel,
        Object beschrijving,
        List<Asset> route,
        String datumuitvoering,
        Object pictures
) {
    
}
