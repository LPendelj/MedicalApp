import { TestBed } from '@angular/core/testing';

import { HttpOrganizationsService } from './http-organizations.service';

describe('HttpOrganizationsService', () => {
  let service: HttpOrganizationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpOrganizationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
