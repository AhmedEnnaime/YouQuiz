import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { ITempoID } from 'src/app/shared/models/ITempoID';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';
import { IValidation } from 'src/app/shared/models/IValidation';
import { loadValidations } from 'src/app/shared/store/actions/validation.action';
import { selectValidations } from 'src/app/shared/store/selectors/validation.selector';
import { selectTempos } from 'src/app/shared/store/tempo/tempo.selector';
import * as tempoPageActions from '../../../shared/store/tempo/actions/tempo-page.actions';

@Component({
  selector: 'app-sallon',
  templateUrl: './sallon.component.html',
  styleUrls: ['./sallon.component.css'],
})
export class SallonComponent {
  tempos: Observable<ITempoQuiz[]>;
  validations: IValidation[] = [];
  quizID?: number;
  selectedQuestion: ITempoQuiz | null = null;
  tempoID?: ITempoID;

  constructor(
    private store: Store,
    private validationStore: Store<{
      validations: { validations: IValidation[] };
    }>,
    private route: ActivatedRoute
  ) {
    this.route.paramMap.subscribe((params) => {
      const idString = params.get('id');
      this.tempoID = {
        quizID: idString !== null ? +idString : 0,
        questionID: undefined,
      };
    });
    this.tempos = store.select(selectTempos);
    this.tempos.subscribe((temposList) => {
      if (temposList.length > 0 && this.selectedQuestion === null) {
        this.selectedQuestion = temposList[0];
        this.loadValidations(this.selectedQuestion.question?.id ?? 0);
      }
    });
    this.validationStore.select(selectValidations).subscribe((validations) => {
      this.validations = validations;
    });
  }

  ngOnInit(): void {
    this.store.dispatch(
      tempoPageActions.enter({ tempoID: this.tempoID as ITempoID })
    );
  }

  onQuestionSelected(index: number): void {
    this.tempos.subscribe((temposList) => {
      this.selectedQuestion = temposList[index];
      this.loadValidations(this.selectedQuestion?.question?.id ?? 0);
    });
  }

  private loadValidations(questionID: number): void {
    this.validationStore.dispatch(loadValidations({ questionID }));
  }
}
