package com.example.ai_job_matching.dto;

public class JobSearchResponse {
    private String jobTitle;
    private String jobDescription;
    private double similarityScore;

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public double getSimilarityScore() {
        return similarityScore;
    }

    public void setSimilarityScore(double similarityScore) {
        this.similarityScore = similarityScore;
    }

    public JobSearchResponse(String jobTitle, String jobDescription, Double similarityScore) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.similarityScore = similarityScore;
    }


    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }
}
