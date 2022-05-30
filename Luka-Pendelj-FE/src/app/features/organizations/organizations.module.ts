import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrganizationsRoutingModule } from './organizations-routing.module';
import { OrganizationsComponent } from './pages/organizations/organizations.component';
import { OrganizationDetailsComponent } from './pages/organization-details/organization-details.component';
import { SharedModuleModule } from 'src/app/shared/shared-module/shared-module.module';


@NgModule({
  declarations: [
    OrganizationsComponent,
    OrganizationDetailsComponent
  ],
  imports: [
    CommonModule,
    OrganizationsRoutingModule,
    SharedModuleModule
  ]
})
export class OrganizationsModule { }
