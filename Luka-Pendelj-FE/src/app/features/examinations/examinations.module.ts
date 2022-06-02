import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExaminationsRoutingModule } from './examinations-routing.module';
import { SharedModuleModule } from 'src/app/shared/shared-module/shared-module.module';
import { ExaminationsComponent } from './pages/examinations/examinations.component';
import { ExaminationDetailsComponent } from './pages/examination-details/examination-details.component';
import { ExaminationAddComponent } from './pages/examination-add/examination-add.component';
import { ExaminationEditComponent } from './pages/examination-edit/examination-edit.component';


@NgModule({
  declarations: [
    ExaminationsComponent,
    ExaminationDetailsComponent,
    ExaminationAddComponent,
    ExaminationEditComponent
  ],
  imports: [
    CommonModule,
    ExaminationsRoutingModule,
    SharedModuleModule
  ]
})
export class ExaminationsModule { }
