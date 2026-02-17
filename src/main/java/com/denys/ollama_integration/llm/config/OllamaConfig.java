package com.denys.ollama_integration.llm.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaConfig {

    private static final String RAG_PROMPT_TEMPLATE = """
            Below is additional context that MAY be relevant to the user's question.
            Use it only if it is directly relevant. Otherwise, answer the question using your own knowledge.

            Context:
            {question_answer_context}

            User question:
            {query}
            """;

    @Bean
    public ChatClient chatClient(OllamaChatModel ollamaChatModel, VectorStore vectorStore) {
        return ChatClient.builder(ollamaChatModel)
                .defaultAdvisors(new SimpleLoggerAdvisor(),
                        QuestionAnswerAdvisor.builder(vectorStore)
                                .searchRequest(SearchRequest.builder()
                                        .topK(3)
                                        .similarityThreshold(0.3)
                                        .build())
                                .promptTemplate(new PromptTemplate(RAG_PROMPT_TEMPLATE))
                                .build())
                .build();
    }
}
