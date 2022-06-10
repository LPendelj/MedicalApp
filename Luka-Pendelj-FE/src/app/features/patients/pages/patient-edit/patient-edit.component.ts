import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Gender, Medic, Organization, Patient } from 'src/app/core/model/models';
import { HttpGendersService } from 'src/app/core/services/http-genders.service';
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

  organizations!: Organization[];

  medics!: Medic[];

  gender: Gender[] = [];

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
              private httpGenders: HttpGendersService,
              private router: Router,
              private activeRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.loadGenders();

    this.loadOrganizations();



  }

  loadPatient(){
    const patientId = Number(this.activeRoute.snapshot.paramMap.get('patientId'));
    this.httpPatient.getPatient(patientId).subscribe(pat => {
      this.patient = pat;

      this.loadInitMedic();
     // this.medics = this.medics?.filter((medic)=>medic.organization?.organizationId===this.patient?.organization?.organizationId);


    });

  }



  createPatientForm(){
    this.editPatientForm = new FormGroup({
      firstname: new FormControl(this.patient?.firstname, [Validators.required, Validators.minLength(2)]),
      lastname: new FormControl(this.patient?.lastname, [Validators.required, Validators.minLength(2)]),
      patientCode: new FormControl(this.patient.patientCode, [Validators.minLength(6), Validators.maxLength(12)]),
      gender: new FormControl(this.gender.find(code=>this.patient.gender!.genderCode == code.genderCode)),
      birthDate: new FormControl(this.patient?.birthDate, Validators.required),
      address: new FormControl(this.patient?.address),
      email: new FormControl(this.patient?.email, Validators.email),
      phone: new FormControl(this.patient?.phone),
      deceased: new FormControl(this.patient?.deceased),
      maritalStatus: new FormControl(this.patient?.maritalStatus),
      organization: new FormControl(this.organizations.find(org=>org.organizationId==this.patient?.organization!.organizationId)),
      mainMedic!: new FormControl( (this.patient?.mainMedic!) ? this.medics?.find(med=>med.medicId==this.patient?.mainMedic!.medicId) : '' )
    });
  }

  loadOrganizations(){
    this.httpOrganization.getAll().subscribe(organizations => {
      this.organizations = organizations;
      this.loadPatient();
    })
  }

  loadInitMedic(){
    this.httpMedic.getAll().subscribe(medics =>{
      this.medics = medics;

     this.medics = this.medics.filter(med => med.organization?.organizationId == this.patient?.mainMedic?.organization?.organizationId
         && med.qualification=='Doctor of Medicine' );
         this.createPatientForm();
        })

    }


    loadGenders(){
      this.httpGenders.getAll().subscribe(genders => this.gender = genders);
    }


  getValue(){
    // let val = event.

     //console.log(this.editPatientForm.get('organization')?.value.organizationId);

     const orgId= this.editPatientForm.get('organization')?.value.organizationId;

     this.loadMedics(orgId);

     this.medics = this.medics?.filter(medic=>medic.qualification==='Doctor of Medicine');
   }

   loadMedics(orgId: number){

     this.httpMedic.getMedicsByOrganization(orgId).subscribe(medics=>this.medics=medics);
    // this.medics = this.medics?.filter((medic)=>medic.organization?.organizationId===this.patient?.organization?.organizationId);
    this.medics = this.medics?.filter(medic=>medic.qualification==='Doctor of Medicine');



   }



  editPatient(){
      this.patient = this.editPatientForm?.getRawValue();
      console.log(this.patient?.patientId);

      const patientId = Number(this.activeRoute.snapshot.paramMap.get('patientId'));

      this.patient.patientId = patientId;
      console.log(this.patient);

      this.httpPatient.updatePatient(this.patient).subscribe();
  }

  isInTheFuture(date: Date) {
    const today = new Date();
   let pipe = new DatePipe('en_US');
    let changedFormat = pipe.transform(today, 'YYYY-MM-dd');


    return this.editPatientForm.get('birthDate')?.value > changedFormat!;
  }

}
