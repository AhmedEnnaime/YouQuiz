import { createAction, props } from '@ngrx/store';
import { Subject } from 'src/app/shared/models/subject.model';

export const subjectsLoadedSuccessfully = createAction(
  '[Subject api] Subjects loaded successfully',
  props<{ subjects: Subject[] }>()
);

export const subjectsLoadedFailure = createAction(
  '[Subject api] Subjects loaded failure',
  props<{ errors: {} }>()
);

export const subjectAddedSuccessfully = createAction(
  '[Subject api] Subject added successfully',
  props<{ addedSubject: Subject }>()
);

export const subjectAddedFailure = createAction(
  '[Subject api] Subject added failure',
  props<{ errors: {} }>()
);

export const subjectUpdatedSuccessfully = createAction(
  '[Subject api] Subject updated successfully',
  props<{ updatedSubject: Subject }>()
);

export const subjectUpdatedFailure = createAction(
  '[Subject api] subject updated failure',
  props<{ errors: {} }>()
);

export const subjectDeletedSuccessfully = createAction(
  '[Subject api] subject deleted successfully',
  props<{ message: string; subjectID: number }>()
);

export const subjectDeletedFailure = createAction(
  '[Subject api] subject deleted failure',
  props<{ errors: {} }>()
);
