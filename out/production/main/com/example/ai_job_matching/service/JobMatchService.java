package com.example.ai_job_matching.service;

import com.example.ai_job_matching.entity.JobMatch;
import com.example.ai_job_matching.repository.JobMatchRepository;
import org.springframework.stereotype.Service;

@Service
public class JobMatchService {
    private final JobMatchRepository jobMatchRepository;
    public JobMatchService(JobMatchRepository jobMatchRepository){
        this.jobMatchRepository= jobMatchRepository;
    }
    public JobMatch save(JobMatch jobMatch){
        return jobMatchRepository.save(jobMatch);
    }

}
