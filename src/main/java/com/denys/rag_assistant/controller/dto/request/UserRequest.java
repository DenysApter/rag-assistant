package com.denys.rag_assistant.controller.dto.request;

import com.denys.rag_assistant.persistence.entity.Role;

public record UserRequest(String name, Role role) {
}
