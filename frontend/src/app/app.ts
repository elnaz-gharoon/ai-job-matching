
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { JobMatch } from './job-match';
import { DecimalPipe } from '@angular/common';
import { NgFor } from '@angular/common';
@Component({
  selector: 'app-root',
  imports: [FormsModule, NgFor, DecimalPipe],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  cvText = '';
  jobDescription = '';
   results: any[] = [];
  constructor(private jobMatch: JobMatch) {}

  calculateMatch() {
     this.jobMatch.searchSimilarJobs(this.cvText).subscribe({
        next: (response) => {
          console.log('Backend Response:', response);
          this.results = response;
        },
               error: (error) => {
                 console.error('Error:', error);
               }
       });
  }
     getMatchStatus(score: number): string{
       return score >= 0.85 ? 'good': 'poor';
       }
     getMatchMessage(score: number):string{
       return score >= 0.85
       ? 'Die Stellenanzeige könnte gut zu deinem Profil passen.'
        : 'Die Stellenanzeige scheint weniger gut zu deinem Profil zu passen.';
        }
}
