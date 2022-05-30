import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ExaminationsComponent } from './features/examinations/pages/examinations/examinations.component';
import { HomepageComponent } from './features/home/pages/homepage/homepage.component';
import { MedicsComponent } from './features/medics/pages/medics/medics.component';
import { OrganizationsComponent } from './features/organizations/pages/organizations/organizations.component';
import { PageNotFoundComponent } from './features/page-not-found/page-not-found.component';
import { PatientsComponent } from './features/patients/pages/patients/patients.component';

const routes: Routes = [{
  path: 'home', component: HomepageComponent,
},
{
  path: 'organizations', component: OrganizationsComponent
},
{
  path: 'practitioners', component: MedicsComponent
},
{
  path: 'patients', component: PatientsComponent
},

{
  path: 'examinations', component: ExaminationsComponent
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
