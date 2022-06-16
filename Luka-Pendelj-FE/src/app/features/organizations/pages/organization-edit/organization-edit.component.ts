import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Organization, OrganizationType } from 'src/app/core/model/models';
import { HttpOrganizationTypesService } from 'src/app/core/services/http-organization-types.service';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';

@Component({
  selector: 'app-organization-edit',
  templateUrl: './organization-edit.component.html',
  styleUrls: ['./organization-edit.component.css']
})
export class OrganizationEditComponent implements OnInit {

  editOrganizationForm?: FormGroup;

  organization!: Organization;

  organizationType: OrganizationType[] = [];


  constructor(
    private httpOrganization: HttpOrganizationsService,
    private httpOrganizationsTypesService: HttpOrganizationTypesService,
    private router: Router,
    private activeRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {

    this.loadOrganizationTypes();

    this.loadOrganization();



  }


  loadOrganizationTypes(){
    this.httpOrganizationsTypesService.getAll().subscribe(orgType => this.organizationType = orgType);
  }


  loadOrganization(){
    const organizationId = Number(this.activeRoute.snapshot.paramMap.get('organizationId'));
    this.httpOrganization.getOrganization(organizationId).subscribe(org =>{
       this.organization = org;
       this.createFormGroup();
    });
  }
  createFormGroup() {
    this.editOrganizationForm = new FormGroup({
      name: new FormControl(this.organization.name, [Validators.required, Validators.minLength(5)]),
      organizationCode: new FormControl(this.organization.organizationCode, [Validators.minLength(6), Validators.maxLength(12)]),
      organizationType: new FormControl(this.organizationType.find(type=>this.organization.organizationType.name==type.name), Validators.required),
      address: new FormControl(this.organization.address),
      email: new FormControl(this.organization.email, Validators.email),
      phone: new FormControl(this.organization.phone),
    });
  }


  editOrganization(){
      this.organization = this.editOrganizationForm?.getRawValue();

      console.log(this.organization);
      this.organization.organizationId = Number(this.activeRoute.snapshot.paramMap.get('organizationId'));

      this.httpOrganization.updateOrganization(this.organization).subscribe();
  }

}
