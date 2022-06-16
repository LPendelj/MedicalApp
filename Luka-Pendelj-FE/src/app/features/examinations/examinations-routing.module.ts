import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExaminationAddComponent } from './pages/examination-add/examination-add.component';
import { ExaminationDetailsComponent } from './pages/examination-details/examination-details.component';
import { ExaminationEditComponent } from './pages/examination-edit/examination-edit.component';
import { ExaminationsComponent } from './pages/examinations/examinations.component';

const routes: Routes = [
  {
    path: 'examinations', component: ExaminationsComponent
  },
  {
    path: 'examination-details/:examinationId', component: ExaminationDetailsComponent
  },
  {
    path: 'examination-add', component: ExaminationAddComponent
  },
  {
    path: 'examination-edit/:examinationId', component: ExaminationEditComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExaminationsRoutingModule { }
