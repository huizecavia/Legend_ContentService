package io.matthijs.legend.cdaclient;

import com.contentful.java.cda.rich.CDARichText;
import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.rich.CDARichBlock;
import com.contentful.java.cda.rich.CDARichDocument;
import com.contentful.java.cda.rich.CDARichNode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.matthijs.legend.ContentService.Model.Hike;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ContentfulAssetService {

    private final CDAClient client;
    private final RedisTemplate<String, String> redisTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public ContentfulAssetService(CDAClient client, RedisTemplate<String, String> redisTemplate, ObjectMapper objectMapper) {
        this.client = client;
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public String getAssetUrl(String assetId) {
        CDAAsset asset = client.fetch(CDAAsset.class).one(assetId);
        return "https:" + asset.url();
    }

    public List<Hike> getHikes() throws JsonProcessingException {

        List<Hike> hikes = new ArrayList<>();

        List<CDAEntry> fetchHikes = client.fetch(CDAEntry.class)
            .where("content_type", "hike")
            .all()
            .entries()
            .values()
            .stream()
            .toList();

        for (CDAEntry e : fetchHikes) {

            List<CDAAsset> routes = e.getField("route");
            List<String> routeUrls = new ArrayList<>();

            for (CDAAsset route : routes) {
                if (route != null) {
                    routeUrls.add(route.url());
                }
            }
            
            List<String> pictureUrls = new ArrayList<>();
            List<CDAAsset> pictures = e.getField("pictures");            

            if (pictures != null) {
                for (CDAAsset picture : pictures) {
                    pictureUrls.add(picture.url());
                }
            }

            StringBuilder beschrijving = new StringBuilder();
            CDARichDocument doc = e.getField("beschrijving");
    
            if (doc != null && doc.getContent() != null) {
                for (CDARichNode node1: doc.getContent()) {
                    if (node1 instanceof CDARichBlock block) {
                        for (CDARichNode node2: block.getContent()) {
                            if (node2 instanceof CDARichText richText) {
                                beschrijving.append(richText.getText());
                            }

                        }            
                    }
                }
            }

            Hike h = new Hike(e.getField("titel"), 
                routeUrls, 
                beschrijving.toString(), 
                e.getField("datumuitvoering"), 
                pictureUrls);

            hikes.add(h);
        }

        return hikes;
    }
}
    