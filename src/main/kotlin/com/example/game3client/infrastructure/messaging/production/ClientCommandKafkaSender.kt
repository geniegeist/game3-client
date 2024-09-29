package com.example.game3client.infrastructure.messaging.production

import com.example.game3client.domain.messaging.ClientCommandSendable
import game3.events.client.command.AuthenticateCommand
import game3.events.client.command.ExecuteGameMoveCommand
import org.apache.avro.specific.SpecificRecord
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import org.springframework.stereotype.Component
import java.util.concurrent.Future

@Component
class ClientCommandKafkaSender(
    private val kafkaProducer: KafkaProducer<String, SpecificRecord>
) : ClientCommandSendable {

    companion object {
        private const val TOPIC = "game3.events.client.command"
    }

    override fun send(authenticateCommand: AuthenticateCommand): Future<RecordMetadata> = kafkaProducer.send(ProducerRecord(TOPIC, authenticateCommand.playerId, authenticateCommand ))

    override fun send(command: ExecuteGameMoveCommand): Future<RecordMetadata> = kafkaProducer.send(ProducerRecord(TOPIC, command.playerId, command ))
}
