package com.example.game3client.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "player")
data class PlayerConfig(
    val id: String
)
