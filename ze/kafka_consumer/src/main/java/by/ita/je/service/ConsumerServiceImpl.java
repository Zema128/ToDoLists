package by.ita.je.service;

import by.ita.je.dto.SimpleDto;
import by.ita.je.service.api.ConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @KafkaListener(topics = "TestTopic", groupId = "group1")
    public void consume(SimpleDto message){
        log.info("INCOMING MESS: " + message.toString());
    }
}
