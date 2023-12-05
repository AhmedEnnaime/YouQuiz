import { createFeatureSelector, createSelector } from '@ngrx/store';
import { ValidationState } from '../reducers/validation.reducer';

export const selectValidationState =
  createFeatureSelector<ValidationState>('validations');

export const selectValidations = createSelector(
  selectValidationState,
  (state) => state.validations
);
