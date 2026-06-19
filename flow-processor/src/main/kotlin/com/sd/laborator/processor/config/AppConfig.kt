package com.sd.laborator.processor.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Duration

/**
 * Beans de infrastructura. RestTemplate este clientul HTTP folosit atat pentru
 * comunicarea cu registrul, cat si pentru chat-ul peer-to-peer.
 */
@Configuration
class AppConfig {

    @Bean
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate =
        builder
            .setConnectTimeout(Duration.ofSeconds(3))
            .setReadTimeout(Duration.ofSeconds(3))
            .build()
}
