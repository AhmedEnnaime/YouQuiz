import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { ValidationService } from 'src/app/services/validation.service';
import * as ValidationActions from '../actions/validation.action';
import { exhaustMap, map } from 'rxjs';
import { IValidation } from '../../models/IValidation';

@Injectable()
export class ValidationEffects {
  constructor(
    private actions$: Actions,
    private validationService: ValidationService
  ) {}

  loadValidations$ = createEffect(() =>
    this.actions$.pipe(
      ofType(ValidationActions.loadValidations),
      exhaustMap((action) => {
        return this.validationService
          .getResponsesByQuestion(action.questionID)
          .pipe(
            map((validations: IValidation[]) =>
              ValidationActions.loadValidationsSuccess({
                validations,
              })
            )
          );
      })
    )
  );
}
