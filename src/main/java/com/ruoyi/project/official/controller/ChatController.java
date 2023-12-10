package com.ruoyi.project.official.controller;


import com.plexpt.chatgpt.ChatGPTStream;
import com.plexpt.chatgpt.entity.chat.Message;
import com.ruoyi.common.utils.GPTEventSourceListener;
import com.ruoyi.common.utils.KeyManager;
import com.ruoyi.framework.aspectj.lang.annotation.BalanceCache;
import com.ruoyi.project.official.service.ChatService;
import com.ruoyi.project.system.service.RedisService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "chat对话")
@RestController
@RequestMapping("/official/chat")
public class ChatController {

    @Autowired(required = false)
    private RedisService redisService;

    @Autowired
    private KeyManager keyManager;

    static Proxy proxy = Proxy.NO_PROXY;


    @Autowired
    private ChatService chatService;

    //记录上下文
    private static Map<String, List<Message>> context = new HashMap<>();


    @BalanceCache
    @GetMapping(value = "")
    public SseEmitter chat( String id,String prompt) {




        String APIKEY = keyManager.getKey();
        ChatGPTStream chatGPTStream = ChatGPTStream.builder()
                .timeout(90)
                .apiKey(APIKEY)
                .apiHost("https://api.chatanywhere.com.cn/")
                .build()
                .init();
        SseEmitter sseEmitter = new SseEmitter(-1L);

        GPTEventSourceListener listener = new GPTEventSourceListener(sseEmitter);
        Message message = Message.of(prompt);
        List<Message> messages = get(id);
        messages.add(message);
        listener.setOnComplate(msg -> {
            add(id, message);
            add(id, Message.ofAssistant(msg));
        });
        chatGPTStream.streamChatCompletion(messages, listener);
        return sseEmitter;
    }


    public List<Message> get(String id) {
        List<Message> messages = context.get(id);
        if (messages == null) {
            messages = new ArrayList<>();
            context.put(id, messages);
        }
        System.out.println("上下文"+context);
        return messages;
    }

    public void add(String id, Message message) {
        List<Message> messages = context.get(id);
        if (messages == null) {
            messages = new ArrayList<>();
            context.put(id, messages);
        }
        messages.add(message);
    }


}
