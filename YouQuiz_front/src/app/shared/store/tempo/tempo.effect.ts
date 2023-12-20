import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { TempoService } from 'src/app/services/tempo.service';
import * as tempoPageActions from './actions/tempo-page.actions';
import * as tempoApiActions from './actions/tempo.api.actions';
import { exhaustMap, map, mergeMap } from 'rxjs';

@Injectable()
export class TempoEffect {
  constructor(private actions$: Actions, private tempoService: TempoService) {}

  loadTempos$ = createEffect(() =>
    this.actions$.pipe(
      ofType(tempoPageActions.enter),
      exhaustMap((action) =>
        this.tempoService
          .getQuestionsByQuiz(action.tempoID)
          .pipe(
            map((tempos) =>
              tempoApiActions.temposLoadedSuccessfully({ tempos })
            )
          )
      )
    )
  );

  detachQuestionFromQuiz$ = createEffect(() =>
    this.actions$.pipe(
      ofType(tempoPageActions.deleteTempo),
      mergeMap((action) =>
        this.tempoService.detachQuestionFromQuiz(action.tempoID).pipe(
          map((response) =>
            tempoApiActions.tempoDeletedSuccessfully({
              message: response.message,
              tempoID: null,
            })
          )
        )
      )
    )
  );
}
