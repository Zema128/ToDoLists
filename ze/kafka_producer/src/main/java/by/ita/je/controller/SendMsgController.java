package by.ita.je.controller;

import by.ita.je.dto.SimpleDto;
import by.ita.je.service.api.SentMessage;
import org.springframework.web.bind.annotation.*;

@RestController
public class SendMsgController {
    private final SentMessage message;

    public SendMsgController(SentMessage sentMessage) {
        this.message = sentMessage;
    }

    @PostMapping("/send/")
    public void sendMsg(@RequestBody SimpleDto simpleDto){
        message.sendMessage(simpleDto);
    }
}
