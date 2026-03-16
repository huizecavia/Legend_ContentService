package io.matthijs.legend.cdaclient;

import com.contentful.java.cda.CDAAsset;
import com.contentful.java.cda.CDAClient;

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
}