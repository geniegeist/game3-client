package com.example.game3client.application

import com.example.game3client.config.PlayerConfig
import com.example.game3client.infrastructure.messaging.production.ClientCommandKafkaSender
import game3.events.client.command.ExecuteGameMoveCommand
import game3.events.server.command.ViableGameActionsCommand
import org.springframework.stereotype.Service

@Service
class GameService(
    private val playerConfig: PlayerConfig,
    private val commandSender: ClientCommandKafkaSender
) {
    fun handle(command: ViableGameActionsCommand) = filter(command)?.let { commandSender.send(ExecuteGameMoveCommand(command.playerId, command.actions.first())) }

    fun filter(command: ViableGameActionsCommand) = if (command.playerId == playerConfig.id) command else null

}
