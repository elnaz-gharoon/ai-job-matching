package com.example.ai_job_matching.dto;

public class OllamaEmbeddingResponse {
    /*äußeres Array: mehrere Embeddings möglich
     inneres Array: die Zahlen eines Embeddings*/
    private double[][] embeddings;

    public double[][] getEmbeddings() {
        return embeddings;
    }

    public void setEmbedding(double[][] embeddings) {
        this.embeddings = embeddings;
    }
    @Override
    public String toString() {
        return "OllamaEmbeddingResponse{" +
                "embeddings=" + (embeddings == null
                ? "null"
                : "Anzahl: " + embeddings.length) +
                '}';
    }
}
