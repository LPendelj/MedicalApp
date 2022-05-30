import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientDetailsComponent } from './pages/patient-details/patient-details.component';
import { PatientsComponent } from './pages/patients/patients.component';

const routes: Routes = [
  {
    path: 'patients', component: PatientsComponent
  },
  {
    path: 'patient-details/:patientId', component: PatientDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientsRoutingModule { }
