import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PatientsRoutingModule } from './patients-routing.module';
import { SharedModuleModule } from 'src/app/shared/shared-module/shared-module.module';
import { PatientsComponent } from './pages/patients/patients.component';
import { PatientDetailsComponent } from './pages/patient-details/patient-details.component';
import { PatientAddComponent } from './pages/patient-add/patient-add.component';


@NgModule({
  declarations: [
    PatientsComponent,
    PatientDetailsComponent,
    PatientAddComponent
  ],
  imports: [
    CommonModule,
    PatientsRoutingModule,
    SharedModuleModule
  ]
})
export class PatientsModule { }
