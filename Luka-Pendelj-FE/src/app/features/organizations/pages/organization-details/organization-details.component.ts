import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Organization } from 'src/app/core/model/models';
import { HttpMedicsService } from 'src/app/core/services/http-medics.service';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';

@Component({
  selector: 'app-organization-details',
  templateUrl: './organization-details.component.html',
  styleUrls: ['./organization-details.component.css']
})
export class OrganizationDetailsComponent implements OnInit {


  organization!: Organization;

  constructor(private activeRoute: ActivatedRoute,
             private httpOrganizations: HttpOrganizationsService,
             private httpMedics: HttpMedicsService,
             private router: Router) { }

  ngOnInit(): void {
    this.loadOrganization();


     this.countMedics();

    console.log(this.organizationId);
  }

  organizationId!: number;

  loadOrganization(){
    this.organizationId = Number(this.activeRoute.snapshot.paramMap.get('organizationId'));

    //console.log(this.organizationId);


    this.httpOrganizations.getOrganization(this.organizationId).subscribe(org => this.organization = org);
  }

  editOrganization(organization: Organization)
  {
    this.router.navigate(['organizations/organization-edit', organization.organizationId]);
  }

  deleteOrganization(organizationId: number){


    var answer = window.confirm(`Are you sure that you want to delete entity with Id ${organizationId}?`);
    if(answer){
    this.httpOrganizations.deleteOrganization(organizationId).subscribe();
    //location.reload();
    this.router.navigate(['organizations/organizations']);
    }

  }

  numberOfMedics?: number;

  countMedics(){

    this.httpMedics.getMedicsByOrganization(this.organizationId).subscribe(medics => this.numberOfMedics = medics.length);
  //console.log(this.organization);
  setTimeout(() =>
  {
    console.log('Number of medics: ' + this.numberOfMedics);
  },
  3000);

  }
}
