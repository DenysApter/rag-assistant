package com.denys.rag_assistant.controller;

import com.denys.rag_assistant.controller.dto.request.UserRequest;
import com.denys.rag_assistant.controller.dto.response.UserResponse;
import com.denys.rag_assistant.service.data.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserRequest userRequest) {
        var user = userService.create(userRequest);
        log.info("user created: {}", user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable UUID id) {
        var user =  UserResponse.from(userService.getById(id));
        return ResponseEntity.ok().body(user);
    }
}
