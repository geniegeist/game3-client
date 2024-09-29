package com.example.game3client.application

import com.example.game3client.config.PlayerConfig
import com.example.game3client.infrastructure.messaging.production.ClientCommandKafkaSender
import game3.events.client.command.AuthenticateCommand
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val playerConfig: PlayerConfig,
    private val clientCommandSender: ClientCommandKafkaSender
) {

    @EventListener(ApplicationReadyEvent::class)
    fun authenticate() {
        clientCommandSender.send(AuthenticateCommand(playerConfig.id)).get()
    }

}