import { createAction, props } from '@ngrx/store';
import { Quiz } from '../../models/quiz.model';

export const loadQuizzes = createAction(
  '[Quizzes] Load Quizzes',
  props<{ quizzes: Quiz[] }>()
);

export const addQuiz = createAction(
  '[Quizzes] Add Quiz',
  props<{ quiz: Quiz }>()
);
