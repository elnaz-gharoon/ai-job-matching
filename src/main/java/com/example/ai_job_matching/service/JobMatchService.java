package com.example.ai_job_matching.service;

import com.example.ai_job_matching.entity.JobMatch;
import com.example.ai_job_matching.repository.JobMatchRepository;
import org.springframework.stereotype.Service;

@Service
public class JobMatchService {

    private final JobMatchRepository jobMatchRepository;
    private final EmbeddingService embeddingService ;

    public JobMatchService(JobMatchRepository jobMatchRepository, EmbeddingService embeddingService){
        this.jobMatchRepository= jobMatchRepository;
        this.embeddingService = embeddingService;
    }
    public JobMatch save(JobMatch jobMatch){
    double[] cvEmbedding = embeddingService.createEmbedding(jobMatch.getCvText());
    double[] jobEmbedding= embeddingService.createEmbedding(jobMatch.getJobDescription());
    return jobMatch;
    }

}
