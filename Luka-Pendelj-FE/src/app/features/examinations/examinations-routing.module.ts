import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExaminationDetailsComponent } from './pages/examination-details/examination-details.component';
import { ExaminationsComponent } from './pages/examinations/examinations.component';

const routes: Routes = [
  {
    path: 'examinations', component: ExaminationsComponent
  },
  {
    path: 'examination-details/:examinationId', component: ExaminationDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExaminationsRoutingModule { }
