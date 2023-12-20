import { createFeatureSelector, createSelector } from '@ngrx/store';
import { QuizState } from './quiz.reducer';
import { QUIZ_FEATURE_KEY } from './quiz.state.module';

export const selectQuizState =
  createFeatureSelector<QuizState>(QUIZ_FEATURE_KEY);

const getAllQuizzes = (state: QuizState) => state.collection;
const getSelectedQuizID = (state: QuizState) => state.selectedQuizID;
const getErrors = (state: QuizState) => state.errors;
const getLoadingState = (state: QuizState) => state.loading;

const getSelectedQuiz = createSelector(
  getAllQuizzes,
  getSelectedQuizID,
  (quizzes, selectedQuizID) =>
    quizzes.find((quiz) => quiz.id === selectedQuizID) ?? null
);

export const selectQuizzes = createSelector(selectQuizState, getAllQuizzes);

export const selectSelectedQuizID = createSelector(
  selectQuizState,
  getSelectedQuizID
);

export const selectSelectedQuiz = createSelector(
  selectQuizState,
  getSelectedQuiz
);

export const selectLoadingState = createSelector(
  selectQuizState,
  getLoadingState
);

export const selectFoundedQuiz = createSelector(
  selectQuizState,
  (state) => state.collection[0]
);

export const selectErrorState = createSelector(selectQuizState, getErrors);
