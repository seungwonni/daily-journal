package com.rate.poker.core.global.config;

import com.rate.poker.core.global.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <pre>
 * 1. 패키지명 : com.alpha9.global.config
 * 2. 타입명 : WebConfig.java
 * 2. 작성일 : 2023. 3. 22. 오후 3:56:28
 * 3. 작성자 : ahyun
 * 4. 설명 : WebMvc  설정
 * </pre>
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

	private static final String CLASSPATH_RESOURCE_LOCATIONS = "classpath:/static/";

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/common/login/**"
						, "/index*"
						,"/kakao-login/**"
						, "/common/guest-login/**"
						, "/common/join*"
						, "/js/**"
						, "/css/**"
						, "/images/**"
						, "/assets/favicon.ico"
				);
	}
	
}
