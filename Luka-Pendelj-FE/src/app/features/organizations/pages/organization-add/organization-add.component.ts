import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Organization, OrganizationType } from 'src/app/core/model/models';
import { HttpOrganizationTypesService } from 'src/app/core/services/http-organization-types.service';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';

@Component({
  selector: 'app-organization-add',
  templateUrl: './organization-add.component.html',
  styleUrls: ['./organization-add.component.css'],
})
export class OrganizationAddComponent implements OnInit {
  addOrganizationForm: FormGroup | undefined;

  organization?: Organization;
  // options: any;
  // selectedIndex: any;

  //organizationTypeOne?: OrganizationType;

  organizationType: OrganizationType[] = [ ];



  constructor(
    private httpOrganization: HttpOrganizationsService,
    private httpOrganizationTypesService: HttpOrganizationTypesService,
    private router: Router
  ) {}




  ngOnInit(): void {
    this.loadOrganizationTypes();
    this.createFormGroup();
  }

  loadOrganizationTypes(){
    this.httpOrganizationTypesService.getAll().subscribe(orgType => this.organizationType = orgType);
  }

  createFormGroup() {
    this.addOrganizationForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(5)]),
      organizationCode: new FormControl('ORG-', [Validators.minLength(6), Validators.maxLength(12)]),
      organizationType: new FormControl('', Validators.required),
      address: new FormControl(''),
      email: new FormControl('', Validators.email),
      phone: new FormControl(''),
    });
  }

  createOrganization() {


    this.organization = this.addOrganizationForm?.getRawValue();




     if(this.organization ){


       this.httpOrganization.createOrganization(this.organization).subscribe(o=>console.log("Uspesno"));
     }

    console.log(this.organization);
  }


}

  //{this.organization =
    //   {
    //     organizationId:0,
    //     name : item.name,
    //     organizationCode: item.code,
    //     active: true,
    //     organizationType : {
    //       orgTypeId: item.selectType,
    //       name: item.selectType
    //     },
    //     address: item.address,
    //     phone: item.phone,
    //     email: item.email,
    //     }
