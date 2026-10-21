package com.example.ai_job_matching.document;

import java.util.List;

public class JobDocument {

    private String jobTitle;
    private String jobDescription;
    private List<Float> embedding;

    public JobDocument() {
    }

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

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void setEmbedding(List<Float> embedding) {
        this.embedding = embedding;
    }
}