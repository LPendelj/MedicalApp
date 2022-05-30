import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Medic } from 'src/app/core/model/models';
import { HttpMedicsService } from 'src/app/core/services/http-medics.service';

@Component({
  selector: 'app-medic-details',
  templateUrl: './medic-details.component.html',
  styleUrls: ['./medic-details.component.css']
})
export class MedicDetailsComponent implements OnInit {

  medic?: Medic;

  constructor(private activeRoute: ActivatedRoute,
             private httpMedics: HttpMedicsService,
              private router: Router) { }

  ngOnInit(): void {
    this.loadMedic();
  }

  loadMedic(){
      const medicId = Number(this.activeRoute.snapshot.paramMap.get('medicId'));
      this.httpMedics.getMedic(medicId).subscribe(med => this.medic=med);
  }

}
