import { createAction, props } from '@ngrx/store';
import { Quiz } from 'src/app/shared/models/quiz.model';

export const enter = createAction('[Quiz page] enter');

export const findQuiz = createAction(
  '[Quiz page] find quiz',
  props<{ quizID: number }>()
);

export const selectQuiz = createAction(
  '[Quiz page] select quiz',
  props<{ quizID: number }>()
);

export const unselectQuiz = createAction('[Quiz page] unselect quiz');

export const addQuiz = createAction(
  '[Quiz page] add Quiz',
  props<{ quiz: Quiz }>()
);

export const updateQuiz = createAction(
  '[Quiz page] update quiz',
  props<{ quiz: Quiz; quizID: number }>()
);

export const deleteQuiz = createAction(
  '[Quiz page] delete quiz',
  props<{ quizID: number | undefined }>()
);
