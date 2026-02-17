package com.denys.ollama_integration.db.qdrant;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QdrantService {

    private final VectorStore vectorStore;

    public void addChunks(List<Document> chunks) {
        vectorStore.add(chunks);
    }

    public void deleteByIds(List<String> ids) {
        vectorStore.delete(ids);
    }
}
