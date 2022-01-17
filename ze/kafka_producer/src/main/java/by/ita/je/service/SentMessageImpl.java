package by.ita.je.service;

import by.ita.je.controller.SimpleDto;
import by.ita.je.service.api.SentMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class SentMessageImpl implements SentMessage {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public SentMessageImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Value(value = "${spring.kafka.topic.name}")
    private  String topicName;

    @Override
    public void sendMessage(SimpleDto msg){
        ListenableFuture<SendResult<String, Object>> future =
                kafkaTemplate.send(topicName, msg);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("Sent message=[" + msg +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]" +
                        " topic = " + result.getRecordMetadata().topic() + " partition = " +
                        result.getRecordMetadata().partition());
            }

            @Override
            public void onFailure(Throwable ex) {
                System.err.println("Unable to send message=["
                        + msg + "] due to : " + ex.getMessage());
            }
        });
    }
}