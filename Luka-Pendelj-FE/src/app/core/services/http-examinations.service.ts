import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Examination } from '../model/models';

@Injectable({
  providedIn: 'root'
})
export class HttpExaminationsService {

  constructor(private httpExaminations: HttpClient) { }

  getAll(): Observable<Examination[]>{
    return this.httpExaminations.get<Examination[]>(`http://localhost:8080/examinations`);
  }

  getSome(pageNo: number, pageSize: number){
    return this.httpExaminations.get<any>(`http://localhost:8080/examinations/filter?pageNo=${pageNo}&pageSize=${pageSize}`);
  }

  getExamination(examId: number){
    return this.httpExaminations.get<Examination>(`http://localhost:8080/examinations/${examId}`);
  }

  createExamination(examination: Examination){
    return this.httpExaminations.post<Examination>(`http://localhost:8080/examinations/save`, examination);
  }

  deleteExamination(examinationId: number){
    return this.httpExaminations.delete<Examination>(`http://localhost:8080/examinations/delete/${examinationId}`);
  }

  updateExamination(examination: Examination){
    return this.httpExaminations.put<Examination>(`http://localhost:8080/examinations/${examination.examinationId}`, examination);
  }

  ///////////////////////////////////////////////////////////
}
