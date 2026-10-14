package com.example.ai_job_matching.service;

import com.example.ai_job_matching.dto.OllamaEmbeddingRequest;
import com.example.ai_job_matching.dto.OllamaEmbeddingResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
@Service
public class OllamaEmbeddingService implements EmbeddingService {
private final RestClient restClient;



    public OllamaEmbeddingService(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public double[] createEmbedding(String text) {
        OllamaEmbeddingRequest request = new OllamaEmbeddingRequest();

        request.setModel("nomic-embed-text");
        request.setInput(text);

        OllamaEmbeddingResponse response = restClient.post()
                .uri("/api/embed")
                .body(request)
                .retrieve()
                .body(OllamaEmbeddingResponse.class);
        System.out.println("Ollama response" + response);
        if (response == null || response.getEmbeddings() == null || response.getEmbeddings().length == 0){
            throw new IllegalStateException("Ollama hat keine Embeddings zurückgegeben.");
        }

        return response.getEmbeddings()[0];
    }
}

