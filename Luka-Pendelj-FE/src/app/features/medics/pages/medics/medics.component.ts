import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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

  pageNo = 1;
  pageSize = 5;

  totalItems = 20;

  filterMedics?: FormGroup;


  constructor(private httpMedic: HttpMedicsService,
    private router: Router) { }

  ngOnInit(): void {
    //method call
    this.createFormGroup();

    this.loadMedics()
  }

  onPageChange(pageNo: number){
    this.loadMedics();
  }

  createFormGroup(){
    this.filterMedics=new FormGroup({
      filterSelect: new FormControl(''),
      filterText: new FormControl('')
    });
  }

  //httpS | method: list | subscribe()  (arrowF with userDefined varname for return value of getAll method)
  loadMedics(){
    //this.httpMedic.getAll().subscribe(medics =>this.medicsList = medics);
    this.httpMedic.getSome((this.pageNo-1), this.pageSize).subscribe(
      medicPage=> {
      this.medicsList = medicPage.content;
      this.totalItems = medicPage.totalElements;
      this.pageSize = medicPage.size;
         }
      )
    }


    filterMedicsByFilter(term: string){

        const filter = this.filterMedics?.get('filterText')?.value;

        this.httpMedic.getMedicsByFilter(term, filter).subscribe(meds => this.medicsList=meds);
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
// function filterMedicsByFilter(filter: any, any: any) {
//   throw new Error('Function not implemented.');
// }

