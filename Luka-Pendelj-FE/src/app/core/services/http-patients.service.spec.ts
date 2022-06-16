import { TestBed } from '@angular/core/testing';

import { HttpPatientsService } from './http-patients.service';

describe('HttpPatientsService', () => {
  let service: HttpPatientsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpPatientsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
