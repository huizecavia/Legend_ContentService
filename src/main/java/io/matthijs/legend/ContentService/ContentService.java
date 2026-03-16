package io.matthijs.legend.ContentService;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



import io.matthijs.legend.ContentService.Model.AssetModel;
import io.matthijs.legend.ContentService.Model.Pagemodel;

@Service
public class ContentService {

    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }



    @Cacheable(value = "justCache")
    public String getCacheContent() {
        Pagemodel p = restTemplate(new RestTemplateBuilder()).getForObject("https://cdn.contentful.com/spaces/gvfxre3jlkjm/entries?access_token=_39Gt27-U1xYE9IUJDDlsj2Lv9A2R9PrrjZsK4AV9T4", Pagemodel.class);
        var a = p.items().get(0);
        var b = a.fields();
        var c = b.beschrijving();
        return String.format("Hello %s!", c.toString());
    }

    public String getAsset() {
        
        AssetModel a = restTemplate(new RestTemplateBuilder()).getForObject("https://cdn.contentful.com/spaces/gvfxre3jlkjm/assets/5XBKPhi2aFxVLizTVn8c9L?access_token=_39Gt27-U1xYE9IUJDDlsj2Lv9A2R9PrrjZsK4AV9T4", AssetModel.class);
        String b = a.toString();
        return "Huh?";
    }

    public String mapContent() {
			Pagemodel p = restTemplate(new RestTemplateBuilder()).getForObject("https://cdn.contentful.com/spaces/gvfxre3jlkjm/entries?access_token=_39Gt27-U1xYE9IUJDDlsj2Lv9A2R9PrrjZsK4AV9T4", Pagemodel.class);
            var a = p.items().get(0).fields();

            var titel = a.titel();
            var beschrijving = a.beschrijving();
            var route = a.route().get(0);
            var datumuitvoering = a.datumuitvoering();
            var pictures = a.pictures();

// all entries
// https://www.contentful.com/developers/docs/references/content-delivery-api/#/reference/content-types

// all assets
// https://cdn.contentful.com/spaces/gvfxre3jlkjm/assets?access_token=_39Gt27-U1xYE9IUJDDlsj2Lv9A2R9PrrjZsK4AV9T4

// single asset
// https://cdn.contentful.com/spaces/gvfxre3jlkjm/assets/5XBKPhi2aFxVLizTVn8c9L?access_token=_39Gt27-U1xYE9IUJDDlsj2Lv9A2R9PrrjZsK4AV9T4

// titel=Hodister,
// route=[{sys={type=Link, linkType=Asset, id=4LuuEqJQIac35xejWg54Om}}],
// beschrijving={data={}, content=[{data={}, content=[{data={}, marks=[], value=Loop van Boverie via Warisy naar Hodister., nodeType=text}], nodeType=paragraph}, {data={}, content=[{data={}, marks=[], value=Neem waterdichte kleding mee voor de terugweg, nodeType=text}], nodeType=paragraph}], nodeType=document},
// datumuitvoering=2023-02-03T00:00+01:00,
// pictures=[{sys={type=Link, linkType=Asset, id=7u8caWHnlOVqziB4Sh1LY4}}, {sys={type=Link, linkType=Asset, id=5XBKPhi2aFxVLizTVn8c9L}}]]!

		// return new Field(titel, beschrijving, route, datumuitvoering, pictures);
        return "Ja!";


    }
}
