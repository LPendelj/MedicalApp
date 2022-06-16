import { TestBed } from '@angular/core/testing';

import { HttpOrganizationTypesService } from './http-organization-types.service';

describe('HttpOrganizationTypesService', () => {
  let service: HttpOrganizationTypesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HttpOrganizationTypesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
