import { createAction, props } from '@ngrx/store';
import { ITempoID } from 'src/app/shared/models/ITempoID';
import { ITempoQuiz } from 'src/app/shared/models/ITempoQuiz';

export const temposLoadedSuccessfully = createAction(
  '[Tempo api] tempos loaded successfully',
  props<{ tempos: ITempoQuiz[] }>()
);

export const temposLoadedFailure = createAction(
  '[Tempo api] tempos loaded failure',
  props<{ errors: {} }>()
);

export const tempoAddedSuccessfully = createAction(
  '[Tempo api] tempo added successfully',
  props<{ addedTempo: ITempoQuiz }>()
);

export const tempoAddedFailure = createAction(
  '[Tempo api] tempo added failure',
  props<{ errors: {} }>()
);

export const tempoUpdatedSuccessfully = createAction(
  '[Tempo api] tempo updated successfully',
  props<{ updatedTempo: ITempoQuiz }>()
);

export const tempoUpdatedFailure = createAction(
  '[Tempo api] tempo updated failure',
  props<{ errors: {} }>()
);

export const tempoDeletedSuccessfully = createAction(
  '[Tempo api] tempo deleted successfully',
  props<{ message: string; tempoID: ITempoID | null }>()
);

export const tempoDeletedFailure = createAction(
  '[Tempo api] tempo deleted failure',
  props<{ errors: {} }>()
);
