import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Patient } from 'src/app/core/model/models';
import { HttpPatientsService } from 'src/app/core/services/http-patients.service';

@Component({
  selector: 'app-patients',
  templateUrl: './patients.component.html',
  styleUrls: ['./patients.component.css']
})
export class PatientsComponent implements OnInit {

  patientsList?: Patient[];

  constructor(private httpPatient: HttpPatientsService,
              private router: Router) { }

  ngOnInit(): void {
    this.loadPatients();
  }

  loadPatients(){
    this.httpPatient.getAll().subscribe(patients => this.patientsList=patients);
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
}
