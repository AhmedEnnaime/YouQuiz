import { createFeatureSelector, createSelector } from '@ngrx/store';
import { QuestionState } from './question.reducer';
import { QUESTION_FEATURE_KEY } from './question.state.module';

export const selectQuestionState =
  createFeatureSelector<QuestionState>(QUESTION_FEATURE_KEY);

const getAllQuestions = (state: QuestionState) => state.collection;
const getSelectedQuestionID = (state: QuestionState) =>
  state.selectedQuestionID;
const getErrors = (state: QuestionState) => state.errors;
const getLoadingState = (state: QuestionState) => state.loading;

const getSelectedQuestion = createSelector(
  getAllQuestions,
  getSelectedQuestionID,
  (questions, selectedQuestionID) =>
    questions.find((question) => question.id === selectedQuestionID) ?? null
);

export const selectQuestions = createSelector(
  selectQuestionState,
  getAllQuestions
);

export const selectSelectedQuestionID = createSelector(
  selectQuestionState,
  getSelectedQuestionID
);

export const selectSelectedQuestion = createSelector(
  selectQuestionState,
  getSelectedQuestion
);

export const selectLoadingState = createSelector(
  selectQuestionState,
  getLoadingState
);

export const selectFoundedQuiz = createSelector(
  selectQuestionState,
  (state) => state.collection[0]
);

export const selectErrorState = createSelector(selectQuestionState, getErrors);
