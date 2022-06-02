import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Gender, Medic, Organization, Patient } from 'src/app/core/model/models';
import { HttpMedicsService } from 'src/app/core/services/http-medics.service';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';
import { HttpPatientsService } from 'src/app/core/services/http-patients.service';

@Component({
  selector: 'app-patient-edit',
  templateUrl: './patient-edit.component.html',
  styleUrls: ['./patient-edit.component.css']
})
export class PatientEditComponent implements OnInit {

  editPatientForm!: FormGroup;

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
              private router: Router,
              private activeRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.loadOrganizations();
    this.loadMedics();
    this.createPatientForm();
    this.loadPatient();
  }



  createPatientForm(){
    this.editPatientForm = new FormGroup({
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

  loadPatient(){
    const patientId = Number(this.activeRoute.snapshot.paramMap.get('patientId'));
    this.httpPatient.getPatient(patientId).subscribe(pat => this.patient = pat);

  }

  editPatient(){
      this.patient = this.editPatientForm?.getRawValue();
      console.log(this.patient?.patientId);

      const patientId = Number(this.activeRoute.snapshot.paramMap.get('patientId'));

      this.patient.patientId = patientId;
      console.log(this.patient);

      this.httpPatient.updatePatient(this.patient).subscribe();
  }

}
