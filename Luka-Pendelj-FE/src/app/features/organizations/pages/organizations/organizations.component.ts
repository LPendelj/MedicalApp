import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Organization } from 'src/app/core/model/models';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';

@Component({
  selector: 'app-organizations',
  templateUrl: './organizations.component.html',
  styleUrls: ['./organizations.component.css']
})
export class OrganizationsComponent implements OnInit {

  organizationsList?: Organization[] = undefined;

  constructor(private httpOrganization: HttpOrganizationsService,
              private router: Router) { }

  ngOnInit(): void {
    console.log("org initialized");

    this.loadOrganizations();
  }

  loadOrganizations(){
    this.httpOrganization.getAll().subscribe(organizations => this.organizationsList = organizations);

  }

  organizationDetails(org: Organization){
    this.router.navigate(['organizations/organization-details', org.organizationId])
  }

  // organizationAdd(){
  //   this.router.navigate(['organizations/organization-add']);
  // }


}
