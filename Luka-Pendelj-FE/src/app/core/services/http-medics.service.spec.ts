import { TestBed } from '@angular/core/testing';

import { HttpMedicsService } from './http-medics.service';

describe('HttpMedicsService', () => {
  let service: HttpMedicsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpMedicsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
