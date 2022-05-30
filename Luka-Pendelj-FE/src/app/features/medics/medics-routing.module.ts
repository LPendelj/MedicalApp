import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MedicsComponent } from './pages/medics/medics.component';

//RAZMISLI DA ISKLJUCIS DEO RUTE

const routes: Routes = [
  {
    path: 'practitioners', component: MedicsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MedicsRoutingModule { }
