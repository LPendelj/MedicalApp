import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Gender, Medic, Organization } from 'src/app/core/model/models';
import { HttpMedicsService } from 'src/app/core/services/http-medics.service';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';

@Component({
  selector: 'app-medic-add',
  templateUrl: './medic-add.component.html',
  styleUrls: ['./medic-add.component.css']
})
export class MedicAddComponent implements OnInit {

  medic!: Medic;

  addMedicForm?: FormGroup;

  organizations?: Organization[];



  qualification?: string[] = [
    'Doctor of Medicine',
    'Medical Assistant',
    'Nurse Practitioner',
    'Doctor of Pharmacy',
    'Certified Nurse Midwife',
    'Emergency Medical Technician'
  ];

  gender?: Gender[] = [
        {genderCode: 'm', genderName: 'male'},
        {genderCode: 'f', genderName: 'female'},
        {genderCode: 'o', genderName: 'other'},
        {genderCode: 'u', genderName: 'unknown'}
         ];




  constructor(private httpMedic: HttpMedicsService,
            private httpOrganization: HttpOrganizationsService,
            private router: Router) { }



  ngOnInit(): void {
    this.loadOrganizations();
    this.createFormGroup();

  }

  createFormGroup() {
    this.addMedicForm = new FormGroup({
      firstname: new FormControl('', [Validators.required, Validators.minLength(2)]),
      lastname: new FormControl('', [Validators.required, Validators.minLength(2)]),
      medicCode: new FormControl('MED-', [Validators.minLength(6), Validators.maxLength(12)]),
      gender: new FormControl(''),
      birthDate: new FormControl('', Validators.required),
      qualification: new FormControl('', Validators.required),
      address: new FormControl(''),
      email: new FormControl('', Validators.email),
      phone: new FormControl(''),
      organization: new FormControl('')
    });
  }


  createMedic(){
    this.medic = this.addMedicForm?.getRawValue();
    console.log(this.medic);
    this.httpMedic.createMedic(this.medic).subscribe();
  }

  loadOrganizations(){
    this.httpOrganization.getAll().subscribe(organizations => this.organizations = organizations);
  }


  isInTheFuture(date: Date) {
    const today = new Date();
   let pipe = new DatePipe('en_US');
    let changedFormat = pipe.transform(today, 'YYYY-MM-dd');


    return this.addMedicForm?.get('birthDate')?.value > changedFormat!;
  }

  resetForm() {
    this.addMedicForm?.reset();
  }
  // {
  //   "medicId": 6,
  //   "medicCode": "MED-66",
  //   "active": true,
  //   "firstname": "Laki",
  //   "lastname": "Lakic",
  //   "gender": {
  //       "genderCode": "m",
  //       "genderName": "male"
  //   },
  //   "birthDate": "1944-02-12",
  //   "address": "address66",
  //   "phone": "060-66666",
  //   "email": "mail5665",
  //   "qualification": "Spec",
  //   "organization": {
  //       "organizationId": 2
  //   }
}


