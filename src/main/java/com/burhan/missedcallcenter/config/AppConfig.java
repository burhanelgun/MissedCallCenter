package com.burhan.missedcallcenter.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ConfigurationProperties(prefix = "app")
@Component
@Data
public class AppConfig implements WebMvcConfigurer {
    private String language;
}