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

}
