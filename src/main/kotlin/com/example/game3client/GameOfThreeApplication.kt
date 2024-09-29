package com.example.game3client

import com.example.game3client.config.PlayerConfig
import com.example.game3client.domain.service.TerminalViewService
import com.example.game3client.infrastructure.messaging.production.ClientCommandKafkaSender
import game3.events.client.command.AuthenticateCommand
import org.apache.avro.specific.SpecificRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(PlayerConfig::class)
class GameOfThreeApplication

fun main(args: Array<String>) {
    runApplication<GameOfThreeApplication>(*args)
}
