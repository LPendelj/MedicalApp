import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Patient } from '../model/models';

@Injectable({
  providedIn: 'root'
})
export class HttpPatientsService {

  constructor(private httpPatients: HttpClient) { }

  getAll(): Observable<Patient[]>{
    return this.httpPatients.get<Patient[]>(`http://localhost:8080/patients`);
  }
}
