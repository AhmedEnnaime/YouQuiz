import { createAction, props } from '@ngrx/store';
import { Quiz } from 'src/app/shared/models/quiz.model';

export const quizzesLoadedSuccessfully = createAction(
  '[Quiz api] quizzes loaded successfully',
  props<{ quizzes: Quiz[] }>()
);

export const quizzesLoadedFailure = createAction(
  '[Quiz api] quizzes loaded failure',
  props<{ errors: {} }>()
);

export const quizAddedSuccessfully = createAction(
  '[Quiz api] quiz added successfully',
  props<{ addedQuiz: Quiz }>()
);

export const quizAddedFailure = createAction(
  '[Quiz api] quiz added failure',
  props<{ errors: {} }>()
);

export const quizUpdatedSuccessfully = createAction(
  '[Quiz api] quiz updated successfully',
  props<{ updatedQuiz: Quiz }>()
);

export const quizUpdatedFailure = createAction(
  '[Quiz api] quiz updated failure',
  props<{ errors: {} }>()
);

export const quizDeletedSuccessfully = createAction(
  '[Quiz api] quiz deleted successfully',
  props<{ message: string; quizID: number }>()
);

export const quizDeletedFailure = createAction(
  '[Quiz api] quiz deleted failure',
  props<{ errors: {} }>()
);
