import { createAction, props } from '@ngrx/store';
import { Level } from 'src/app/shared/models/level.model';

export const levelsLoadedSuccessfully = createAction(
  '[Level api] Levels loaded successfully',
  props<{ levels: Level[] }>()
);

export const levelsLoadedFailure = createAction(
  '[Level api] Levels loaded failure',
  props<{ errors: {} }>()
);

export const levelAddedSuccessfully = createAction(
  '[Level api] level added successfully',
  props<{ addedLevel: Level }>()
);

export const levelAddedFailure = createAction(
  '[Level api] level added failure',
  props<{ errors: {} }>()
);

export const levelUpdatedSuccessfully = createAction(
  '[Level api] level updated successfully',
  props<{ updatedLevel: Level }>()
);

export const levelUpdatedFailure = createAction(
  '[Level api] level updated failure',
  props<{ errors: {} }>()
);

export const levelDeletedSuccessfully = createAction(
  '[Level api] level deleted successfully',
  props<{ message: string; levelID: number }>()
);

export const levelDeletedFailure = createAction(
  '[Level api] level deleted failure',
  props<{ errors: {} }>()
);
