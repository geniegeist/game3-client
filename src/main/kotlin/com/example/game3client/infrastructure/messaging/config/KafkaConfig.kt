package com.example.game3client.infrastructure.messaging.config

import com.example.game3client.config.PlayerConfig
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig
import org.apache.avro.specific.SpecificRecord
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.KafkaProducer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import java.util.*

@Configuration
@EnableKafka
class KafkaConfig(
   private val playerConfig: PlayerConfig,
) {

   companion object {
      private const val BOOTSTRAP_SERVERS = "localhost:9092"
      private const val SCHEMA_REGISTRY_URL = "http://localhost:8085"
   }

   @Bean
   fun consumerFactory() = DefaultKafkaConsumerFactory<String, SpecificRecord>(kafkaConsumerProps())

   @Bean
   fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, SpecificRecord> {
      val factory = ConcurrentKafkaListenerContainerFactory<String, SpecificRecord>()
      factory.consumerFactory = consumerFactory()
      return factory
   }

   @Bean
   fun kafkaSender() = KafkaProducer<String, SpecificRecord>(kafkaSenderProps())

   private fun kafkaConsumerProps() = mapOf(
      ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to BOOTSTRAP_SERVERS,
      ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to "org.apache.kafka.common.serialization.StringDeserializer",
      ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to "io.confluent.kafka.serializers.KafkaAvroDeserializer",
      "schema.registry.url" to SCHEMA_REGISTRY_URL,
      ConsumerConfig.GROUP_ID_CONFIG to playerConfig.id,
      KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG to true,
   )

   private fun kafkaSenderProps(): Properties {
      val props = Properties()
      props["bootstrap.servers"] = BOOTSTRAP_SERVERS
      props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
      props["value.serializer"] = "io.confluent.kafka.serializers.KafkaAvroSerializer"
      props["schema.registry.url"] = SCHEMA_REGISTRY_URL
      props[KafkaAvroSerializerConfig.VALUE_SUBJECT_NAME_STRATEGY] = "io.confluent.kafka.serializers.subject.TopicRecordNameStrategy"
      return props
   }
}