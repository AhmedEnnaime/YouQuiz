import { createAction, props } from '@ngrx/store';
import { Question } from 'src/app/shared/models/question.model';

export const addQuestion = createAction(
  '[Question page] add question',
  props<{ question: Question }>()
);

export const updateQuestion = createAction(
  '[Question page] update question',
  props<{ question: Question; questionID: number }>()
);
