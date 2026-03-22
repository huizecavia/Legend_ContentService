package io.matthijs.legend.cdaclient;

import com.contentful.java.cda.rich.CDARichText;
import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.rich.CDARichBlock;
import com.contentful.java.cda.rich.CDARichDocument;
import com.contentful.java.cda.rich.CDARichNode;

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

        // beschrijving
        StringBuilder beschrijving = new StringBuilder();

        CDARichDocument doc = e.getField("beschrijving");
        List<CDARichNode> b = doc.getContent();

        for (CDARichNode node1: doc.getContent()) {
            for (CDARichNode node2: ((CDARichBlock) node1).getContent()) {
                String text = ((CDARichText) node2).getText().toString();
                beschrijving.append(text);
            }
        }

        Hike h = new Hike(e.getField("titel"), 
            routes.get(0).url(), 
            beschrijving.toString(), 
            e.getField("datumuitvoering"), 
            pictureUrls);

        return h;
    }
}
