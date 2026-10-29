import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})

export class JobMatch {
   private apiUrl = 'http://localhost:8080/api/job-matches';
  constructor(private http: HttpClient) {}
  // Sendet den Lebenslauf und die Stellenbeschreibung an das Spring-Boot-Backend,
  // damit beide Texte miteinander verglichen werden können.
  createJobMatch(cvText: string, jobDescription: string){
    const request= {
      cvText: cvText,
      jobDescription: jobDescription
      };
    return this.http.post(this.apiUrl, request);
    }

  searchSimilarJobs(cvText: string) {
    return this.http.get<any[]>(
      `${this.apiUrl}/search`,
      {
        params: { cvText: cvText }
      }
    );
  }
}
