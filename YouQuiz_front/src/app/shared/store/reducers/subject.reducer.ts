import { createReducer, on } from '@ngrx/store';
import { Subject } from '../../models/subject.model';
import { loadSubjects } from '../actions/subject.action';

export interface SubjectState {
  subjects: Subject[];
}

export const initialState: SubjectState = {
  subjects: [],
};

export const SubjectsReducer = createReducer(
  initialState,
  on(loadSubjects, (state, { subjects }) => ({ ...state, subjects }))
);
