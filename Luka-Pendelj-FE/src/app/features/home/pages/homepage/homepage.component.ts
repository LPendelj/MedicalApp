import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }


  organizationsPage(){
    console.log("org link clicked");

    this.router.navigate(['organizations'])
  }

  medicsPage(){
    console.log("medic link clicked");

    this.router.navigate(['practitioners'])
  }

  patientsPage(){
    console.log("patients link clicked");

    this.router.navigate(['patients'])
  }

  examinationsPage(){
    console.log("exams link clicked");

    this.router.navigate(['examinations'])
  }


}
