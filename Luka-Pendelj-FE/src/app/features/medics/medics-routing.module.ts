import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MedicAddComponent } from './pages/medic-add/medic-add.component';
import { MedicDetailsComponent } from './pages/medic-details/medic-details.component';
import { MedicsComponent } from './pages/medics/medics.component';

//RAZMISLI DA ISKLJUCIS DEO RUTE!!!

const routes: Routes = [
  {
    path: 'practitioners', component: MedicsComponent
  },
  {
    path: 'practitioner-details/:medicId', component: MedicDetailsComponent
  },
  {
    path: 'practitioner-add', component: MedicAddComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MedicsRoutingModule { }
