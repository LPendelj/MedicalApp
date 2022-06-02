import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Medic } from 'src/app/core/model/models';
import { HttpMedicsService } from 'src/app/core/services/http-medics.service';

@Component({
  selector: 'app-medics',
  templateUrl: './medics.component.html',
  styleUrls: ['./medics.component.css']
})
export class MedicsComponent implements OnInit {

  medicsList?: Medic[];
  //httpMedic, Router
  constructor(private httpMedic: HttpMedicsService,
    private router: Router) { }

  ngOnInit(): void {
    //method call
    this.loadMedics()
  }

  //httpS | method: list | subscribe()  (arrowF with userDefined varname for return value of getAll method)
  loadMedics(){
    this.httpMedic.getAll().subscribe(medics =>this.medicsList = medics);
  }

  medicDetails(medic: Medic){
    this.router.navigate(['practitioners/practitioner-details', medic.medicId]);
  }


  deleteMedic(medic: Medic){
    var answer = window.confirm(`Are you sure that you want to delete entity with Id ${medic.medicId}?`);
    if(answer){
    this.httpMedic.deleteMedic(medic.medicId).subscribe();
    location.reload();
    }
  }

  editMedic(medic: Medic){
    this.router.navigate(['practitioners/practitioner-edit', medic.medicId]);
  }
}
