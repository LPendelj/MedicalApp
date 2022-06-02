import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Organization, OrganizationType } from 'src/app/core/model/models';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';

@Component({
  selector: 'app-organization-edit',
  templateUrl: './organization-edit.component.html',
  styleUrls: ['./organization-edit.component.css']
})
export class OrganizationEditComponent implements OnInit {

  editOrganizationForm?: FormGroup;

  organization!: Organization;

  organizationType: OrganizationType[] = [
    {
      id: 1,
      name: 'Hospital',
    },
    {
      id: 2,
      name: 'Insurance Company',
    },
    {
      id: 3,
      name: 'Educational Institute',
    },
    {
      id: 4,
      name: 'Clinical Research',
    },
    {
      id: 5,
      name: 'Other',
    },
  ];


  constructor(
    private httpOrganization: HttpOrganizationsService,
    private router: Router,
    private activeRoute: ActivatedRoute
  ) { }

  ngOnInit(): void {

    this.createFormGroup();
    this.loadOrganization();
  }



  loadOrganization(){
    const organizationId = Number(this.activeRoute.snapshot.paramMap.get('organizationId'));
    this.httpOrganization.getOrganization(organizationId).subscribe(org => this.organization = org);
  }
  createFormGroup() {
    this.editOrganizationForm = new FormGroup({
      name: new FormControl('', Validators.required),
      organizationCode: new FormControl(''),
      organizationType: new FormControl(''),
      address: new FormControl(''),
      email: new FormControl(''),
      phone: new FormControl(''),
    });
  }


  editOrganization(){
      this.organization = this.editOrganizationForm?.getRawValue();

      console.log(this.organization);
      this.organization.organizationId = Number(this.activeRoute.snapshot.paramMap.get('organizationId'));

      this.httpOrganization.updateOrganization(this.organization).subscribe();
  }

}
