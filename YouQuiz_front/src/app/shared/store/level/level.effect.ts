import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { LevelService } from 'src/app/services/level.service';
import * as levelPageActions from './actions/level-page.actions';
import * as levelApiActions from './actions/level.api.actions';
import { concatMap, exhaustMap, map, mergeMap } from 'rxjs';

@Injectable()
export class LevelEffect {
  constructor(private actions$: Actions, private levelService: LevelService) {}

  loadLevels$ = createEffect(() =>
    this.actions$.pipe(
      ofType(levelPageActions.enter),
      exhaustMap(() =>
        this.levelService
          .getLevels()
          .pipe(
            map((levels) =>
              levelApiActions.levelsLoadedSuccessfully({ levels })
            )
          )
      )
    )
  );

  createLevel$ = createEffect(() =>
    this.actions$.pipe(
      ofType(levelPageActions.addLevel),
      concatMap((action) =>
        this.levelService
          .addLevel(action.level)
          .pipe(
            map((addedLevel) =>
              levelApiActions.levelAddedSuccessfully({ addedLevel })
            )
          )
      )
    )
  );

  updateLevel$ = createEffect(() =>
    this.actions$.pipe(
      ofType(levelPageActions.updateLevel),
      concatMap((action) =>
        this.levelService
          .updateLevel(action.level, action.levelID)
          .pipe(
            map((updatedLevel) =>
              levelApiActions.levelUpdatedSuccessfully({ updatedLevel })
            )
          )
      )
    )
  );

  deleteLevel$ = createEffect(() =>
    this.actions$.pipe(
      ofType(levelPageActions.deleteLevel),
      mergeMap((action) =>
        this.levelService.deleteLevel(action.levelID).pipe(
          map((response) =>
            levelApiActions.levelDeletedSuccessfully({
              message: response.message,
              levelID: response.deletedElementIdentifier,
            })
          )
        )
      )
    )
  );
}
