package com.denys.rag_assistant.controller.dto.response;

import com.denys.rag_assistant.persistence.entity.DialogEntity;

import java.time.Instant;

public record DialogResponse(String title, Instant createdAt) {

    public static DialogResponse from(DialogEntity entity) {
        return new DialogResponse(entity.getTitle(), entity.getCreatedAt());
    }
}
