import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Patient } from 'src/app/core/model/models';
import { HttpPatientsService } from 'src/app/core/services/http-patients.service';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})
export class PatientDetailsComponent implements OnInit {

  patient!: Patient;

  constructor(private activeRoute: ActivatedRoute,
    private httpPatients: HttpPatientsService,
    private router: Router) { }

  ngOnInit(): void {
    this.loadPatient();
  }

  loadPatient(){
    const patientId = Number(this.activeRoute.snapshot.paramMap.get('patientId'));
    this.httpPatients.getPatient(patientId).subscribe(pat => this.patient = pat);

  }

  editPatient(pat: Patient){
    this.router.navigate(['patients/patient-edit', pat.patientId]);
  }

  deletePatient(pat: Patient){
    var answer = window.confirm(`Are you sure that you want to delete entity with Id ${pat.patientId}?`);
    if(answer){
      this.httpPatients.deletePatient(pat.patientId).subscribe();
      this.router.navigate(['patients/patients']);
    }
  }
}
