import {
  Component,
  EventEmitter,
  OnChanges,
  Output,
  SimpleChange,
  SimpleChanges,
} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { ITempoID } from 'src/app/shared/models/ITempoID';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';
import { IValidation } from 'src/app/shared/models/IValidation';
import { selectTempos } from 'src/app/shared/store/tempo/tempo.selector';
import * as tempoPageActions from '../../../shared/store/tempo/actions/tempo-page.actions';
import { selectValidations } from 'src/app/shared/store/validations/validation.selector';
import * as validationPageActions from '../../../shared/store/validations/actions/validation-page.actions';
import * as questionPageActions from '../../../shared/store/question/actions/question-page.actions';
import { FormGroup } from '@angular/forms';
import { Question } from 'src/app/shared/models/question.model';

@Component({
  selector: 'app-sallon',
  templateUrl: './sallon.component.html',
  styleUrls: ['./sallon.component.css'],
})
export class SallonComponent implements OnChanges {
  tempos: Observable<ITempoQuiz[]>;
  validations: Observable<IValidation[]>;
  quizID?: number;
  selectedQuestion: ITempoQuiz | null = null;
  previousSelectedQuestion: ITempoQuiz | null = null;
  tempoID?: ITempoID;
  selectedQuestionText?: string | null;
  @Output() selectedQuestionChange = new EventEmitter<ITempoQuiz>();
  question?: Question;
  questionText?: string | null;
  questionForm?: FormGroup;

  constructor(private store: Store, private route: ActivatedRoute) {
    this.route.paramMap.subscribe((params) => {
      const idString = params.get('id');
      this.tempoID = {
        quizID: idString !== null ? +idString : 0,
        questionID: undefined,
      };
    });
    this.store.dispatch(
      tempoPageActions.enter({ tempoID: this.tempoID as ITempoID })
    );
    this.tempos = store.select(selectTempos);
    this.tempos.subscribe((temposList) => {
      if (temposList.length > 0 && this.selectedQuestion === null) {
        this.selectedQuestion = temposList[0];
        this.previousSelectedQuestion = temposList[0];
        this.loadValidations(this.selectedQuestion.question?.id ?? 0);
      }
    });
    this.validations = store.select(selectValidations);
  }

  onQuestionSelected(index: number): void {
    this.tempos.subscribe((temposList) => {
      this.selectedQuestion = temposList[index];
      this.previousSelectedQuestion = temposList[index - 1];
      this.loadValidations(this.selectedQuestion?.question?.id ?? 0);
      this.selectedQuestionChange.emit(this.selectedQuestion);
    });
  }
  onQuestionTextChange(selectedQuestionText: string | null): void {
    // console.log(selectedQuestionText);

    this.questionText = selectedQuestionText;
    console.log(this.questionForm?.getRawValue());

    this.questionForm?.valueChanges.subscribe((latestValues) => {
      console.log('6756');

      this.question = {
        questionText: this.questionText as string,
        ...latestValues,
      };
      console.log(this.question);
    });
  }

  onFormChange(updatedForm: FormGroup): void {
    console.log(updatedForm.getRawValue());
    this.questionForm = updatedForm;
    updatedForm.valueChanges.subscribe((newValues) => {
      this.question = {
        questionText: this.questionText as string,
        ...newValues,
      };
      console.log(this.question);
    });
    // this.question = {
    //   questionText: this.questionText as string,
    //   questionType: updatedForm.value.questionType,
    //   subject_id: updatedForm.value.subject_id,
    //   level_id: updatedForm.value.level_id,
    //   totalScore: updatedForm.value.totalScore,
    // };
    // console.log(this.question);
  }

  updateQuestion(): void {
    this.store.dispatch(
      questionPageActions.updateQuestion({
        question: this.question as Question,
        questionID: 0,
      })
    );
  }
  private loadValidations(questionID: number): void {
    this.store.dispatch(
      validationPageActions.getResponsesByQuestion({ questionID })
    );
  }

  ngOnInit(): void {
    console.log('previous ' + this.previousSelectedQuestion);
  }
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['selectedQuestion']) {
      console.log(this.question);
    }
  }
}
