import { createAction, props } from '@ngrx/store';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';

export const enter = createAction('[Tempo page] enter');

export const selectTempo = createAction(
  '[Tempo page] select tempo',
  props<{ questionID: number; quizID: number }>()
);

export const unselectTempo = createAction('[Tempo page] unselect tempo');

export const addTempo = createAction(
  '[Tempo page] add tempo',
  props<{ tempo: ITempoQuiz }>()
);

export const updateTempo = createAction(
  '[Tempo page] update tempo',
  props<{ tempo: ITempoQuiz }>()
);

export const deleteTempo = createAction(
  '[Tempo page] delete tempo',
  props<{ questionID: number | undefined; quizID: number | undefined }>()
);
