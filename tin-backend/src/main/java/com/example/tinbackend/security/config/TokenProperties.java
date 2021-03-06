package com.example.tinbackend.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security.jwt")
@Getter
@Setter
public class TokenProperties {

    private String loginPath = "/api/login";
    private String header = "Authorization";
    private String prefix = "Bearer ";
    private int expiration = 86400;
    private String secret = "JwtSecretKey";
}
