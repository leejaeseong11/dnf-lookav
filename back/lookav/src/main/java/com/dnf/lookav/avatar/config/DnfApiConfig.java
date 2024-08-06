package com.dnf.lookav.avatar.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DnfApiConfig {
    @Value("${dnf.api.key}")
    public String apiKey;
}
