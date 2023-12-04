import { createAction, props } from '@ngrx/store';
import { Subject } from '../../models/subject.model';

export const loadSubjects = createAction(
  '[Subjects] Load Subjects',
  props<{ subjects: Subject[] }>()
);

export const addSubject = createAction(
  '[Subjects] Add Subject',
  props<{ subject: Subject }>()
);
