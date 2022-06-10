import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Gender } from '../model/models';

@Injectable({
  providedIn: 'root'
})
export class HttpGendersService {

  constructor(private httpGenders: HttpClient) { }

  getAll(): Observable<Gender[]>{
    return this.httpGenders.get<Gender[]>(`http://localhost:8080/genders`);
  }

  

}
