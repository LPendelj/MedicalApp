import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MedicsRoutingModule } from './medics-routing.module';
import { MedicsComponent } from './pages/medics/medics.component';
import { SharedModuleModule } from 'src/app/shared/shared-module/shared-module.module';


@NgModule({
  declarations: [
    MedicsComponent
  ],
  imports: [
    CommonModule,
    MedicsRoutingModule,
    SharedModuleModule
  ]
})
export class MedicsModule { }
