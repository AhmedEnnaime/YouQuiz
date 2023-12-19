import { createReducer, on } from '@ngrx/store';
import { Level } from '../../models/level.model';
import * as levelPageActions from './actions/level-page.actions';
import * as levelApiActions from './actions/level.api.actions';

export interface LevelState {
  collection: Level[];
  selectedLevelID: number | null;
  loading: boolean;
  errors: {};
}

export const initialState: LevelState = {
  collection: [],
  selectedLevelID: null,
  loading: false,
  errors: {},
};

export const LevelReducer = createReducer(
  initialState,
  on(levelPageActions.enter, (state, action) => ({
    ...state,
    selectedLevelID: null,
    loading: true,
  })),
  on(levelPageActions.selectLevel, (state, action) => ({
    ...state,
    selectedLevelID: action.levelID,
    loading: true,
  })),
  on(levelPageActions.unselectLevel, (state, action) => ({
    ...state,
    selectedLevelID: null,
  })),
  on(levelApiActions.levelsLoadedSuccessfully, (state, action) => ({
    ...state,
    collection: action.levels,
  })),
  on(levelApiActions.levelAddedSuccessfully, (state, action) => ({
    collection: createLevel(state.collection, action.addedLevel),
    selectedLevelID: null,
    loading: false,
    errors: {},
  })),
  on(levelApiActions.levelUpdatedSuccessfully, (state, action) => ({
    collection: updateLevel(state.collection, action.updatedLevel),
    selectedLevelID: null,
    loading: false,
    errors: {},
  })),
  on(levelApiActions.levelDeletedSuccessfully, (state, action) => ({
    collection: deleteLevel(state.collection, action.levelID),
    selectedLevelID: null,
    loading: false,
    errors: {},
  })),
  on(
    levelApiActions.levelAddedFailure,
    levelApiActions.levelUpdatedFailure,
    levelApiActions.levelDeletedFailure,
    levelApiActions.levelsLoadedFailure,
    (state, action) => ({
      ...state,
      loading: false,
      errors: action.errors,
    })
  )
);

const createLevel = (levels: Level[], newLevel: Level) => [...levels, newLevel];

const updateLevel = (levels: Level[], updatedLevel: Level) =>
  levels.map((level) =>
    level.id === updatedLevel.id
      ? Object.assign({}, level, updatedLevel)
      : level
  );

const deleteLevel = (levels: Level[], levelID: number) =>
  levels.filter((level) => level.id != levelID);
