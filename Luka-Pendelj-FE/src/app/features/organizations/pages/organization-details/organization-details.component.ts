import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Organization } from 'src/app/core/model/models';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';

@Component({
  selector: 'app-organization-details',
  templateUrl: './organization-details.component.html',
  styleUrls: ['./organization-details.component.css']
})
export class OrganizationDetailsComponent implements OnInit {


  organization?: Organization;

  constructor(private activeRoute: ActivatedRoute,
             private httpOrganizations: HttpOrganizationsService,
             private router: Router) { }

  ngOnInit(): void {
    this.loadOrganization();
  }



  loadOrganization(){
    const organizationId = Number(this.activeRoute.snapshot.paramMap.get('organizationId'));
    this.httpOrganizations.getOrganization(organizationId).subscribe(org => this.organization = org);
  }

}
