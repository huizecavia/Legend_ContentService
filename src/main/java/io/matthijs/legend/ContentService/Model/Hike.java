package io.matthijs.legend.ContentService.Model;
import java.util.List;

import com.contentful.java.cda.rich.CDARichDocument;

public record Hike(
        String titel,
        String route,
        CDARichDocument beschrijving,
        String datumuitvoering,
        List<String> pictures
) {
    
}
