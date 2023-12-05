import { createReducer, on } from '@ngrx/store';
import { IValidation } from '../../models/IValidation';
import * as ValidationActions from '../actions/validation.action';

export interface ValidationState {
  validations: IValidation[];
}

export const initialState: ValidationState = {
  validations: [],
};

export const ValidationReducer = createReducer(
  initialState,
  on(ValidationActions.loadValidations, (state) => ({
    ...state,
  })),
  on(ValidationActions.loadValidationsSuccess, (state, { validations }) => ({
    ...state,
    validations,
  }))
);
