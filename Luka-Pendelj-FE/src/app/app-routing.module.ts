import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './features/home/pages/homepage/homepage.component';
import { OrganizationsComponent } from './features/organizations/pages/organizations/organizations.component';

const routes: Routes = [{
  path: 'home', component: HomepageComponent,
},
{
  path: 'organizations', component: OrganizationsComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
