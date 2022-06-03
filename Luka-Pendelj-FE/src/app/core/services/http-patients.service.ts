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

  getPatient(patientId: number){
    return this.httpPatients.get<Patient>(`http://localhost:8080/patients/${patientId}`);
  }

  createPatient(patient: Patient){
    return this.httpPatients.post<Patient>(`http://localhost:8080/patients/save`, patient);
  }

  deletePatient(patientId: number){
    return this.httpPatients.delete<Patient>(`http://localhost:8080/patients/delete/${patientId}`);
  }

  updatePatient(patient: Patient){
    return this.httpPatients.put<Patient>(`http://localhost:8080/patients/${patient.patientId}`, patient);
  }

  ///////////////////////////////////////////////////////////

  getPatientsByOrganization(organizationId: number){
    return this.httpPatients.get<Patient[]>(`http://localhost:8080/patients/organization/${organizationId}`);
  }

   getPatientsByOrganizationName(organizationName: string){
     return this.httpPatients.get<Patient[]>(`http://localhost:8080/patients/organizationName/${organizationName}`);
  }

  getPatientsByFilter(filter: string, term: string){

    return this.httpPatients.post<Patient[]>(`http://localhost:8080/patients/filter`, [filter, term]);
  }

}
