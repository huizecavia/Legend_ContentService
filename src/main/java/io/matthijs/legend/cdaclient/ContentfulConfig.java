package io.matthijs.legend.cdaclient;

import com.contentful.java.cda.CDAClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContentfulConfig {

    @Bean
    public CDAClient contentfulClient() {
        return CDAClient.builder()
                .setSpace("gvfxre3jlkjm")
                .setToken("_39Gt27-U1xYE9IUJDDlsj2Lv9A2R9PrrjZsK4AV9T4")
                .build();
    }
}