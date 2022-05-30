import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
//import { ExaminationsComponent } from './features/examinations/pages/examinations/examinations.component';
import { HomepageComponent } from './features/home/pages/homepage/homepage.component';
//import { MedicsComponent } from './features/medics/pages/medics/medics.component';
import { PageNotFoundComponent } from './features/page-not-found/page-not-found.component';
//import { PatientsComponent } from './features/patients/pages/patients/patients.component';

const routes: Routes = [{
  path: 'home', component: HomepageComponent,
},
{
  path: '', redirectTo: 'home', pathMatch: 'full'
},
{
  path: 'organizations', loadChildren: () => import('./features/organizations/organizations.module')
  .then((m) =>m.OrganizationsModule)
},
{
  path: 'practitioners', loadChildren: () => import('./features/medics/medics.module')
  .then((m) =>m.MedicsModule)
},
{
  path: 'patients', loadChildren: () => import('./features/patients/patients.module')
  .then((m) =>m.PatientsModule)
},{
  path: 'examinations', loadChildren: () => import('./features/examinations/examinations.module')
  .then((m) =>m.ExaminationsModule)
},
{
  path: '**', component: PageNotFoundComponent
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
