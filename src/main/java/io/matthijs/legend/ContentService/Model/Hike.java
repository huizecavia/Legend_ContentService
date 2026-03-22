package io.matthijs.legend.ContentService.Model;
import java.util.List;

import com.contentful.java.cda.rich.CDARichDocument;

public record Hike(
        String titel,
        String route,
        String beschrijving,
        String datumuitvoering,
        List<String> pictures
) {
    
}
