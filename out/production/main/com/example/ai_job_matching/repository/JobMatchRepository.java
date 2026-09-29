package com.example.ai_job_matching.repository;

import com.example.ai_job_matching.entity.JobMatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobMatchRepository extends JpaRepository<JobMatch, Long> {

}
