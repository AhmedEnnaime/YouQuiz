import { createAction, props } from '@ngrx/store';
import { Subject } from 'src/app/shared/models/subject.model';

export const enter = createAction('[Subject page] enter');

export const selectSubject = createAction(
  '[Subject page] select subject',
  props<{ subjectID: number }>()
);

export const unselectSubject = createAction('[Subject page] unselect page');

export const addSubject = createAction(
  '[Subject page] add subject',
  props<{ subject: Subject }>()
);

export const updateSubject = createAction(
  '[Subject page] update subject',
  props<{ subject: Subject; subjectID: number }>()
);

export const deleteSubject = createAction(
  '[Subject page] delete subject',
  props<{ subjectID: number | undefined }>()
);
