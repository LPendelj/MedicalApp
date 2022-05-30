import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Examination } from 'src/app/core/model/models';
import { HttpExaminationsService } from 'src/app/core/services/http-examinations.service';

@Component({
  selector: 'app-examinations',
  templateUrl: './examinations.component.html',
  styleUrls: ['./examinations.component.css']
})
export class ExaminationsComponent implements OnInit {

  examinationsList?: Examination[];

  constructor(private httpExamination: HttpExaminationsService,
              private router: Router) { }

  ngOnInit(): void {
    this.loadExaminations();
  }

  loadExaminations(){
    this.httpExamination.getAll().subscribe(examinations => this.examinationsList = examinations);
  }


}
