package com.example.ai_job_matching.document;

import java.util.List;

public class JobDocument {
    String jobTitle;
    String jobDescription;
    List<Float> embedding;
    public JobDocument(String jobTitle, String jobDescription, List<Float> embedding) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.embedding = embedding;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public List<Float> getEmbedding() {
        return embedding;
    }
}
