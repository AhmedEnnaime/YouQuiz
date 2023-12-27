import { createAction, props } from '@ngrx/store';
import { ITempoID } from 'src/app/shared/models/ITempoID';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';

export const enter = createAction(
  '[Tempo page] enter',
  props<{ tempoID: ITempoID }>()
);

export const selectTempo = createAction(
  '[Tempo page] select tempo',
  props<{ tempoID: ITempoID }>()
);

export const unselectTempo = createAction('[Tempo page] unselect tempo');

export const addTempo = createAction(
  '[Tempo page] add tempo',
  props<{ tempo: ITempoQuiz }>()
);

export const updateTempo = createAction(
  '[Tempo page] update tempo',
  props<{ questionID: number; quizID: number; tempo: ITempoQuiz }>()
);

export const deleteTempo = createAction(
  '[Tempo page] delete tempo',
  props<{ tempoID: ITempoID | undefined }>()
);
