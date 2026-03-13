package io.matthijs.legend.ContentService;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ContentService {

    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Cacheable(value = "justCache")
    public String getContent() {
        Pagemodel p = restTemplate(new RestTemplateBuilder()).getForObject("https://cdn.contentful.com/spaces/gvfxre3jlkjm/entries?access_token=_39Gt27-U1xYE9IUJDDlsj2Lv9A2R9PrrjZsK4AV9T4", Pagemodel.class);
        var a = p.items().get(0);
        var b = a.fields();
        var c = b.beschrijving();
        return String.format("Hello %s!", c.toString());
    }
}
