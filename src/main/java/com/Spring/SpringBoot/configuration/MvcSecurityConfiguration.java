package com.Spring.SpringBoot.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class MvcSecurityConfiguration  implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry)
    {
        registry.addConverter(new MultipartConverter());
    }
}
