package io.matthijs.legend.ContentService.Model;
import java.io.Serializable;
import java.util.List;

import com.contentful.java.cda.rich.CDARichDocument;

public record Hike(
        String titel,
        List<String> routes,
        String beschrijving,
        String datumuitvoering,
        List<String> pictures
) implements Serializable {
    
}
