import { createFeatureSelector, createSelector } from '@ngrx/store';
import { VALIDATION_FEATURE_KEY } from './validation.state.module';
import { ValidationState } from './validation.reducer';

export const selectValidationState = createFeatureSelector<ValidationState>(
  VALIDATION_FEATURE_KEY
);

const getResponsesByQuestions = (state: ValidationState) => state.collection;
const getSelectedValidationID = (state: ValidationState) =>
  state.selectedValidationID;
const getSelectedQuestionID = (state: ValidationState) =>
  state.selectedQuestionID;
const getErrors = (state: ValidationState) => state.errors;
const getLoadingState = (state: ValidationState) => state.loading;

const getSelectedValidation = createSelector(
  getResponsesByQuestions,
  getSelectedQuestionID,
  (validations, selectedQuestionID) =>
    validations.find(
      (validation) => validation.question_id === selectedQuestionID ?? null
    )
);

export const selectValidations = createSelector(
  selectValidationState,
  getResponsesByQuestions
);

export const selectSelectedValidationID = createSelector(
  selectValidationState,
  getSelectedValidationID
);

export const selectSelectedQuestionID = createSelector(
  selectValidationState,
  getSelectedQuestionID
);

export const selectSelectedValidation = createSelector(
  selectValidationState,
  getSelectedValidation
);

export const selectLoadingState = createSelector(
  selectValidationState,
  getLoadingState
);

export const selectErrorState = createSelector(
  selectValidationState,
  getErrors
);
