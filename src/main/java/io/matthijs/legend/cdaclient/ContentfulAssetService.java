package io.matthijs.legend.cdaclient;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.rich.CDARichDocument;

import io.matthijs.legend.ContentService.Model.Hike;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentfulAssetService {

    private final CDAClient client;

    @Autowired
    public ContentfulAssetService(CDAClient client) {
        this.client = client;
    }

    public String getAssetUrl(String assetId) {
        CDAAsset asset = client.fetch(CDAAsset.class).one(assetId);
        return "https:" + asset.url();
    }

    public Hike getEntry() {

        List<CDAEntry> fetchHikes = client.fetch(CDAEntry.class)
            .where("content_type", "hike")
            .all()
            .entries()
            .values()
            .stream()
            .toList();

        CDAEntry e = fetchHikes.get(0);
        List<CDAAsset> routes = e.getField("route");
        List<CDAAsset> pictures = e.getField("pictures");
        List<String> pictureUrls = new ArrayList<>();
        pictureUrls.add(pictures.get(0).url());
        pictureUrls.add(pictures.get(1).url());

        Hike h = new Hike(e.getField("titel"), 
            routes.get(0).url(), 
            e.getField("beschrijving"), 
            e.getField("datumuitvoering"), 
            pictureUrls);

        return h;
    }
}

// // Fetch one asset by ID
// CDAAsset asset = client.fetch(CDAAsset.class).one("your-asset-id");

// // Fetch one entry by ID
// CDAEntry entry = client.fetch(CDAEntry.class).one("your-entry-id");

// // Query multiple entries (all)
// CDAArray entries = client.fetch(CDAEntry.class).all();

// // Query entries by content type
// CDAArray hikes = client.fetch(CDAEntry.class)
//     .where("content_type", "hike")
//     .all();

