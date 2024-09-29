package com.example.game3client.infrastructure.messaging.consumption

import com.example.game3client.application.GameService
import com.example.game3client.application.ViewService
import com.example.game3client.config.PlayerConfig
import game3.events.server.command.ClearClientViewCommand
import game3.events.server.command.PrintMessageToClientCommand
import game3.events.server.command.ViableGameActionsCommand
import org.apache.avro.specific.SpecificRecord
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class UniversalKafkaConsumer(
    private val viewService: ViewService,
    private val playerConfig: PlayerConfig,
    private val gameService: GameService
) {
    @KafkaListener(
       topics = ["game3.events.server.command"]
   )
   fun listen(data: SpecificRecord) {
       when (data) {
           is PrintMessageToClientCommand -> viewService.handle(data)
           is ClearClientViewCommand -> viewService.handle(data)
           is ViableGameActionsCommand -> gameService.handle(data)
           else -> println("Unhandled event: $data")
       }
   }
}