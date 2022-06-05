import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Examination } from 'src/app/core/model/models';
import { HttpExaminationsService } from 'src/app/core/services/http-examinations.service';

@Component({
  selector: 'app-examination-details',
  templateUrl: './examination-details.component.html',
  styleUrls: ['./examination-details.component.css']
})
export class ExaminationDetailsComponent implements OnInit {

  exam!: Examination;

  visible = false;

  constructor(
    private activeRoute: ActivatedRoute,
    private httpExam: HttpExaminationsService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.loadExamination();
  }

  loadExamination(){
    const examId=Number(this.activeRoute.snapshot.paramMap.get('examinationId'));
    this.httpExam.getExamination(examId).subscribe(exam=>this.exam = exam);

    //this.
  }

  editExamination(examination: Examination){
    this.router.navigate(['examinations/examination-edit', examination.examinationId]);
  }

  deleteExamination(examinationId: number){
    var answer = window.confirm(`Are you sure that you want to delete entity with Id ${examinationId}?`);
    if(answer){
    this.httpExam.deleteExamination(examinationId).subscribe();
    this.router.navigate(['examinations/examinations']);
    }
  }

}
