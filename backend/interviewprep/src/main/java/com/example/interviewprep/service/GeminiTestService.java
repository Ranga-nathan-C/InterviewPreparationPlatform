package com.example.interviewprep.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class GeminiTestService {

    private final ChatClient chatClient;

    public GeminiTestService(ChatClient.Builder chatClientBuilder) {

        this.chatClient = chatClientBuilder.build();
    }

    public String testGemini() {
        return chatClient
                .prompt("what is famous in  trichy,tamilNadu ?")
                .call()
                .content();
    }
}