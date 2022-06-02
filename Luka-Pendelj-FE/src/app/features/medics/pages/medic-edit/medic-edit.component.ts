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
      this.createFormGroup();
  }


  createFormGroup() {
    this.editMedicForm = new FormGroup({
      firstname: new FormControl('', Validators.required),
      lastname: new FormControl(''),
      medicCode: new FormControl(''),
      gender: new FormControl(''),
      birthDate: new FormControl('', Validators.required),
      qualification: new FormControl(''),
      address: new FormControl(''),
      email: new FormControl('', Validators.email),
      phone: new FormControl(''),
      organization: new FormControl('')
    });
  }

  loadOrganizations(){
    this.httpOrganization.getAll().subscribe(organizations => this.organizations = organizations);
  }

  updateMedic(){
    this.medic = this.editMedicForm?.getRawValue();




    this.medic.medicId = Number(this.activeRoute.snapshot.paramMap.get('medicId'));
    console.log(this.medic);

    this.httpMedic.updateMedic(this.medic).subscribe();
  }


}
