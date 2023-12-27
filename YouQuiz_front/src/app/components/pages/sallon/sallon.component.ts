import {
  Component,
  EventEmitter,
  OnChanges,
  Output,
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
  tempoID?: ITempoID;
  selectedQuestionText?: string | null;
  @Output() selectedQuestionChange = new EventEmitter<ITempoQuiz>();
  question?: Question;
  questionText?: string | null;
  questionForm?: FormGroup;
  tempo?: ITempoQuiz;

  constructor(private store: Store, private route: ActivatedRoute) {
    this.route.paramMap.subscribe((params) => {
      this.quizID = parseInt(params.get('id') as string);
      this.tempoID = {
        quizID: this.quizID !== null ? +this.quizID : 0,
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
        this.loadValidations(this.selectedQuestion.question?.id ?? 0);
      }
    });
    this.validations = store.select(selectValidations);
  }

  onQuestionSelected(index: number): void {
    this.tempos.subscribe((temposList) => {
      this.selectedQuestion = temposList[index];
      this.loadValidations(this.selectedQuestion?.question?.id ?? 0);
      this.selectedQuestionChange.emit(this.selectedQuestion);
    });
  }
  onQuestionTextChange(selectedQuestionText: string | null): void {
    this.questionText = selectedQuestionText;
    this.questionForm?.valueChanges.subscribe((latestValues) => {
      this.question = {
        questionText: this.questionText as string,
        ...latestValues,
      };
    });
  }

  onFormChange(updatedForm: FormGroup): void {
    // console.log(updatedForm.getRawValue());
    this.questionForm = updatedForm;
    updatedForm.valueChanges.subscribe((newValues) => {
      console.log(newValues);

      this.tempo = {
        question: {
          questionText: this.questionText as string,
          questionType: newValues.questionType,
          totalScore: newValues.totalScore,
          subject_id: newValues.subject_id,
          level_id: newValues.level_id,
        },
        time: newValues.time,
        quiz: null,
      };
    });
  }

  updateQuestion(): void {
    console.log(this.tempo);

    // this.store.dispatch(
    //   tempoPageActions.updateTempo({
    //     questionID: this.selectedQuestion?.question?.id as number,
    //     quizID: this.quizID as number,
    //     tempo: this.tempo as ITempoQuiz,
    //   })
    // );
  }

  saveQuestion(): void {
    console.log('Save');
  }

  handleSubmit() {
    if (this.selectedQuestion) {
      this.updateQuestion();
    } else {
      this.saveQuestion();
    }
  }

  private loadValidations(questionID: number): void {
    this.store.dispatch(
      validationPageActions.getResponsesByQuestion({ questionID })
    );
  }

  ngOnInit(): void {}
  ngOnChanges(changes: SimpleChanges): void {
    if (changes['selectedQuestion']) {
      console.log(this.question);
    }
  }
}
