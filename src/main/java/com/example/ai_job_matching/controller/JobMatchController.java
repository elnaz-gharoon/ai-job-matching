package com.example.ai_job_matching.controller;

import com.example.ai_job_matching.document.JobDocument;
import com.example.ai_job_matching.dto.JobMatchRequest;
import com.example.ai_job_matching.dto.JobMatchResponse;
import com.example.ai_job_matching.dto.JobSearchResponse;
import com.example.ai_job_matching.entity.JobMatch;
import com.example.ai_job_matching.service.JobMatchService;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/job-matches")
@CrossOrigin(origins = "http://localhost:4200")
public class JobMatchController {
    private final JobMatchService jobMatchService;

    public JobMatchController(JobMatchService jobMatchService) {
        this.jobMatchService = jobMatchService;
    }

    @PostMapping
    public JobMatchResponse createJobMatch(@RequestBody JobMatchRequest request) throws IOException {
        JobMatch jobMatch = new JobMatch();

        jobMatch.setCvText(request.getCvText());
        jobMatch.setJobDescription(request.getJobDescription());
        jobMatch.setJobTitle(request.getJobTitle());
       JobMatch saveJobMatched = jobMatchService.save(jobMatch);
        String jobTitle = jobMatch.getJobTitle();

       JobMatchResponse response = new JobMatchResponse();
       response.setId(saveJobMatched.getId());
       response.setMatchScore(saveJobMatched.getMatchScore());
       response.setRecommendation(saveJobMatched.getRecommendation());

       return response;
    }
    @GetMapping("/search")
    public List<JobSearchResponse> searchSimilarJobs (@RequestParam  String cvText) throws IOException{
     return jobMatchService.searchSimilarJobs(cvText);
    }
}
