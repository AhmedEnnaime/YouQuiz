import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { QuestionService } from 'src/app/services/question.service';
import * as questionPageActions from './actions/question-page.actions';
import * as questionApiActions from './actions/question.api.actions';
import { concatMap, map } from 'rxjs';

@Injectable()
export class QuestionEffect {
  constructor(
    private actions$: Actions,
    private questionService: QuestionService
  ) {}

  createQuestion$ = createEffect(() =>
    this.actions$.pipe(
      ofType(questionPageActions.addQuestion),
      concatMap((action) =>
        this.questionService
          .addQuestion(action.question)
          .pipe(
            map((addedQuestion) =>
              questionApiActions.questionAddedSuccessfully({ addedQuestion })
            )
          )
      )
    )
  );

  updateQuestion$ = createEffect(() =>
    this.actions$.pipe(
      ofType(questionPageActions.updateQuestion),
      concatMap((action) =>
        this.questionService
          .updateQuestion(action.question, action.questionID)
          .pipe(
            map((updatedQuestion) =>
              questionApiActions.questionUpdatedSuccessfully({
                updatedQuestion,
              })
            )
          )
      )
    )
  );
}
