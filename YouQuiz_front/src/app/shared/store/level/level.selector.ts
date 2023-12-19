import { createFeatureSelector, createSelector } from '@ngrx/store';
import { LEVEL_FEATURE_KEY } from './level.state.module';
import { LevelState } from './level.reducer';

export const selectLevelState =
  createFeatureSelector<LevelState>(LEVEL_FEATURE_KEY);

/**
 * Local selectors
 */

const getAllLevels = (state: LevelState) => state.collection;
const getSelectedLevelID = (state: LevelState) => state.selectedLevelID;
const getErrors = (state: LevelState) => state.errors;
const getLoadingState = (state: LevelState) => state.loading;

const getSelectedLevel = createSelector(
  getAllLevels,
  getSelectedLevelID,
  (levels, selectedLevelID) =>
    levels.find((level) => level.id === selectedLevelID) ?? null
);

/**
 * Global selectors
 */
export const selectLevels = createSelector(selectLevelState, getAllLevels);

export const selectSelectedLevelCode = createSelector(
  selectLevelState,
  getSelectedLevelID
);

export const selectSelectedLevel = createSelector(
  selectLevelState,
  getSelectedLevel
);

export const selectLoadingState = createSelector(
  selectLevelState,
  getLoadingState
);

export const selectErrorState = createSelector(selectLevelState, getErrors);
