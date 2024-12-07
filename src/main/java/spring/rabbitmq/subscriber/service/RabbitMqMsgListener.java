package spring.rabbitmq.subscriber.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Map;

@Component
public class RabbitMqMsgListener {

//    @RabbitListener(queues = "neko_queue")
//    public void receiveMessage(String model) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        MsgModel map = mapper.readValue(model, MsgModel.class);
//
//        System.out.println("Received message 1: " + map.getMsg());
//    }

    @RabbitListener(queues = "neko_queue")
    public void receiveMessages(Message message) throws IOException {
        String body = new String(message.getBody());
        Map<String, Object> headers = message.getMessageProperties().getHeaders();
        String typeHeader = (String) headers.get("MsgType");
        switch (typeHeader) {
            case "type1":
                handleType1Message(body);
                break;
            case "type2":
                handleType2Message(body);
                break;
            default:
                System.out.println("Unhandled message type: " + typeHeader);
                break;
        }
    }

    private void handleType1Message(String message) {
        ObjectMapper mapper = new ObjectMapper();
        MsgModel msgModel = null;
        try {
            String stringToConvert = mapper.readValue(message, String.class);
            msgModel = mapper.readValue(stringToConvert, MsgModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Handling Type1 message: " + msgModel.getMsg());
    }

    private void handleType2Message(String message) {
        ObjectMapper mapper = new ObjectMapper();
        MsgModel msgModel = null;
        try {
            String stringToConvert = mapper.readValue(message, String.class);
            msgModel = mapper.readValue(stringToConvert, MsgModel.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Handling Type2 message: " + msgModel.getMsg());
    }
}
