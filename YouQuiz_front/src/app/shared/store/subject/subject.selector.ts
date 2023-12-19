import { createFeatureSelector, createSelector } from '@ngrx/store';
import { SubjectState } from './subject.reducer';
import { SUBJECT_FEATURE_KEY } from './subject.state.module';

export const selectSubjectState =
  createFeatureSelector<SubjectState>(SUBJECT_FEATURE_KEY);

const getAllSubjects = (state: SubjectState) => state.collection;
const getSelectedSubjectID = (state: SubjectState) => state.selectedSubjectID;
const getErrors = (state: SubjectState) => state.errors;
const getLoadingState = (state: SubjectState) => state.loading;

const getSelectedSubject = createSelector(
  getAllSubjects,
  getSelectedSubjectID,
  (subjects, selectedSubjectID) =>
    subjects.find((subject) => subject.id === selectedSubjectID) ?? null
);

export const selectSubjects = createSelector(
  selectSubjectState,
  getAllSubjects
);

export const selectSelectedSubjectID = createSelector(
  selectSubjectState,
  getSelectedSubjectID
);

export const selectSelectedSubject = createSelector(
  selectSubjectState,
  getSelectedSubject
);

export const selectLoadingState = createSelector(
  selectSubjectState,
  getLoadingState
);

export const selectErrorState = createSelector(selectSubjectState, getErrors);
