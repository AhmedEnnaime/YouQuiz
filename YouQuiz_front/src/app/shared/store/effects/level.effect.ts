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
}
