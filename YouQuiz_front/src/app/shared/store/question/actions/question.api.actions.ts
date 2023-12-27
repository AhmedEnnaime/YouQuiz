import { createAction, props } from '@ngrx/store';
import { Question } from 'src/app/shared/models/question.model';

export const questionAddedSuccessfully = createAction(
  '[Question api] question added successfully',
  props<{ addedQuestion: Question }>()
);

export const questionAddedFailure = createAction(
  '[Question ap] question added failure',
  props<{ errors: {} }>()
);
export const questionUpdatedSuccessfully = createAction(
  '[Question api] question updated successfully',
  props<{ updatedQuestion: Question }>()
);

export const questionUpdatedFailure = createAction(
  '[Question api] question updated failure',
  props<{ errors: {} }>()
);
