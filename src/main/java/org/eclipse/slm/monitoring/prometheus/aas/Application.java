package org.eclipse.slm.monitoring.prometheus.aas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, OAuth2ResourceServerAutoConfiguration.class })
//@ServletComponentScan(basePackages = {"de.fhg.ipa.ced.monitoring.prometheus"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("*");
//			}
//		};
//	}

}
