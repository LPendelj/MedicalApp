import { TestBed } from '@angular/core/testing';

import { HttpGendersService } from './http-genders.service';

describe('HttpGendersService', () => {
  let service: HttpGendersService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpGendersService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
