import { TestBed } from '@angular/core/testing';

import { HttpExaminationsService } from './http-examinations.service';

describe('HttpExaminationsService', () => {
  let service: HttpExaminationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpExaminationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
