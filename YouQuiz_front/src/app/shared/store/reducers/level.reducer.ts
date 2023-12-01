import { createReducer, on } from '@ngrx/store';
import { Level } from '../../models/level.model';
import { addLevel, loadLevels, removeLevel } from '../actions/level.action';

export interface LevelState {
  levels: Level[];
}

export const initialState: LevelState = {
  levels: [],
};

export const LevelsReducer = createReducer(
  initialState,
  on(loadLevels, (state, { levels }) => ({ ...state, levels })),
  on(addLevel, (state, { level }) => ({
    ...state,
    levels: [...state.levels, level],
  })),
  on(removeLevel, (state, { id }) => ({
    ...state,
    levels: state.levels.filter((level) => level.id !== id),
  }))
);
