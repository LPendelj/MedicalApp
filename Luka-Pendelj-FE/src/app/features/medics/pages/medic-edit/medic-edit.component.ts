import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { Gender, Medic, Organization } from 'src/app/core/model/models';
import { HttpMedicsService } from 'src/app/core/services/http-medics.service';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';

@Component({
  selector: 'app-medic-edit',
  templateUrl: './medic-edit.component.html',
  styleUrls: ['./medic-edit.component.css']
})
export class MedicEditComponent implements OnInit {

  medic!: Medic;

  editMedicForm?: FormGroup;

  organizations?: Organization[];

  //[‘Doctor of Medicine’, ‘Medical Assistant’, ‘Nurse
//Practitioner’, ‘Doctor of Pharmacy’, ‘Certified Nurse
//Midwife’, ‘Emergency Medical Technician’]

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
            private router: Router,
            private activeRoute: ActivatedRoute) { }


  ngOnInit(): void {
      this.loadOrganizations();
      this.loadMedic();


      console.log(this.editMedicForm?.get('qualification'));


  }


  createFormGroup() {
    this.editMedicForm = new FormGroup({
      firstname: new FormControl(this.medic.firstname, [Validators.required, Validators.minLength(2)]),
      lastname: new FormControl(this.medic.lastname, [Validators.required, Validators.minLength(2)]),
      medicCode: new FormControl(this.medic.medicCode, [Validators.minLength(6), Validators.maxLength(12)]),
      gender: new FormControl(this.gender?.find(code=>this.medic.gender!.genderCode == code.genderCode)),
      birthDate: new FormControl(this.medic.birthDate, Validators.required),
      qualification: new FormControl({value: this.medic.qualification, disabled: true}, [Validators.required]),
      address: new FormControl(this.medic.address),
      email: new FormControl(this.medic.email, Validators.email),
      phone: new FormControl(this.medic.phone),
      organization: new FormControl( (this.medic.organization) ? this.organizations?.find(org=>org.organizationId==this.medic?.organization!.organizationId) : '')
    });
    let pipe = new DatePipe('en_US');
    let changedFormat = pipe.transform(this.medic.birthDate, 'YYYY-MM-dd');
    this.editMedicForm.get('birthDate')?.setValue(changedFormat);
  }

  loadOrganizations(){
    this.httpOrganization.getAll().subscribe(organizations => this.organizations = organizations);
  }

  loadMedic(){
    const medicId = Number(this.activeRoute.snapshot.paramMap.get('medicId'));
    console.log(this.medic);

    this.httpMedic.getMedic(medicId).subscribe(med=>{
      this.medic = med;
      this.createFormGroup();
    })
  }


  updateMedic(){
    this.medic = this.editMedicForm?.getRawValue();
    this.medic.medicId = Number(this.activeRoute.snapshot.paramMap.get('medicId'));
    console.log(this.medic);

    this.httpMedic.updateMedic(this.medic).subscribe();
  }

  //Checking if date is invalid
  isInTheFuture(date?: Date) {
    const today = new Date();
   let pipe = new DatePipe('en_US');
    let changedFormat = pipe.transform(today, 'YYYY-MM-dd');


    return this.editMedicForm?.get('birthDate')?.value > changedFormat!;
  }

}
