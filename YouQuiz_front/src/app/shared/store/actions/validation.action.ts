import { createAction, props } from '@ngrx/store';
import { IValidation } from '../../models/IValidation';

export const loadValidations = createAction(
  '[Validations]',
  props<{ questionID: number }>()
);

export const loadValidationsSuccess = createAction(
  '[LoadSuccessValidation]',
  props<{ validations: IValidation[] }>()
);
