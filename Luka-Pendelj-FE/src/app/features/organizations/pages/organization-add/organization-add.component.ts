import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Organization, OrganizationType } from 'src/app/core/model/models';
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
    private router: Router
  ) {}

  ngOnInit(): void {
    this.createFormGroup();
  }

  createFormGroup() {
    this.addOrganizationForm = new FormGroup({
      name: new FormControl('', Validators.required),
      organizationCode: new FormControl('', [Validators.minLength(3), Validators.maxLength(12)]),
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
