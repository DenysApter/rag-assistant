package com.denys.rag_assistant.controller.dto.response;

import com.denys.rag_assistant.persistence.entity.Role;
import com.denys.rag_assistant.persistence.entity.UserEntity;

import java.time.Instant;

public record UserResponse(String name, Role role, Instant createdAt) {

    public static UserResponse from(UserEntity entity) {
        return new UserResponse(entity.getName(), entity.getRole(), entity.getCreatedAt());
    }
}
