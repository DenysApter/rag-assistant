package com.denys.rag_assistant.controller;

import com.denys.rag_assistant.ai.KnowledgeBaseIngestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
public class KnowledgeBaseController {

    private final KnowledgeBaseIngestionService knowledgeBaseIngestionService;

    @Value("${app.data-lake.path}")
    private String dataLakePath;

    @PostMapping("/ingest")
    public String ingest() {
        knowledgeBaseIngestionService.ingestDataLake();
        return "Data lake ingested successfully";
    }

    @PostMapping(value = "/upload/admin", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadAdmin(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            knowledgeBaseIngestionService.saveFile(file, "admin");
        } catch (IllegalArgumentException ex) {
            return "File not uploaded to admin, file is incorrect: " + file.getOriginalFilename();
        }
        knowledgeBaseIngestionService.ingestDataLake();
        return "File uploaded to admin: " + file.getOriginalFilename();
    }

    @PostMapping(value = "/upload/user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadUser(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            knowledgeBaseIngestionService.saveFile(file, "user");
        } catch (IllegalArgumentException ex) {
            return "File not uploaded to user, file is incorrect: " + file.getOriginalFilename();
        }

        knowledgeBaseIngestionService.ingestDataLake();
        return "File uploaded to user: " + file.getOriginalFilename();
    }
}
