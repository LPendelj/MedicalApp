import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Organization } from 'src/app/core/model/models';
import { HttpOrganizationsService } from 'src/app/core/services/http-organizations.service';

@Component({
  selector: 'app-organizations',
  templateUrl: './organizations.component.html',
  styleUrls: ['./organizations.component.css']
})
export class OrganizationsComponent implements OnInit {

  filterOrganizations?: FormGroup;

  organizationsList?: Organization[] = undefined;


  pageNo = 1;
  pageSize = 5;

  totalItems = 20;



  constructor(private httpOrganization: HttpOrganizationsService,
              private router: Router) { }

  ngOnInit(): void {
    console.log("org initialized");

    this.createFormGroup();

    this.loadOrganizations();
  }

  createFormGroup(){
    this.filterOrganizations=new FormGroup({
      filterSelect: new FormControl(''),
      filterText: new FormControl('')
    });
  }



  onPageChange(pageNo: number){
    this.loadOrganizations();
  }

  loadOrganizations(){
    //this.httpOrganization.getAll().subscribe(organizations => this.organizationsList = organizations);
    this.httpOrganization.getSome((this.pageNo-1), this.pageSize).subscribe(
      organizationPage=> {
      this.organizationsList = organizationPage.content;
      this.totalItems = organizationPage.totalElements;
      this.pageSize = organizationPage.size;
      }
  )
  }


  filterOrganizationsByFilter(filter: any){

  }

  organizationDetails(org: Organization){
    this.router.navigate(['organizations/organization-details', org.organizationId])
  }

  // organizationAdd(){
  //   this.router.navigate(['organizations/organization-add']);
  // }




  deleteOrganization(organizationId: number){


    var answer = window.confirm(`Are you sure that you want to delete entity with Id ${organizationId}?`);
    if(answer){
    this.httpOrganization.deleteOrganization(organizationId).subscribe();
    location.reload();
    }

  }

  editOrganization(organization: Organization)
  {
    this.router.navigate(['organizations/organization-edit', organization.organizationId]);
  }

}
