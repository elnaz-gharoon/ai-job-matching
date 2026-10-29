package com.example.ai_job_matching;


import com.example.ai_job_matching.entity.JobMatch;
import com.example.ai_job_matching.repository.JobMatchRepository;
import com.example.ai_job_matching.service.EmbeddingService;
import com.example.ai_job_matching.service.JobMatchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import com.example.ai_job_matching.dto.JobSearchResponse;
import org.opensearch.client.opensearch.core.SearchResponse;
import org.opensearch.client.opensearch.core.search.Hit;
import com.example.ai_job_matching.document.JobDocument;
import org.opensearch.client.opensearch.core.search.HitsMetadata;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.IndexRequest;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


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

    @Test
    void searchSimilarJobs_shouldReturnJobWithSimilarityScore() throws IOException {
        String cvText = "Java Entwicklerin mit Erfahrung in Spring Boot";
        double [] embedding = {0.1, 0.2, 0.3};
        when(embeddingService.createEmbedding(cvText)).thenReturn(embedding);
        List<Float> jobEmbedding = List.of(0.1f, 0.2f, 0.3f);
        JobDocument jobDocument = new JobDocument(
                "Junior Java Backend Developer",
                "Java, Spring Boot, REST APIs und PostgreSQL",
                jobEmbedding
        );
        Hit<JobDocument> hit = Hit.of(h -> h
                .index("job-embeddings")
                .id("1")
                .score(0.95)
                .source(jobDocument)
        );

        // Arrange: OpenSearch-Antwort simulieren
        @SuppressWarnings("unchecked")
        SearchResponse<JobDocument> searchResponse =
                mock(SearchResponse.class);

        @SuppressWarnings("unchecked")
        HitsMetadata<JobDocument> hitsMetadata =
                mock(HitsMetadata.class);

        when(hitsMetadata.hits()).thenReturn(List.of(hit));
        when(searchResponse.hits()).thenReturn(hitsMetadata);

        when(openSearchClient.search(
                any(
                        java.util.function.Function.class
                ),
                org.mockito.ArgumentMatchers.eq(JobDocument.class)
        )).thenReturn(searchResponse);

        // Act: Die echte Service-Methode aufrufen
        List<JobSearchResponse> results =
                jobMatchService.searchSimilarJobs(cvText);

        // Assert: Das Ergebnis überprüfen
        assertEquals(1, results.size());
        assertEquals(
                "Junior Java Backend Developer",
                results.get(0).getJobTitle()
        );
        assertEquals(
                "Java, Spring Boot, REST APIs und PostgreSQL",
                results.get(0).getJobDescription()
        );
        assertEquals(
                0.95,
                results.get(0).getSimilarityScore(),
                0.0001
        );

        verify(embeddingService).createEmbedding(cvText);
    }

}
