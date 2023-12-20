import { Component, Input, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';
import { IValidation } from 'src/app/shared/models/IValidation';
import { selectValidations } from 'src/app/shared/store/validations/validation.selector';
import * as validationPageActions from '../../../../shared/store/validations/actions/validation-page.actions';

@Component({
  selector: 'app-question-card',
  templateUrl: './question-card.component.html',
  styleUrls: ['./question-card.component.css'],
})
export class QuestionCardComponent {
  @Input() tempo?: ITempoQuiz;
  validations?: Observable<IValidation[]>;

  isOpen: boolean = false;

  constructor(private store: Store) {}

  loadQuestionsValidation() {
    this.validations = this.store.select(selectValidations);
    this.store.dispatch(
      validationPageActions.getResponsesByQuestion({
        questionID: this.tempo?.question?.id as number,
      })
    );
  }

  toggleDropdown() {
    if (!this.isOpen) {
      this.loadQuestionsValidation();
    }
    this.isOpen = !this.isOpen;
  }

  closeDropdown() {
    this.isOpen = false;
  }
}
