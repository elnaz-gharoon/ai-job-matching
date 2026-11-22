import { TestBed } from '@angular/core/testing';

import { JobMatch } from './job-match';

describe('JobMatch', () => {
  let service: JobMatch;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JobMatch);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
