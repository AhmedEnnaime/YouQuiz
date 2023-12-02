import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { QuizService } from 'src/app/services/quiz.service';
import * as QuizActions from '../actions/quiz.action';
import { exhaustMap, map } from 'rxjs';
import { Quiz } from '../../models/quiz.model';

@Injectable()
export class QuizEffects {
  constructor(private actions$: Actions, private quizService: QuizService) {}

  loadQuizzes$ = createEffect(() =>
    this.actions$.pipe(
      ofType(QuizActions.loadQuizzes),
      exhaustMap(() =>
        this.quizService
          .getQuizzes()
          .pipe(map((quizzes: Quiz[]) => QuizActions.loadQuizzes({ quizzes })))
      )
    )
  );

  addQuiz$ = createEffect(
    () =>
      this.actions$.pipe(
        ofType(QuizActions.addQuiz),
        exhaustMap((action) =>
          this.quizService
            .addQuiz(action.quiz)
            .pipe(
              map((addedQuiz: Quiz) => QuizActions.addQuiz({ quiz: addedQuiz }))
            )
        )
      ),
    { dispatch: false }
  );
}
