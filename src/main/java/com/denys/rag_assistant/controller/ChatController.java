package com.denys.rag_assistant.controller;

import com.denys.rag_assistant.controller.dto.request.QuestionRequest;
import com.denys.rag_assistant.service.DialogFacade;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.UUID;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final DialogFacade dialogFacade;

    @PostMapping("ask/response")
    public ResponseEntity<String> ask(@RequestBody QuestionRequest questionRequest) {
        log.info("Non streaming question received: {}", questionRequest);
        var answer = dialogFacade.ask(questionRequest);
        log.info("Non streaming answer generated: {}", answer);
        return ResponseEntity.ok().body(answer);
    }

    @PostMapping(value = "ask/streaming", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter askStreaming(@RequestBody QuestionRequest questionRequest) {
        log.info("Streaming question received: {}", questionRequest);
        return dialogFacade.askStreaming(questionRequest);
    }
}
