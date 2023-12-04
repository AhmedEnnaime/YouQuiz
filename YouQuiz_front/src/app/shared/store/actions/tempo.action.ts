import { createAction, props } from '@ngrx/store';
import { ITempoQuiz } from '../../models/ITempoQuiz';

export const loadTempos = createAction(
  '[Tempos] Load Tempos',
  props<{ tempos: ITempoQuiz[]; quizID: number }>()
);
