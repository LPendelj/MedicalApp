import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Organization } from '../model/models';

@Injectable({
  providedIn: 'root'
})
export class HttpOrganizationsService {

  constructor(private httpOrganizations: HttpClient) { }


  getAll(): Observable<Organization[]>{

    // let headers = new HttpHeaders();
    // headers = headers.set('Authorization', this.userLoginService.token!);


    return this.httpOrganizations.get<Organization[]>(`http://localhost:8080/organizations`)

  }

  getOrganization(organizationId: number): Observable<Organization>{
      return this.httpOrganizations.get<Organization>(`http://localhost:8080/organizations/${organizationId}`);
  }

  //CHECK!!
  createOrganization(organization: Organization): Observable<Organization>{

    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    return this.httpOrganizations.post<Organization>(`http://localhost:8080/organizations/save`, organization, {headers:headers});
  }

  deleteOrganization(organizationId: number){
    return this.httpOrganizations.delete<Organization>(`http://localhost:8080/organizations/delete/${organizationId}`);
  }

  updateOrganization(organization: Organization){
    return this.httpOrganizations.put<Organization>(`http://localhost:8080/organizations/${organization.organizationId}`, organization);
  }

  ///////////////////////////////////////////////////////////



}

/* const headers = new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded');
    return this.httpClient.post<UserDetails>(`http://localhost:8080/api/auth/login`, params.toString(), {headers}); */

// OVOOOOOOOOO:

    /* getAll(): Observable<City[]>{

    // let headers = new HttpHeaders();
    // headers = headers.set('Authorization', this.userLoginService.token!);


    return this.httpClient.get<City[]>(
        `${environment.serverUrl}/api/city`
    )
  } */
