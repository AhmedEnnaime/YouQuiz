import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { QuizService } from 'src/app/services/quiz.service';
import * as quizPageActions from './actions/quiz-page.actions';
import * as quizApiActions from './actions/quiz.api.actions';
import { concatMap, exhaustMap, map } from 'rxjs';

@Injectable()
export class QuizEffect {
  constructor(private actions$: Actions, private quizService: QuizService) {}

  loadQuizzes$ = createEffect(() =>
    this.actions$.pipe(
      ofType(quizPageActions.enter),
      exhaustMap(() =>
        this.quizService
          .getQuizzes()
          .pipe(
            map((quizzes) =>
              quizApiActions.quizzesLoadedSuccessfully({ quizzes })
            )
          )
      )
    )
  );

  createQuiz$ = createEffect(() =>
    this.actions$.pipe(
      ofType(quizPageActions.addQuiz),
      concatMap((action) =>
        this.quizService
          .addQuiz(action.quiz)
          .pipe(
            map((addedQuiz) =>
              quizApiActions.quizAddedSuccessfully({ addedQuiz })
            )
          )
      )
    )
  );
}
