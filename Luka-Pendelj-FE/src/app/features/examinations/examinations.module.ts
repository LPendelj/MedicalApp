import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ExaminationsRoutingModule } from './examinations-routing.module';
import { SharedModuleModule } from 'src/app/shared/shared-module/shared-module.module';
import { ExaminationsComponent } from './pages/examinations/examinations.component';


@NgModule({
  declarations: [
    ExaminationsComponent
  ],
  imports: [
    CommonModule,
    ExaminationsRoutingModule,
    SharedModuleModule
  ]
})
export class ExaminationsModule { }
