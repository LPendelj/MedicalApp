import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MedicDetailsComponent } from './pages/medic-details/medic-details.component';
import { MedicsComponent } from './pages/medics/medics.component';

//RAZMISLI DA ISKLJUCIS DEO RUTE!!!

const routes: Routes = [
  {
    path: 'practitioners', component: MedicsComponent
  },
  {
    path: 'practitioner-details/:medicId', component: MedicDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MedicsRoutingModule { }
