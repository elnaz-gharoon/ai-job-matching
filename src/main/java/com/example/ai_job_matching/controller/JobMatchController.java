package com.example.ai_job_matching.controller;

import com.example.ai_job_matching.dto.JobMatchRequest;
import com.example.ai_job_matching.dto.JobMatchResponse;
import com.example.ai_job_matching.entity.JobMatch;
import com.example.ai_job_matching.service.JobMatchService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/job-matches")

public class JobMatchController {
    private final JobMatchService jobMatchService;

    public JobMatchController(JobMatchService jobMatchService) {
        this.jobMatchService = jobMatchService;
    }

    @PostMapping
    public JobMatchResponse createJobMatch(@RequestBody JobMatchRequest request){
        JobMatch jobMatch = new JobMatch();

        jobMatch.setCvText(request.getCvText());
        jobMatch.setJobDescription(request.getJobDescription());
       JobMatch saveJobMatched = jobMatchService.save(jobMatch);

       JobMatchResponse response = new JobMatchResponse();
       response.setId(saveJobMatched.getId());
       response.setMatchScore(saveJobMatched.getMatchScore());
       response.setRecommendation(saveJobMatched.getRecommendation());
       return response;

    }

}
