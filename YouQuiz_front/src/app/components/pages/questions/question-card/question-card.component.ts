import { Component, Input, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';
import { IValidation } from 'src/app/shared/models/IValidation';
import { loadValidations } from 'src/app/shared/store/actions/validation.action';
import { selectValidations } from 'src/app/shared/store/selectors/validation.selector';

@Component({
  selector: 'app-question-card',
  templateUrl: './question-card.component.html',
  styleUrls: ['./question-card.component.css'],
})
export class QuestionCardComponent implements OnInit {
  @Input() props?: any;
  tempo?: ITempoQuiz;
  validations?: IValidation[];

  isOpen: boolean = false;

  constructor(
    private store: Store<{ validations: { validations: IValidation[] } }>
  ) {}

  loadQuestionsValidation() {
    this.store.select(selectValidations).subscribe((validations) => {
      this.validations = validations;
    });
    this.store.dispatch(
      loadValidations({
        questionID: this.tempo?.question.id ?? 0,
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

  ngOnInit(): void {
    this.tempo = {
      ...this.props,
    };
  }
}
