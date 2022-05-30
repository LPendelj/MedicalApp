import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrganizationDetailsComponent } from './pages/organization-details/organization-details.component';
import { OrganizationsComponent } from './pages/organizations/organizations.component';

const routes: Routes = [
  {
    path: 'organizations', component: OrganizationsComponent
  },
  {
    path: 'organization-details/:organizationId', component: OrganizationDetailsComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrganizationsRoutingModule { }
