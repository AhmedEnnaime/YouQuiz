import { createFeatureSelector } from '@ngrx/store';
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
