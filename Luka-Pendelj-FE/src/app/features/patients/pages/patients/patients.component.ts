import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { NgbPagination } from '@ng-bootstrap/ng-bootstrap';
import { Patient } from 'src/app/core/model/models';
import { HttpPatientsService } from 'src/app/core/services/http-patients.service';

@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.css']
})
export class PatientsComponent implements OnInit {

  patientsList?: Patient[];

  filterPatients?: FormGroup;

  pageNo: number = 1;

  pageSize: number = 5;

  totalItems = 20;


  constructor(private httpPatient: HttpPatientsService,
              private router: Router) { }

  ngOnInit(): void {
    this.loadPatients();
    this.createFormGroup();
  }

  createFormGroup(){
    this.filterPatients=new FormGroup({
      filterSelect: new FormControl(''),
      filterText: new FormControl('')
    });
  }

  loadPatients(){
    //this.httpPatient.getAll().subscribe(patients => this.patientsList=patients);
    this.httpPatient.getSome((this.pageNo-1), this.pageSize).subscribe(
        patientPage=> {
        this.patientsList = patientPage.content;
        this.totalItems = patientPage.totalElements;
        this.pageSize = patientPage.size;
        }
    )
  }

  patientDetails(pat: Patient){
    this.router.navigate(['patients/patient-details', pat.patientId]);
  }

  deletePatient(pat: Patient){
    var answer = window.confirm(`Are you sure that you want to delete entity with Id ${pat.patientId}?`);
    if(answer){
      this.httpPatient.deletePatient(pat.patientId).subscribe();
      location.reload();
    }
  }

  editPatient(pat: Patient){
    this.router.navigate(['patients/patient-edit', pat.patientId]);
  }



  onPageChange(page: number){
      this.loadPatients();
  }

  pagination?: NgbPagination;

  filterPatientsByFilter(term: string){
    const filter = this.filterPatients?.get('filterText')?.value;

    this.httpPatient.getPatientsByFilter(term, filter).subscribe(patients => this.patientsList=patients);

    //this.pagination?.hidden = true;
  }

  // filterPatientsByFirstName(){

  // }

  // filterPatientsByLastName(){

  // }

  // filterPatientsByCompany(value: string){
  //  this.httpPatient.getPatientsByOrganizationName(value).subscribe(patients => this.patientsList=patients);
  // }

  // filterPatientsByMedicName(){

  // }

  clearFilter(){
    this.loadPatients();
  }
}
