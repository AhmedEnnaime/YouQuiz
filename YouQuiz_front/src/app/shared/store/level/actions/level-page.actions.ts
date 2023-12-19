import { createAction, props } from '@ngrx/store';
import { Level } from 'src/app/shared/models/level.model';

export const enter = createAction('[Level page] enter');

export const selectLevel = createAction(
  '[Level page] select level',
  props<{ levelID: number }>()
);

export const unselectLevel = createAction('[Level page] unselect level');

export const addLevel = createAction(
  '[Level page] add Level',
  props<{ level: Level }>()
);

export const updateLevel = createAction(
  '[Level page] update Level',
  props<{ level: Level; levelID: number }>()
);

export const deleteLevel = createAction(
  '[Level page] delete level',
  props<{ levelID: number | undefined }>()
);
