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

  getExamination(examId: number){
    return this.httpExaminations.get<Examination>(`http://localhost:8080/examinations/${examId}`);
  }
}
