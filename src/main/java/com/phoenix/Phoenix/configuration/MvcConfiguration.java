package com.phoenix.Phoenix.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("forward:/landing/page");
        registry.addViewController("/").setViewName("forward:/landing/pageAdmin");
    }

    @Bean
    public LocaleResolver localeResolver(){
        var session = new SessionLocaleResolver();
        var indonesia = new Locale("id", "ID");
        session.setDefaultLocale(indonesia);
        return session;
    }
}
