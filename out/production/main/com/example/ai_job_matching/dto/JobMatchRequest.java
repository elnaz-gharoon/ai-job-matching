package com.example.ai_job_matching.dto;

public class JobMatchRequest {
    private String cVText;
    private String jobDescription;

    public String getcVText() {
        return cVText;
    }

    public void setcVText(String cVText) {
        this.cVText = cVText;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }
}
