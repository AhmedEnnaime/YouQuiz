import { createReducer, on } from '@ngrx/store';
import { Subject } from '../../models/subject.model';
import * as subjectPageActions from './actions/subject-page.actions';
import * as subjectApiActions from './actions/subject.api.actions';

export interface SubjectState {
  collection: Subject[];
  selectedSubjectID: number | null;
  loading: boolean;
  errors: {};
}

export const initialState: SubjectState = {
  collection: [],
  selectedSubjectID: null,
  loading: false,
  errors: {},
};
export const SubjectReducer = createReducer(
  initialState,
  on(subjectPageActions.enter, (state, action) => ({
    ...state,
    selectedSubjectID: null,
    loading: true,
  })),
  on(subjectPageActions.selectSubject, (state, action) => ({
    ...state,
    selectedSubjectID: action.subjectID,
    loading: true,
  })),
  on(subjectPageActions.unselectSubject, (state, action) => ({
    ...state,
    selectedSubjectID: null,
  })),
  on(subjectApiActions.subjectsLoadedSuccessfully, (state, action) => ({
    ...state,
    collection: action.subjects,
  })),
  on(subjectApiActions.subjectAddedSuccessfully, (state, action) => ({
    collection: createSubject(state.collection, action.addedSubject),
    selectedSubjectID: null,
    loading: false,
    errors: {},
  })),
  on(subjectApiActions.subjectUpdatedSuccessfully, (state, action) => ({
    collection: updateSubject(state.collection, action.updatedSubject),
    selectedSubjectID: null,
    loading: false,
    errors: {},
  })),
  on(subjectApiActions.subjectDeletedSuccessfully, (state, action) => ({
    collection: deleteSubject(state.collection, action.subjectID),
    selectedSubjectID: null,
    loading: false,
    errors: {},
  })),
  on(
    subjectApiActions.subjectsLoadedFailure,
    subjectApiActions.subjectAddedFailure,
    subjectApiActions.subjectUpdatedFailure,
    subjectApiActions.subjectDeletedFailure,
    (state, action) => ({
      ...state,
      selectedSubjectID: null,
      loading: false,
      errors: action.errors,
    })
  )
);

const createSubject = (subjects: Subject[], newSubject: Subject) => [
  ...subjects,
  newSubject,
];
const updateSubject = (subjects: Subject[], updatedSubject: Subject) =>
  subjects.map((subject) =>
    subject.id === updatedSubject.id
      ? Object.assign({}, subject, updatedSubject)
      : subject
  );
const deleteSubject = (subjects: Subject[], subjectID: number) =>
  subjects.filter((subject) => subject.id != subjectID);
