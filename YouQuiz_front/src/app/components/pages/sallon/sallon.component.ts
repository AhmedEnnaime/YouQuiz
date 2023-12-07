import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';
import { IValidation } from 'src/app/shared/models/IValidation';
import { loadTempos } from 'src/app/shared/store/actions/tempo.action';
import { loadValidations } from 'src/app/shared/store/actions/validation.action';
import { selectValidations } from 'src/app/shared/store/selectors/validation.selector';

@Component({
  selector: 'app-sallon',
  templateUrl: './sallon.component.html',
  styleUrls: ['./sallon.component.css'],
})
export class SallonComponent {
  tempos: ITempoQuiz[] = [];
  validations: IValidation[] = [];
  quizID?: number;
  selectedQuestion: ITempoQuiz | null = null;

  constructor(
    private store: Store<{ tempos: { tempos: ITempoQuiz[] } }>,
    private validationStore: Store<{
      validations: { validations: IValidation[] };
    }>,
    private route: ActivatedRoute
  ) {
    store
      .select('tempos')
      .subscribe((temposState: { tempos: ITempoQuiz[] }) => {
        this.tempos = temposState.tempos;
        if (this.tempos.length > 0 && this.selectedQuestion === null) {
          this.selectedQuestion = this.tempos[0];
          this.loadValidations(this.selectedQuestion.question.id ?? 0);
        }
      });
    this.validationStore.select(selectValidations).subscribe((validations) => {
      this.validations = validations;
    });
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const idString = params.get('id');
      this.quizID = idString !== null ? +idString : 0;
    });
    this.store.dispatch(
      loadTempos({ tempos: this.tempos, quizID: this.quizID ?? 0 })
    );
  }

  onQuestionSelected(index: number): void {
    this.selectedQuestion = this.tempos[index];
    this.loadValidations(this.selectedQuestion.question.id ?? 0);
  }

  private loadValidations(questionID: number): void {
    this.validationStore.dispatch(loadValidations({ questionID }));
  }
}
