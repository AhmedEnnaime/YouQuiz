import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges,
} from '@angular/core';
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

  form = new FormGroup({
    questionType: new FormControl<string>(
      (this.selectedQuestion?.question?.questionType.toString() as string) ?? ''
    ),
    level_id: new FormControl<number>(
      this.selectedQuestion?.question?.level?.id ?? 0
    ),
    subject_id: new FormControl<number>(
      this.selectedQuestion?.question?.subject?.id ?? 0
    ),
    totalScore: new FormControl<number>(
      this.selectedQuestion?.question?.totalScore ?? 0
    ),
  });

  constructor(private store: Store) {
    this.levels = store.select(selectLevels);
    this.subjects = store.select(selectSubjects);
  }

  ngOnInit(): void {
    this.store.dispatch(levelPageActions.enter());
    this.store.dispatch(subjectPageActions.enter());
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['selectedQuestion']) {
      this.updateForm();
    }
  }
  updateForm(): void {
    this.form.patchValue({
      level_id: this.selectedQuestion?.question?.level?.id,
      subject_id: this.selectedQuestion?.question?.subject?.id,
      totalScore: this.selectedQuestion?.question?.totalScore || 0,
    });
  }
}
