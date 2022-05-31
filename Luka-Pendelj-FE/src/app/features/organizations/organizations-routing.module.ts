import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrganizationAddComponent } from './pages/organization-add/organization-add.component';
import { OrganizationDetailsComponent } from './pages/organization-details/organization-details.component';
import { OrganizationsComponent } from './pages/organizations/organizations.component';

const routes: Routes = [
  {
    path: 'organizations', component: OrganizationsComponent
  },
  {
    path: 'organization-details/:organizationId', component: OrganizationDetailsComponent
  },
  {
    path: 'organization-add', component: OrganizationAddComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OrganizationsRoutingModule { }
