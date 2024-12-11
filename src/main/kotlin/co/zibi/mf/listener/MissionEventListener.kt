package co.zibi.mf.listener

import org.apache.kafka.common.protocol.Message
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class MissionEventListener {

    @KafkaListener(id = "create-mission-event", topics = ["create-mission"])
    fun listenCreateMission(message: Message) {

    }
}