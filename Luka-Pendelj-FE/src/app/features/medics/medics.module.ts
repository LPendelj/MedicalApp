import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MedicsRoutingModule } from './medics-routing.module';
import { MedicsComponent } from './pages/medics/medics.component';
import { SharedModuleModule } from 'src/app/shared/shared-module/shared-module.module';
import { MedicDetailsComponent } from './pages/medic-details/medic-details.component';
import { MedicAddComponent } from './pages/medic-add/medic-add.component';
import { MedicEditComponent } from './pages/medic-edit/medic-edit.component';


@NgModule({
  declarations: [
    MedicsComponent,
    MedicDetailsComponent,
    MedicAddComponent,
    MedicEditComponent
  ],
  imports: [
    CommonModule,
    MedicsRoutingModule,
    SharedModuleModule
  ]
})
export class MedicsModule { }
