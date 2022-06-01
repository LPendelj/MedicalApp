import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Gender, Medic, Organization, Patient } from 'src/app/core/model/models';
import { HttpMedicsService } from 'src/app/core/services/http-medics.service';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';
import { HttpPatientsService } from 'src/app/core/services/http-patients.service';

@Component({
  selector: 'app-patient-add',
  templateUrl: './patient-add.component.html',
  styleUrls: ['./patient-add.component.css']
})
export class PatientAddComponent implements OnInit {

  addPatientForm?: FormGroup;

  patient!: Patient;

  //organizationCheck?: Organization;

  organizations?: Organization[];

  medics?: Medic[];

  gender?: Gender[] = [
    {genderCode: 'm', genderName: 'male'},
    {genderCode: 'f', genderName: 'female'},
    {genderCode: 'o', genderName: 'other'},
    {genderCode: 'u', genderName: 'unknown'}
     ];

  maritalStatus?: string[] = [
    'Annulled',
    'Divorced',
    'Married',
    'Polygamous',
    'Never Married',
    'Widowed',
    'Unknown'
  ]

  constructor(private httpPatient: HttpPatientsService,
              private httpMedic: HttpMedicsService,
              private httpOrganization: HttpOrganizationsService,
              private router: Router) { }

  ngOnInit(): void {
    this.loadOrganizations();
    this.loadMedics();
    this.createPatientForm();
  }



  createPatientForm(){
    this.addPatientForm = new FormGroup({
      firstname: new FormControl('', Validators.required),
      lastname: new FormControl(''),
      patientCode: new FormControl(''),
      gender: new FormControl(''),
      birthDate: new FormControl('', Validators.required),
      address: new FormControl(''),
      email: new FormControl('', Validators.email),
      phone: new FormControl(''),
      deceased: new FormControl(''),
      maritalStatus: new FormControl(''),
      organization: new FormControl(''),
      mainMedic: new FormControl('')
    });
  }

  loadOrganizations(){
    this.httpOrganization.getAll().subscribe(organizations => this.organizations = organizations);
  }

  loadMedics(){
    this.httpMedic.getAll().subscribe(medics =>this.medics = medics);
    //this.medics = this.medics?.filter((medic, organization)=>medic.organization===this.organizationCheck?.organizationId)
  }



  createPatient(){
      this.patient = this.addPatientForm?.getRawValue();
      console.log(this.patient);

      this.httpPatient.createPatient(this.patient).subscribe();
  }
}


