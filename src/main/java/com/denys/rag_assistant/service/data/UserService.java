package com.denys.rag_assistant.service.data;

import com.denys.rag_assistant.controller.dto.request.UserRequest;
import com.denys.rag_assistant.persistence.entity.Role;
import com.denys.rag_assistant.persistence.entity.UserEntity;
import com.denys.rag_assistant.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity create(UserRequest userRequest) {
        var user = new UserEntity();
        user.setName(userRequest.name());
        user.setRole(userRequest.role());
        return userRepository.save(user);
    }

    public UserEntity getById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + id));
    }

    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }
}
