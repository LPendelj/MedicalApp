import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Gender, Medic, Organization, Patient } from 'src/app/core/model/models';
import { HttpMedicsService } from 'src/app/core/services/http-medics.service';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';
import { HttpPatientsService } from 'src/app/core/services/http-patients.service';
import { DatePipe } from '@angular/common';


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

    //this.isInTheFuture(new Date('2126/12/12'));
  }

  // ngOnInputChange(): void{

  // }



  createPatientForm(){
    this.addPatientForm = new FormGroup({
      firstname: new FormControl('', [Validators.required, Validators.minLength(2)]),
      lastname: new FormControl('', [Validators.required, Validators.minLength(2)]),
      patientCode: new FormControl('', [Validators.minLength(3), Validators.maxLength(12)]),
      gender: new FormControl(''),
      birthDate: new FormControl('', [Validators.required]),
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

  // validateDate(){
  //   const inFuture = (date: Date) => {
  //     return date.setHours(0,0,0,0) > new Date().setHours(0,0,0,0)
  //     };


  // }

  isInTheFuture(date: Date) {
    const today = new Date();
   let pipe = new DatePipe('en_US');
    let changedFormat = pipe.transform(today, 'YYYY-MM-dd');


    return this.birthDate?.value > changedFormat!;
  }


  get firstName(){
    return this.addPatientForm.get('firstname');
  }
  get lastName(){
    return this.addPatientForm.get('lastname');
  }
  get email(){
    return this.addPatientForm.get('email');
  }
  get birthDate(){
    return this.addPatientForm.get('birthDate');
  }
  get patientCode(){
    return this.addPatientForm.get('patientCode');
  }


  getValue(){
   // let val = event.

    console.log(this.addPatientForm.get('organization')?.value.organizationId);

    const orgId= this.addPatientForm.get('organization')?.value.organizationId;

    this.loadMedics(orgId);

  }

  loadMedics(orgId: number){

    this.httpMedic.getMedicsByOrganization(orgId).subscribe(medics=>this.medics=medics);
    this.medics = this.medics?.filter(medic=>medic.qualification==='Doctor of Medicine');
    //this.medics = this.medics?.filter((medic, organization)=>medic.organization===this.organizationCheck?.organizationId)
  }



  createPatient(){
      this.patient = this.addPatientForm?.getRawValue();
      console.log(this.patient);

      this.httpPatient.createPatient(this.patient).subscribe();
  }


    resetForm() {
    this.addPatientForm.reset();
  }


 
}


