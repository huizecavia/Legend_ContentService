package io.matthijs.legend.ContentService.Model;
import java.util.List;

public record Hike(
        String titel,
        String route,
        String beschrijving,
        String datumuitvoering,
        List<String> pictures
) {
    
}
