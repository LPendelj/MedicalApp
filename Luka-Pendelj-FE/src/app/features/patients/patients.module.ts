import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PatientsRoutingModule } from './patients-routing.module';
import { SharedModuleModule } from 'src/app/shared/shared-module/shared-module.module';
import { PatientsComponent } from './pages/patients/patients.component';


@NgModule({
  declarations: [
    PatientsComponent
  ],
  imports: [
    CommonModule,
    PatientsRoutingModule,
    SharedModuleModule
  ]
})
export class PatientsModule { }
