package com.denys.ollama_integration.llm.client;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LlmClient {

    private final ChatClient chatClient;

    public String ask(String question) {
        ChatResponse response = chatClient.prompt()
                .user(u -> u
//                        .text("Answer the following question concisely: {operation}") // Can include custom prompt as a wrap under user question
                        .text("{query}") // query it is param of question in Spring AI
                        .param("query", question))
                .call()
                .chatResponse();

        return response.getResult().getOutput().getText();
    }
}
