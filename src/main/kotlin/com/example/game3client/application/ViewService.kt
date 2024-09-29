package com.example.game3client.application

import com.example.game3client.config.PlayerConfig
import com.example.game3client.domain.view.ViewDrawable
import game3.events.server.command.ClearClientViewCommand
import game3.events.server.command.PrintMessageToClientCommand
import org.springframework.stereotype.Service

@Service
class ViewService(
    private val viewDrawable: ViewDrawable,
    private val playerConfig: PlayerConfig
) {
    fun handle(command: PrintMessageToClientCommand) = filter(command)?.let { viewDrawable.printMessage(it.message) }

    fun handle(command: ClearClientViewCommand) = filter(command)?.let { viewDrawable.clear() }

    fun filter(command: PrintMessageToClientCommand) = if (command.playerId == playerConfig.id) command else null

    fun filter(command: ClearClientViewCommand) = if (command.playerId == playerConfig.id) command else null
}