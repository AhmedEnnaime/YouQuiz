import { Component, Input, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Level } from 'src/app/shared/models/level.model';
import { Subject } from 'src/app/shared/models/subject.model';
import { selectLevels } from 'src/app/shared/store/level/level.selector';
import { selectSubjects } from 'src/app/shared/store/subject/subject.selector';
import * as levelPageActions from '../../../../shared/store/level/actions/level-page.actions';
import * as subjectPageActions from '../../../../shared/store/subject/actions/subject-page.actions';
import { QuestionType } from 'src/app/core/enums/QuestionType';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-right-side',
  templateUrl: './right-side.component.html',
  styleUrls: ['./right-side.component.css'],
})
export class RightSideComponent implements OnInit {
  levels: Observable<Level[]>;
  subjects: Observable<Subject[]>;
  @Input() selectedQuestion: ITempoQuiz | null = null;
  questionTypes: string[] = Object.keys(QuestionType).filter((v) =>
    isNaN(Number(v))
  );
  @Input() tempos?: Observable<ITempoQuiz[]>;

  constructor(private store: Store) {
    this.levels = store.select(selectLevels);
    this.subjects = store.select(selectSubjects);
  }

  form = new FormGroup({
    questionType: new FormControl<QuestionType>(QuestionType.SINGLE),
    level_id: new FormControl<number>(0),
    subject_id: new FormControl<number>(0),
    totalScore: new FormControl<number>(0),
  });

  ngOnInit(): void {
    this.store.dispatch(levelPageActions.enter());
    this.store.dispatch(subjectPageActions.enter());
  }
}
