import { createAction, props } from '@ngrx/store';
import { IValidation } from 'src/app/shared/models/IValidation';

export const selectValidation = createAction(
  '[Validation page] select validation',
  props<{ validationID: number }>()
);

export const unselectValidation = createAction(
  '[Validation page] unselect page'
);

export const getResponsesByQuestion = createAction(
  '[Validation page] get responses by page',
  props<{ questionID: number }>()
);

export const addValidation = createAction(
  '[Validation page] add validation',
  props<{ validation: IValidation }>()
);

export const updateValidation = createAction(
  '[Validation page] update validation',
  props<{ validation: IValidation; validationID: number }>()
);

export const deleteValidation = createAction(
  '[Validation page] delete validation',
  props<{ validationID: number | undefined }>()
);
