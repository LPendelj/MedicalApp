import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Medic } from '../model/models';

//Observable, HttpClient, (HttpHeaders), 'model'

@Injectable({
  providedIn: 'root'
})
export class HttpMedicsService {

  constructor(private httpMedics: HttpClient) { }

  

  //Observable of type ModelArray.
  //http.method<Model>(`url`, (headers))
  getAll(): Observable<Medic[]>{
    return this.httpMedics.get<Medic[]>(`http://localhost:8080/practitioners`);
  }

  getMedic(medicId: number): Observable<Medic>{
    return this.httpMedics.get<Medic>(`http://localhost:8080/practitioners/${medicId}`);
  }

  createMedic(medic: Medic): Observable<Medic>{
    return this.httpMedics.post<Medic>(`http://localhost:8080/practitioners/save`, medic);
  }

  deleteMedic(medicId: number){

  }

}
