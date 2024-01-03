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
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-right-side',
  templateUrl: './right-side.component.html',
  styleUrls: ['./right-side.component.css'],
})
export class RightSideComponent implements OnInit, OnChanges {
  levels?: Observable<Level[]>;
  subjects?: Observable<Subject[]>;
  @Input() selectedQuestion?: ITempoQuiz | null;
  questionTypes: string[] = Object.keys(QuestionType).filter((v) =>
    isNaN(Number(v))
  );
  @Input() tempos?: Observable<ITempoQuiz[]>;
  @Output() formChange = new EventEmitter<FormGroup>();
  form!: FormGroup;

  constructor(private store: Store, private fb: FormBuilder) {
    this.store.dispatch(levelPageActions.enter());
    this.store.dispatch(subjectPageActions.enter());
  }

  setQuestionForm(): void {
    this.form = this.fb.group({
      questionID: [
        this.selectedQuestion?.id?.questionID == undefined
          ? null
          : this.selectedQuestion.id.questionID,
      ],
      questionType: [
        this.selectedQuestion?.question?.questionType
          ? (this.selectedQuestion?.question?.questionType.toString() as string)
          : null,
      ],
      level_id: [this.selectedQuestion?.question?.level?.id],
      subject_id: [this.selectedQuestion?.question?.subject?.id],
      totalScore: [this.selectedQuestion?.question?.totalScore],
      time: [this.selectedQuestion?.time],
    });
  }

  updateForm(): void {
    this.setQuestionForm();
    this.formChange.emit(this.form);
  }

  ngOnInit(): void {
    this.levels = this.store.select(selectLevels);
    this.subjects = this.store.select(selectSubjects);
    this.updateForm();
  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['selectedQuestion']) {
      this.updateForm();
    }
  }
}
