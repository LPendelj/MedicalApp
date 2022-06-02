import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
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
export class PatientAddComponent implements OnInit, OnChanges {

  addPatientForm!: FormGroup;

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

  ngOnChanges(changes: SimpleChanges): void {

  }

  // ngOnInputChanges(event: any){
  //   this.loadMedics();
  // }

  ngOnInit(): void {
    this.loadOrganizations();

    this.createPatientForm();
  }

  // ngOnInputChange(): void{

  // }



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


  getValue(){
   // let val = event.

    console.log(this.addPatientForm.get('organization')?.value.organizationId);

    const orgId= this.addPatientForm.get('organization')?.value.organizationId;

    this.loadMedics(orgId);

  }

  loadMedics(orgId: number){

    this.httpMedic.getMedicsByOrganization(orgId).subscribe(medics=>this.medics=medics);
    //this.medics = this.medics?.filter((medic, organization)=>medic.organization===this.organizationCheck?.organizationId)
  }



  createPatient(){
      this.patient = this.addPatientForm?.getRawValue();
      console.log(this.patient);

      this.httpPatient.createPatient(this.patient).subscribe();
  }
}


