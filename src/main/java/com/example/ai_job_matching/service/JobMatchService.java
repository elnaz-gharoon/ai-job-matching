package com.example.ai_job_matching.service;

import com.example.ai_job_matching.document.JobDocument;
import com.example.ai_job_matching.dto.JobSearchResponse;
import com.example.ai_job_matching.entity.JobMatch;
import com.example.ai_job_matching.repository.JobMatchRepository;
import org.opensearch.client.opensearch.OpenSearchClient;
import org.opensearch.client.opensearch.core.IndexRequest;
import org.springframework.stereotype.Service;
import org.opensearch.client.opensearch.core.SearchResponse;

import java.io.IOException;
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
  public List<JobSearchResponse> searchSimilarJobs(String cvText) throws IOException{

double [] cvEmbedding = embeddingService.createEmbedding(cvText);
List<Float> embeddings = Arrays.stream(cvEmbedding).mapToObj(value -> (float)value).toList();

      SearchResponse<JobDocument> response = openSearchClient.search(s -> s
              .index("job-embeddings").size(3).query(q->q.knn(k->k.field("embedding")
                      .vector(embeddings).k(3))
                      ),
                      JobDocument.class);


      return response.hits().hits().stream()
              .filter(hit -> hit.source() != null)
              .filter(hit -> hit.source().getJobTitle() != null)
              .map(hit -> new JobSearchResponse(
                      hit.source().getJobTitle(),
                      hit.source().getJobDescription(),
                      hit.score()
              ))
              .toList();
  }

}
