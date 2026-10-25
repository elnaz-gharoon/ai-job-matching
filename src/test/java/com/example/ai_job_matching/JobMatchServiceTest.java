package com.example.ai_job_matching;


import com.example.ai_job_matching.entity.JobMatch;
import com.example.ai_job_matching.repository.JobMatchRepository;
import com.example.ai_job_matching.service.EmbeddingService;
import com.example.ai_job_matching.service.JobMatchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.IndexRequest;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class JobMatchServiceTest {
    @Mock
    private JobMatchRepository jobMatchRepository;

    @Mock
    private EmbeddingService embeddingService;

    @Mock
    private OpenSearchClient openSearchClient;

    @InjectMocks
    private JobMatchService jobMatchService;

@Test
    void save_shouldCreateEmbeddingAndIndexJobDocument() throws IOException {

        JobMatch jobMatch = new JobMatch();
        jobMatch.setJobTitle("Junior Java Backend Developer");
        jobMatch.setJobDescription("Java, Spring Boot, REST APIs und PostgreSQL");
        double [] embedding = {0.1,0.2,0.3};
        when(embeddingService.createEmbedding(jobMatch.getJobDescription())).thenReturn(embedding);
        JobMatch result = jobMatchService.save(jobMatch);
        assertSame(jobMatch, result);
    verify(embeddingService).createEmbedding(jobMatch.getJobDescription());
  ArgumentCaptor<IndexRequest> index = ArgumentCaptor.forClass(IndexRequest.class);
  verify(openSearchClient).index(index.capture());
    IndexRequest capturedRequest = index.getValue();
    assertEquals( "job-embeddings", capturedRequest.index());

    }

}
