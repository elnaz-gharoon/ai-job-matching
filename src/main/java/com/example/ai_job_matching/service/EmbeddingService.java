package com.example.ai_job_matching.service;

import java.util.ArrayList;
import java.util.List;

public interface EmbeddingService {

  double[] createEmbedding(String text);
}
