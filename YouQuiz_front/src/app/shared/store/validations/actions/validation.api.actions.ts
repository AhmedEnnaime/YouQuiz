import { createAction, props } from '@ngrx/store';
import { IValidation } from 'src/app/shared/models/IValidation';

export const getResponsesByQuestionSuccessfully = createAction(
  '[Validation api] get responses by question successfully',
  props<{ validations: IValidation[] }>()
);

export const getResponsesByQuestionFailure = createAction(
  '[Validation api] get responses by question failure',
  props<{ errors: {} }>()
);

export const validationAddedSuccessfully = createAction(
  '[Validation api] validation added successfully',
  props<{ addedValidation: IValidation }>()
);

export const validationAddedFailure = createAction(
  '[Validation api] validation added failure',
  props<{ errors: {} }>()
);

export const validationUpdatedSuccessfully = createAction(
  '[Validation api] validation updated successfully',
  props<{ updatedValidation: IValidation }>()
);

export const validationUpdatedFailure = createAction(
  '[Validation api] validation updated failure',
  props<{ errors: {} }>()
);

export const validationDeletedSuccessfully = createAction(
  '[Validation api] validation deleted successfully',
  props<{ message: string; validationID: number }>()
);

export const validationDeletedFailure = createAction(
  '[Validation api] validation deleted failure',
  props<{ errors: {} }>()
);
