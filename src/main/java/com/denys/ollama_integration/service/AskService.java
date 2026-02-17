package com.denys.ollama_integration.service;

import com.denys.ollama_integration.llm.client.LlmClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AskService {

    private final LlmClient llmClient;

    public String ask(String question) {
        return llmClient.ask(question);
    }
}
