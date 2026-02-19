package com.denys.rag_assistant.controller;

import com.denys.rag_assistant.controller.dto.request.DialogRequest;
import com.denys.rag_assistant.controller.dto.response.DialogResponse;
import com.denys.rag_assistant.controller.dto.response.MessageResponse;
import com.denys.rag_assistant.persistence.entity.UserEntity;
import com.denys.rag_assistant.service.data.DialogService;
import com.denys.rag_assistant.service.data.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/dialogs")
@RequiredArgsConstructor
@Slf4j
public class DialogController {

    private final DialogService dialogService;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> createDialog(@RequestBody DialogRequest dialogRequest) {
        UserEntity user;
        try {
            user = userService.getById(dialogRequest.userId());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        var dialog = dialogService.create(user, dialogRequest.title().orElse("Dialog Title"));
        log.info("Dialog created: {}", dialog);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<DialogResponse> getDialogs(@RequestParam UUID userId) {
        return dialogService.getByUserId(userId).stream()
                .map(DialogResponse::from)
                .toList();
    }

    @GetMapping("/{dialogId}/messages")
    public List<MessageResponse> getMessages(@PathVariable UUID dialogId) {
        return dialogService.getMessages(dialogId).stream()
                .map(MessageResponse::from)
                .toList();
    }
}
