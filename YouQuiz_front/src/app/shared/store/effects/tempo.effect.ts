import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { QuestionService } from 'src/app/services/question.service';
import * as TempoActions from '../actions/tempo.action';
import { exhaustMap, map } from 'rxjs';
import { ITempoQuiz } from '../../models/ITempoQuiz';

@Injectable()
export class TempoEffects {
  constructor(
    private actions$: Actions,
    private questionService: QuestionService
  ) {}

  loadTempos$ = createEffect(() =>
    this.actions$.pipe(
      ofType(TempoActions.loadTempos),
      exhaustMap((action) =>
        this.questionService
          .getQuestionsByQuiz(action.quizID)
          .pipe(
            map((tempos: ITempoQuiz[]) =>
              TempoActions.loadTempos({ tempos, quizID: action.quizID })
            )
          )
      )
    )
  );
}
