import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ServiceType } from '../model/models';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceTypeService {

  constructor(private httpServiceTypeService: HttpClient) { }

  getAll():Observable<ServiceType[]>{
    return this.httpServiceTypeService.get<ServiceType[]>(`http://localhost:8080/servicetype`);
  }
}
