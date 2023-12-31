import { createReducer, on } from '@ngrx/store';
import { IValidation } from '../../models/IValidation';
import * as validationPageActions from './actions/validation-page.actions';
import * as validationApiActions from './actions/validation.api.actions';

export interface ValidationState {
  collection: IValidation[];
  selectedValidationID: number | null;
  selectedQuestionID: number | null;
  loading: boolean;
  errors: {};
}

export const initialState: ValidationState = {
  collection: [],
  selectedValidationID: null,
  selectedQuestionID: null,
  loading: false,
  errors: {},
};

export const ValidationReducer = createReducer(
  initialState,
  on(validationApiActions.validationAddedSuccessfully, (state, action) => ({
    collection: createValidation(state.collection, action.addedValidation),
    selectedValidationID: null,
    selectedQuestionID: null,
    loading: false,
    errors: {},
  })),
  on(validationApiActions.validationUpdatedSuccessfully, (state, action) => ({
    collection: updateValidation(state.collection, action.updatedValidation),
    selectedValidationID: null,
    loading: false,
    selectedQuestionID: null,
    errors: {},
  })),
  on(validationApiActions.validationDeletedSuccessfully, (state, action) => ({
    collection: deleteValidation(state.collection, action.validationID),
    selectedValidationID: null,
    selectedQuestionID: null,
    loading: false,
    errors: {},
  })),
  on(
    validationApiActions.getResponsesByQuestionSuccessfully,
    (state, action) => ({
      ...state,
      collection: action.validations,
      loading: false,
    })
  ),
  on(validationPageActions.getResponsesByQuestion, (state, action) => ({
    ...state,
    selectedQuestionID: action.questionID,
    loading: true,
  })),
  on(
    validationApiActions.getResponsesByQuestionFailure,
    validationApiActions.validationAddedFailure,
    validationApiActions.validationUpdatedFailure,
    validationApiActions.validationDeletedFailure,
    (state, action) => ({
      ...state,
      selectedValidationID: null,
      loading: false,
      errors: action.errors,
    })
  )
);

const createValidation = (
  validations: IValidation[],
  newValidation: IValidation
) => [...validations, newValidation];
const updateValidation = (
  validations: IValidation[],
  updatedValidation: IValidation
) =>
  validations.map((validation) =>
    validation.id == updatedValidation.id
      ? Object.assign({}, validation, updatedValidation)
      : validation
  );
const deleteValidation = (validations: IValidation[], validationID: number) =>
  validations.filter((validation) => validation.id != validationID);
