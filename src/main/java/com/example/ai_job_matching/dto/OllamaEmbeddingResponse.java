package com.example.ai_job_matching.dto;

public class OllamaEmbeddingResponse {
    /*äußeres Array: mehrere Embeddings möglich
     inneres Array: die Zahlen eines Embeddings*/
    private double[][] embeddings;

    public double[][] getEmbeddings() {
        return embeddings;
    }

    public void setEmbedding(double[][] embedding) {
        this.embeddings = embedding;
    }
}
