import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExaminationsComponent } from './pages/examinations/examinations.component';

const routes: Routes = [
  {
    path : 'examinations', component: ExaminationsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ExaminationsRoutingModule { }
