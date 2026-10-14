
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { JobMatch } from './job-match';

@Component({
  selector: 'app-root',
  imports: [FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  cvText = '';
  jobDescription = '';

  constructor(private jobMatch: JobMatch) {}

  calculateMatch() {
      console.log('Calculate ////Match wurde geklickt');
    this.jobMatch.createJobMatch(this.cvText, this.jobDescription)
      .subscribe({
        next: (response) => {
          console.log('Backend Response:', response);
        },
        error: (error) => {
          console.error('Error:', error);
        }
      });
  }
}
