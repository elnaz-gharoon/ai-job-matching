package com.example.ai_job_matching;

import com.example.ai_job_matching.controller.JobMatchController;
import com.example.ai_job_matching.dto.JobMatchRequest;
import com.example.ai_job_matching.dto.JobMatchResponse;
import com.example.ai_job_matching.entity.JobMatch;
import com.example.ai_job_matching.service.JobMatchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobMatchControllerTest {
    @Mock
    private JobMatchService
            jobMatchService;
    @InjectMocks
    private JobMatchController jobMatchController;

    @Test
    void createJobMatch_shouldReturnJobMatchResponse() {
        JobMatchRequest request = new JobMatchRequest();
        request.setCvText("Java, Spring Boot, PostgreSQL");
        request.setJobDescription("Gesucht wird ein Java Backend Developer");
        JobMatch savedJobMatch = new JobMatch();
        savedJobMatch.setCvText(request.getCvText());
        savedJobMatch.setJobDescription(request.getJobDescription());
        savedJobMatch.setMatchScore(85.0);
        savedJobMatch.setRecommendation("Gut geeignet");
        when(jobMatchService.save(org.mockito.ArgumentMatchers.any(JobMatch.class)))
                .thenReturn(savedJobMatch);
        JobMatchResponse response = jobMatchController.createJobMatch(request);
        assertEquals(85.0, response.getMatchScore());
        assertEquals("gut geeigned", response.getRecommendation());

}}
