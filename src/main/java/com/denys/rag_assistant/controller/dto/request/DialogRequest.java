package com.denys.rag_assistant.controller.dto.request;


import java.util.Optional;
import java.util.UUID;

public record DialogRequest(UUID userId, Optional<String> title) {
}
