package com.example.ai_job_matching.dto;

public class OllamaEmbeddingRequest {
    private String input;
    private  String model;

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
