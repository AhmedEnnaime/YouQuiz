import { createAction, props } from '@ngrx/store';
import { Level } from '../../models/level.model';

export const loadLevels = createAction(
  '[Levels] Load Levels',
  props<{ levels: Level[] }>()
);

export const addLevel = createAction(
  '[Levels] Add level',
  props<{ level: Level }>()
);

export const removeLevel = createAction(
  '[Levels] Remove level',
  props<{ id: number }>()
);

export const updateLevel = createAction(
  '[Levels] Update level',
  props<{ level: Level; id: number }>()
);
