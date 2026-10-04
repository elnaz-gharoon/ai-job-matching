package com.example.ai_job_matching.service;

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
        return new double[0];
    }
}
