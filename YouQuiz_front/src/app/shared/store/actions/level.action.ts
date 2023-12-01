import { createAction, props } from '@ngrx/store';
import { Level } from '../../models/level.model';

export const loadLevels = createAction(
  '[Levels] Load Levels',
  props<{ levels: Level[] }>()
);
