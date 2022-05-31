import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OrganizationsRoutingModule } from './organizations-routing.module';
import { OrganizationsComponent } from './pages/organizations/organizations.component';
import { OrganizationDetailsComponent } from './pages/organization-details/organization-details.component';
import { SharedModuleModule } from 'src/app/shared/shared-module/shared-module.module';
import { OrganizationAddComponent } from './pages/organization-add/organization-add.component';


@NgModule({
  declarations: [
    OrganizationsComponent,
    OrganizationDetailsComponent,
    OrganizationAddComponent
  ],
  imports: [
    CommonModule,
    OrganizationsRoutingModule,
    SharedModuleModule
  ]
})
export class OrganizationsModule { }
