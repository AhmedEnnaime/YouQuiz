import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { StudentService } from 'src/app/services/student.service';
import { SubjectService } from 'src/app/services/subject.service';
import { TrainerService } from 'src/app/services/trainer.service';
import { Student } from 'src/app/shared/models/student.model';
import { Subject } from 'src/app/shared/models/subject.model';
import { Trainer } from 'src/app/shared/models/trainer.model';
import { selectSubjects } from 'src/app/shared/store/subject/subject.selector';
import * as subjectPageActions from '../../shared/store/subject/actions/subject-page.actions';

@Component({
  selector: 'app-summary',
  templateUrl: './summary.component.html',
  styleUrls: ['./summary.component.css'],
})
export class SummaryComponent implements OnInit {
  students: Student[] = [];
  trainers: Trainer[] = [];
  subjects: Observable<Subject[]>;

  constructor(
    private store: Store,
    private studentService: StudentService,
    private trainerService: TrainerService,
    private subjectService: SubjectService
  ) {
    this.subjects = store.select(selectSubjects);
  }

  ngOnInit(): void {
    this.studentService.getStudents().subscribe((students) => {
      this.students = students;
    });
    this.trainerService.getTrainers().subscribe((trainers) => {
      this.trainers = trainers;
    });
    this.store.dispatch(subjectPageActions.enter());
  }
}
