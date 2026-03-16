package io.matthijs.legend.cdaclient;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;

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

    public String getEntry() {

        CDAArray entries = client.fetch(CDAEntry.class).all();
        String q = entries.toString();

        List<CDAEntry> fetchHikes = client.fetch(CDAEntry.class)
            .where("content_type", "hike")
            .all()
            .entries()
            .values()
            .stream()
            .toList();

        CDAEntry e = fetchHikes.get(0);
        String title = e.getField("titel");
        
        return "Got it";
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

