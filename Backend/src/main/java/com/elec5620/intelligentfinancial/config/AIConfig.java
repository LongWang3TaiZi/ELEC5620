package com.elec5620.intelligentfinancial.config;

import io.github.sashirestela.openai.SimpleOpenAI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {
    @Bean
    public SimpleOpenAI simpleOpenAI() {
        return SimpleOpenAI.builder()
                .apiKey("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")
                .build();
    }
}
