import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrganizationType } from '../model/models';
import { HttpOrganizationsService } from './http-organizations.service';

@Injectable({
  providedIn: 'root'
})
export class HttpOrganizationTypesService {

  constructor(private httpOrganizationTypeService: HttpClient) { }

  getAll():Observable<OrganizationType[]>{
    return this.httpOrganizationTypeService.get<OrganizationType[]>(`http://localhost:8080/organizationtype`);
  }
}
