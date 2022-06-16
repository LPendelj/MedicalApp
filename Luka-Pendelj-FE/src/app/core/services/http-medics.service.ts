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

  getSome(pageNo: number, pageSize: number){
    return this.httpMedics.get<any>(`http://localhost:8080/practitioners/filter?pageNo=${pageNo}&pageSize=${pageSize}`);
  }

  getMedic(medicId: number): Observable<Medic>{
    return this.httpMedics.get<Medic>(`http://localhost:8080/practitioners/${medicId}`);
  }

  createMedic(medic: Medic): Observable<Medic>{
    return this.httpMedics.post<Medic>(`http://localhost:8080/practitioners/save`, medic);
  }

  deleteMedic(medicId: number){
    return this.httpMedics.delete<Medic>(`http://localhost:8080/practitioners/delete/${medicId}`);
  }

  updateMedic(medic: Medic){
    return this.httpMedics.put<Medic>(`http://localhost:8080/practitioners/${medic.medicId}`, medic);
  }

  ///////////////////////////////////////////////////////////

  getMedicsByOrganization(organizationId: number){
    return this.httpMedics.get<Medic[]>(`http://localhost:8080/practitioners/organization/${organizationId}`);
  }

  getMedicsByFilter(filter: string, term: string){

    return this.httpMedics.post<Medic[]>(`http://localhost:8080/practitioners/filter`, [filter, term]);
  }

}
