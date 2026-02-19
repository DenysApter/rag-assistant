package com.denys.rag_assistant.controller.dto.response;

import com.denys.rag_assistant.persistence.entity.MessageEntity;

import java.time.Instant;

public record MessageResponse(DialogResponse dialog, String question, String answer, Instant createdAt) {

    public static MessageResponse from(MessageEntity entity) {
        return new MessageResponse(
                DialogResponse.from(entity.getDialog()),
                entity.getQuestion(),
                entity.getAnswer(),
                entity.getCreatedAt()
        );
    }
}
