import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Examination, Medic, Organization, Patient, ServiceType } from 'src/app/core/model/models';
import { HttpExaminationsService } from 'src/app/core/services/http-examinations.service';
import { HttpMedicsService } from 'src/app/core/services/http-medics.service';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';
import { HttpPatientsService } from 'src/app/core/services/http-patients.service';

@Component({
  selector: 'app-examination-edit',
  templateUrl: './examination-edit.component.html',
  styleUrls: ['./examination-edit.component.css']
})
export class ExaminationEditComponent implements OnInit {


  examination!: Examination;

  editExaminationForm?: FormGroup;

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
    private router: Router,
    private activeRoute: ActivatedRoute
  ) { }




  ngOnInit(): void {

    this.loadMedics();
    this.loadOrganizations();
    this.loadPatients();

    this.createFormGroup();


  }

  createFormGroup() {
    this.editExaminationForm = new FormGroup({
      examinationCode: new FormControl(''),
      status: new FormControl('', Validators.required),
      serviceType: new FormControl(''),
      priority: new FormControl(''),
      startDate: new FormControl('', Validators.required),
      endDate: new FormControl('', Validators.required),
      diagnosis: new FormControl(''),
      organization: new FormControl(''),
      patient: new FormControl(''),
      medicList: new FormControl('')
    });
  }


  loadMedics(){
    this.httpMedic.getAll().subscribe(medics=>this.medics=medics);
  }

  loadPatients(){
    this.httpPatient.getAll().subscribe(patients=>this.patients=patients);
  }

  loadOrganizations(){
    this.httpOrganization.getAll().subscribe(org=>this.organizations=org);

  }


  editExamination(){
      console.log("update Ex called.");
      this.examination = this.editExaminationForm?.getRawValue();
      this.examination.examinationId = Number(this.activeRoute.snapshot.paramMap.get('examinationId'));
      console.log(this.examination);
      this.httpExamination.updateExamination(this.examination).subscribe();
  }

}
