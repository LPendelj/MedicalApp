import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Examination, Medic, Organization, Patient, ServiceType } from 'src/app/core/model/models';
import { HttpExaminationsService } from 'src/app/core/services/http-examinations.service';
import { HttpMedicsService } from 'src/app/core/services/http-medics.service';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';
import { HttpPatientsService } from 'src/app/core/services/http-patients.service';

@Component({
  selector: 'app-examination-add',
  templateUrl: './examination-add.component.html',
  styleUrls: ['./examination-add.component.css']
})
export class ExaminationAddComponent implements OnInit {

  examination!: Examination;

  addExaminationForm?: FormGroup;

  patients?: Patient[];

  organizations?: Organization[];

  medics?: Medic[];

  status?: string[] = ['planned', 'triaged', 'in-progress', 'suspended', 'finished', 'cancelled', 'entered-in-error'];

  serviceTypes?: ServiceType[] = [
    {serviceId: 1, serviceName:'Aged Residential Care'},
    {serviceId: 2, serviceName:'Acupuncture'},
    {serviceId: 3, serviceName:'Bowen Therapy'},
    {serviceId: 4, serviceName:'Blood Donation'},
    {serviceId: 5, serviceName:'Immunization'},
    {serviceId: 6, serviceName:'Physiotherapy'},
    {serviceId: 7, serviceName:'Dental'},
    {serviceId: 8, serviceName:'Emergency Medical'},
    {serviceId: 9, serviceName:'Psychology'},
    {serviceId: 10, serviceName:'Dermatology'},
    {serviceId: 11, serviceName:'Home Care/Housekeeping Assistance'},
    {serviceId: 12, serviceName:'Family Planning'},
    {serviceId: 13, serviceName:'Optometry'},
    {serviceId: 14, serviceName:'Osteopathy'},
    {serviceId: 15, serviceName:'Podiatry'},
    {serviceId: 16, serviceName:'Endodontic'},
    {serviceId: 17, serviceName:'Oral Surgery'},
    {serviceId: 18, serviceName:'Osteopathy'},
    ];



  priorities?: string[] = ['asap', 'callback results', 'emergency', 'routine', 'rush',  'reporting', 'timing critical'];

  constructor(
    private httpPatient: HttpPatientsService,
    private httpMedic: HttpMedicsService,
    private httpOrganization: HttpOrganizationsService,
    private httpExamination: HttpExaminationsService,
    private router: Router
  ) { }




  ngOnInit(): void {

    //this.loadMedics();
    this.loadOrganizations();
    //this.loadPatients();

    this.createFormGroup();


  }

  createFormGroup() {
    this.addExaminationForm = new FormGroup({
      examinationCode: new FormControl('', [Validators.minLength(3), Validators.maxLength(12)]),
      status: new FormControl('', Validators.required),
      serviceType: new FormControl('', Validators.required),
      priority: new FormControl(''),
      startDate: new FormControl('' ),
      endDate: new FormControl(''),
      diagnosis: new FormControl(''),
      organization: new FormControl(''),
      patient: new FormControl(''),
      medicList: new FormControl('')
    });
  }

  getValue(){
    const orgId= this.addExaminationForm?.get('organization')?.value.organizationId;
    this.loadMedics(orgId);
    this.loadPatients(orgId);
  }


  loadMedics(orgId: number){
    this.httpMedic.getMedicsByOrganization(orgId).subscribe(medics=>this.medics=medics);
  }

  loadPatients(orgId: number){
    this.httpPatient.getPatientsByOrganization(orgId).subscribe(patients=>this.patients=patients);
  }

  loadOrganizations(){
    this.httpOrganization.getAll().subscribe(org=>this.organizations=org);

  }

  isValidDate() {
  //   const today = new Date();
  //  let pipe = new DatePipe('en_US');
  //   let changedFormat = pipe.transform(today, 'YYYY-MM-dd');
  console.log(this.addExaminationForm?.get('startDate')?.value );
    console.log(this.addExaminationForm?.get('endDate')?.value );


    return this.addExaminationForm?.get('endDate')?.value > this.addExaminationForm?.get('startDate')?.value;
  }

  createExamination(){
      console.log("create Ex called.");
      this.examination = this.addExaminationForm?.getRawValue();
      console.log(this.examination);
      this.httpExamination.createExamination(this.examination).subscribe();
  }
}


// "examinationCode": "EXA-24324",
// "status": "ended",
// "serviceType": {
//     "serviceId": 2
// },
// "priority": "high",
// "startDate": 1637485932000,
// "endDate": 1637665932000,
// "diagnosis": "Epilepsy",
// "organization": {
//     "organizationId": 1
// },
// "patient": {
//     "patientId": 1
//     }
// },
// "medic": [
//     {
//         "medicId": 1
//     }
// ]

