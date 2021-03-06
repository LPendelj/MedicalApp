import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PatientAddComponent } from './pages/patient-add/patient-add.component';
import { PatientDetailsComponent } from './pages/patient-details/patient-details.component';
import { PatientEditComponent } from './pages/patient-edit/patient-edit.component';
import { PatientsComponent } from './pages/patients/patients.component';

const routes: Routes = [
  {
    path: 'patients', component: PatientsComponent
  },
  {
    path: 'patient-details/:patientId', component: PatientDetailsComponent
  },
  {
    path: 'patient-add', component: PatientAddComponent
  },
  {
    path: 'patient-edit/:patientId', component: PatientEditComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PatientsRoutingModule { }
