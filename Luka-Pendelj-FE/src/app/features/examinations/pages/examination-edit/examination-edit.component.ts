import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Examination, Medic, Organization, Patient, ServiceType } from 'src/app/core/model/models';
import { HttpExaminationsService } from 'src/app/core/services/http-examinations.service';
import { HttpMedicsService } from 'src/app/core/services/http-medics.service';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';
import { HttpPatientsService } from 'src/app/core/services/http-patients.service';
import { HttpServiceTypeService } from 'src/app/core/services/http-service-type.service';

@Component({
  selector: 'app-examination-edit',
  templateUrl: './examination-edit.component.html',
  styleUrls: ['./examination-edit.component.css']
})
export class ExaminationEditComponent implements OnInit {


  examination!: Examination;

  editExaminationForm?: FormGroup;

  patients?: Patient[];

  organizations!: Organization[];

  medics?: Medic[];

  status?: string[] = ['planned', 'triaged', 'in-progress', 'suspended', 'finished', 'cancelled', 'entered-in-error'];

  serviceTypes: ServiceType[] = [];



  priorities?: string[] = ['asap', 'callback results', 'emergency', 'routine', 'rush',  'reporting', 'timing critical'];

  constructor(
    private httpPatient: HttpPatientsService,
    private httpMedic: HttpMedicsService,
    private httpOrganization: HttpOrganizationsService,
    private httpExamination: HttpExaminationsService,
    private httpServiceTypeService: HttpServiceTypeService,
    private router: Router,
    private activeRoute: ActivatedRoute
  ) { }




  ngOnInit(): void {
    const examId=Number(this.activeRoute.snapshot.paramMap.get('examinationId'));




    this.loadOrganizations();

    this.loadHttpTypeService();

    this.loadExamination(examId);


  }




  loadExamination(examId: number){


    this.httpExamination.getExamination(examId).subscribe(exam=>{
     // this.loadOrganizations();
      this.examination = exam;

      this.createFormGroup();
      this.getInitialValues();
      }
    );

  }

  createFormGroup() {
    this.editExaminationForm = new FormGroup({
      examinationCode: new FormControl(this.examination?.examinationCode,[Validators.minLength(7), Validators.maxLength(12)]),
      status: new FormControl(this.examination?.status, Validators.required),
      serviceType: new FormControl(this.serviceTypes[this.examination?.serviceType.serviceId -1], ),
      priority: new FormControl(this.examination?.priority),
      startDate: new FormControl(this.examination?.startDate, Validators.required),
      endDate: new FormControl(this.examination?.endDate, Validators.required),
      diagnosis: new FormControl(this.examination?.diagnosis),
      organization!: new FormControl( (this.examination!.organization) ? this.organizations.find(org=>org.organizationId==this.examination?.organization!.organizationId) : ''),
      patient: new FormControl(this.examination?.patient?.firstname + ' ' + this.examination?.patient?.lastname),
      medicList: new FormControl(this.examination?.medicList)
    });
  }

  loadOrganizations(){
    this.httpOrganization.getAll().subscribe(org=>{
        this.organizations=org;

      });


  }

  loadHttpTypeService(){
    return this.httpServiceTypeService.getAll().subscribe(serviceTypes => this.serviceTypes = serviceTypes);
  }


  getInitialValues(){
    this.loadPatients();
    this.loadMedics();

  }

  getValue(){
    const orgId= this.editExaminationForm?.get('organization')?.value.organizationId;

    this.changeMedics(orgId);
    this.changePatients(orgId);

    console.log(orgId);

  }



  loadMedics(){
    this.httpMedic.getMedicsByOrganization(this.examination!.organization!.organizationId).subscribe(medics=>this.medics=medics);
  }

  changeMedics(orgId: number){
    this.httpMedic.getMedicsByOrganization(orgId).subscribe(medics=>this.medics=medics);
  }

  loadPatients(){
    this.httpPatient.getPatientsByOrganization(this.examination!.organization!.organizationId).subscribe(patients=>this.patients=patients);
  }

  changePatients(orgId: number){
    this.httpPatient.getPatientsByOrganization(orgId).subscribe(patients=>this.patients=patients);
  }

  //Checking if end date is after the start date
  isValidDate() {
      return this.editExaminationForm?.get('endDate')?.value > this.editExaminationForm?.get('startDate')?.value;
    }



  editExamination(){
      console.log("update Ex called.");
      this.examination = this.editExaminationForm?.getRawValue();
      this.examination.examinationId = Number(this.activeRoute.snapshot.paramMap.get('examinationId'));
     // console.log(this.examination);
      this.httpExamination.updateExamination(this.examination).subscribe();
  }

}
