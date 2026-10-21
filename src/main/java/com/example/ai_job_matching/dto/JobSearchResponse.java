package com.example.ai_job_matching.dto;

public class JobSearchResponse {
    private String jobTitle;
    private String jobDescription;

    public JobSearchResponse(String jobTitle, String jobDescription) {
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }
}
