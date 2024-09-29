package com.example.game3client.domain.messaging

import game3.events.client.command.AuthenticateCommand
import game3.events.client.command.ExecuteGameMoveCommand
import org.apache.kafka.clients.producer.RecordMetadata
import java.util.concurrent.Future

interface ClientCommandSendable {
    fun send(authenticateCommand: AuthenticateCommand): Future<RecordMetadata>
    fun send(command: ExecuteGameMoveCommand): Future<RecordMetadata>
}