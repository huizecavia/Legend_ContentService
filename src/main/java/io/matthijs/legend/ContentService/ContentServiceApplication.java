package io.matthijs.legend.ContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.matthijs.legend.ContentService.Model.Hike;
import io.matthijs.legend.ContentService.Model.Pagemodel;
import io.matthijs.legend.cdaclient.ContentfulAssetService;

@RestController
@EnableCaching
@SpringBootApplication(scanBasePackages = "io.matthijs.legend")
public class ContentServiceApplication {

	private final RedisTemplate<String, String> redisTemplate;
	private final ContentService contentService;
	private final ContentfulAssetService contentfulAssetService;

	@Autowired
	public ContentServiceApplication(RedisTemplate<String, String> redisTemplate, ContentService contentService, ContentfulAssetService contentfulAssetService) {
		this.redisTemplate = redisTemplate;
        this.contentService = contentService;
		this.contentfulAssetService = contentfulAssetService;
    }

	public static void main(String[] args) {
		SpringApplication.run(ContentServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
				Pagemodel p = restTemplate(new RestTemplateBuilder()).getForObject("https://cdn.contentful.com/spaces/gvfxre3jlkjm/entries?access_token=_39Gt27-U1xYE9IUJDDlsj2Lv9A2R9PrrjZsK4AV9T4", Pagemodel.class);
				var a = p.items().get(0);
				var b = a.fields();
		return String.format("Hello %s!", b);
	}


	@GetMapping("/put")
	public String put(@RequestParam(value = "name", defaultValue = "World") String name) {

		String key = "sleutel";
		String value = name;
		redisTemplate.opsForValue().set(key, value);

		return "maybe";
	}

	@GetMapping("/get")
	public String get() {
		String key = "sleutel";
		return redisTemplate.opsForValue().get(key);
	}


	@GetMapping("/just")
	public String just() {
		String content = contentService.getCacheContent();
		return content;
	}

	@GetMapping("/map")
	public String map() {
		String s = contentService.mapContent();
		return "Nee";
	}

		@GetMapping("/entry")
		public Hike getEntry() {
		Hike h = contentfulAssetService.getEntry();
		return h;
	}
		@GetMapping("/cda")
		public String cda() {
		var a = contentfulAssetService.getAssetUrl("4LuuEqJQIac35xejWg54Om");
		return a;
	}

}
