package com.denys.ollama_integration.controller;

import com.denys.ollama_integration.llm.rag.DataLakeIngestionService;
import com.denys.ollama_integration.service.AskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final AskService askService;
    private final DataLakeIngestionService ingestionService;

    @GetMapping("/ask")
    public String ask(@RequestParam String question) {
        return askService.ask(question);
    }

    @PostMapping("/ingest")
    public String ingest() {
        ingestionService.ingestDataLake();
        return "Data lake ingested successfully";
    }
}
