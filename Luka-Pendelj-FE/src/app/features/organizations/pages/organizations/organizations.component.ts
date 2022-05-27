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

  constructor(private httpOrganizations: HttpOrganizationsService,
              private router: Router) { }

  ngOnInit(): void {
    this.loadOrganizations();
  }

  loadOrganizations(){
    this.httpOrganizations.getAll().subscribe(organizations => this.organizationsList = organizations);

  }

}
