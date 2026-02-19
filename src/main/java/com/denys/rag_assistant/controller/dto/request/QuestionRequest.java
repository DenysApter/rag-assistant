package com.denys.rag_assistant.controller.dto.request;

import java.io.Serializable;
import java.util.UUID;

public record QuestionRequest(UUID dialogId, String question) implements Serializable {
}
