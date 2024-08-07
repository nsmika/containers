package ru.netology.profiledemo.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.profiledemo.model.DevProfile;
import ru.netology.profiledemo.model.ProductionProfile;
import ru.netology.profiledemo.model.SystemProfile;

@Configuration
public class ProfileConfig {

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "true", matchIfMissing = true)
    public SystemProfile devProfile() {
        return new DevProfile();
    }

    @Bean
    @ConditionalOnProperty(name = "netology.profile.dev", havingValue = "false", matchIfMissing = false)
    public SystemProfile prodProfile() {
        return new ProductionProfile();
    }
}