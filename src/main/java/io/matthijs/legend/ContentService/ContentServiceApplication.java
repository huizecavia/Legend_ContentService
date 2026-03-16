package io.matthijs.legend.ContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.matthijs.legend.ContentService.Model.Hike;
import io.matthijs.legend.cdaclient.ContentfulAssetService;

@RestController
@EnableCaching
@SpringBootApplication(scanBasePackages = "io.matthijs.legend")
public class ContentServiceApplication {

	private final RedisTemplate<String, String> redisTemplate;
	private final ContentfulAssetService contentfulAssetService;

	@Autowired
	public ContentServiceApplication(RedisTemplate<String, String> redisTemplate, ContentfulAssetService contentfulAssetService) {
		this.redisTemplate = redisTemplate;
		this.contentfulAssetService = contentfulAssetService;
    }

	public static void main(String[] args) {
		SpringApplication.run(ContentServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

		@GetMapping("/hike")
		public Hike getEntry() {
		Hike h = contentfulAssetService.getEntry();
		return h;
	}
		@GetMapping("/asset")
		public String getAsset() {
		var a = contentfulAssetService.getAssetUrl("4LuuEqJQIac35xejWg54Om");
		return a;
	}

}
