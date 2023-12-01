import { createReducer, on } from '@ngrx/store';
import { Level } from '../../models/level.model';
import { loadLevels } from '../actions/level.action';

export interface LevelState {
  levels: Level[];
}

export const initialState: LevelState = {
  levels: [],
};

export const LevelsReducer = createReducer(
  initialState,
  on(loadLevels, (state, { levels }) => ({ ...state, levels }))
);
