import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { LevelService } from 'src/app/services/level.service';
import * as LevelActions from '../actions/level.action';
import { exhaustMap, map } from 'rxjs';
import { Level } from '../../models/level.model';

@Injectable()
export class LevelEffects {
  constructor(private actions$: Actions, private levelService: LevelService) {}

  loadLevels$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LevelActions.loadLevels),
      exhaustMap(() =>
        this.levelService
          .getLevels()
          .pipe(map((levels: Level[]) => LevelActions.loadLevels({ levels })))
      )
    )
  );

  addLevel$ = createEffect(
    () =>
      this.actions$.pipe(
        ofType(LevelActions.addLevel),
        exhaustMap((action) =>
          this.levelService
            .addLevel(action.level)
            .pipe(
              map((addedLevel: Level) =>
                LevelActions.addLevel({ level: addedLevel })
              )
            )
        )
      ),
    { dispatch: false }
  );

  removeLevel$ = createEffect(() =>
    this.actions$.pipe(
      ofType(LevelActions.removeLevel),
      exhaustMap((action) =>
        this.levelService
          .deleteLevel(action.id)
          .pipe(map(() => LevelActions.removeLevel({ id: action.id })))
      )
    )
  );
}
