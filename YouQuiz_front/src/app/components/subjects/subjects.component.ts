import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Subject } from 'src/app/shared/models/subject.model';
import * as subjectPageActions from '../../shared/store/subject/actions/subject-page.actions';
import { selectSubjects } from 'src/app/shared/store/subject/subject.selector';

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.css'],
})
export class SubjectsComponent implements OnInit {
  subjects: Observable<Subject[]>;

  constructor(private store: Store) {
    this.subjects = store.select(selectSubjects);
  }

  ngOnInit(): void {
    this.store.dispatch(subjectPageActions.enter());
  }
}
