import { Injectable } from '@angular/core';
import { Actions, createEffect, ofType } from '@ngrx/effects';
import { ValidationService } from 'src/app/services/validation.service';
import * as validationPageActions from './actions/validation-page.actions';
import * as validationApiActions from './actions/validation.api.actions';
import { concatMap, exhaustMap, map, mergeMap } from 'rxjs';

@Injectable()
export class ValidationEffect {
  constructor(
    private actions$: Actions,
    private validationService: ValidationService
  ) {}

  getResponsesByQuestion$ = createEffect(() =>
    this.actions$.pipe(
      ofType(validationPageActions.getResponsesByQuestion),
      exhaustMap((action) =>
        this.validationService.getResponsesByQuestion(action.questionID).pipe(
          map((validations) =>
            validationApiActions.getResponsesByQuestionSuccessfully({
              validations,
            })
          )
        )
      )
    )
  );

  createValidation = createEffect(() =>
    this.actions$.pipe(
      ofType(validationPageActions.addValidation),
      concatMap((action) =>
        this.validationService.createValidation(action.validation).pipe(
          map((addedValidation) =>
            validationApiActions.validationAddedSuccessfully({
              addedValidation,
            })
          )
        )
      )
    )
  );

  updateValidation$ = createEffect(() =>
    this.actions$.pipe(
      ofType(validationPageActions.updateValidation),
      concatMap((action) =>
        this.validationService
          .updateValidation(action.validationID, action.validation)
          .pipe(
            map((updatedValidation) =>
              validationApiActions.validationUpdatedSuccessfully({
                updatedValidation,
              })
            )
          )
      )
    )
  );

  deleteValidation$ = createEffect(() =>
    this.actions$.pipe(
      ofType(validationPageActions.deleteValidation),
      mergeMap((action) =>
        this.validationService.deleteValidation(action.validationID).pipe(
          map((response) =>
            validationApiActions.validationDeletedSuccessfully({
              message: response.message,
              validationID: response.deletedElementIdentifier,
            })
          )
        )
      )
    )
  );
}
