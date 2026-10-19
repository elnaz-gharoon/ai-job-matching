package com.example.ai_job_matching.service;

import com.example.ai_job_matching.document.JobDocument;
import com.example.ai_job_matching.entity.JobMatch;
import com.example.ai_job_matching.repository.JobMatchRepository;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.IndexRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class JobMatchService {

    private final JobMatchRepository jobMatchRepository;
    private final EmbeddingService embeddingService ;
    private final OpenSearchClient openSearchClient;



    public JobMatchService(JobMatchRepository jobMatchRepository, EmbeddingService embeddingService, OpenSearchClient openSearchClient){
        this.jobMatchRepository= jobMatchRepository;
        this.embeddingService = embeddingService;
        this.openSearchClient = openSearchClient;

    }
    public JobMatch save(JobMatch jobMatch) throws IOException {


    double[] jobEmbedding= embeddingService.createEmbedding(jobMatch.getJobDescription());
        List<Float> embedding = Arrays.stream(jobEmbedding)
                .mapToObj(value -> (float) value)
                .toList();



    JobDocument jobDocument= new JobDocument(jobMatch.getJobTitle(), jobMatch.getJobDescription(),embedding );
    IndexRequest indexRequest =
            new IndexRequest.Builder<JobDocument>().index("job-embeddings").document(jobDocument).build();

openSearchClient.index(indexRequest);

    return jobMatch;
    }

}
