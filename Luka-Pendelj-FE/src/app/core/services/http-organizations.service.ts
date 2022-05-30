import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Organization } from '../model/models';

@Injectable({
  providedIn: 'root'
})
export class HttpOrganizationsService {

  constructor(private httpClient: HttpClient) { }


  getAll(): Observable<Organization[]>{

    // let headers = new HttpHeaders();
    // headers = headers.set('Authorization', this.userLoginService.token!);


    return this.httpClient.get<Organization[]>(`http://localhost:8080/organizations`)

  }

  getOrganization(organizationId: number): Observable<Organization>{
      return this.httpClient.get<Organization>(`http://localhost:8080/organizations/${organizationId}`);
  }

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
