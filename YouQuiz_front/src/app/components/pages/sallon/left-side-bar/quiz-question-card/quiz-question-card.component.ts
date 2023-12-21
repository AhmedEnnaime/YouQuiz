import { Component, Input } from '@angular/core';
import { Store } from '@ngrx/store';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';
import * as tempoPageActions from '../../../../../shared/store/tempo/actions/tempo-page.actions';
import { ITempoID } from 'src/app/shared/models/ITempoID';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-quiz-question-card',
  templateUrl: './quiz-question-card.component.html',
  styleUrls: ['./quiz-question-card.component.css'],
})
export class QuizQuestionCardComponent {
  @Input() questionNum?: number;
  @Input() isSelected: boolean = false;
  @Input() tempo?: ITempoQuiz;
  tempoID?: ITempoID;

  constructor(private store: Store, private route: ActivatedRoute) {}

  detachQuestionFromQuiz(): void {
    this.route.paramMap.subscribe((params) => {
      const idString = params.get('id');
      this.tempoID = {
        quizID: idString !== null ? +idString : 0,
        questionID: this.tempo?.question?.id,
      };
    });

    this.store.dispatch(
      tempoPageActions.deleteTempo({ tempoID: this.tempoID })
    );
  }
}
