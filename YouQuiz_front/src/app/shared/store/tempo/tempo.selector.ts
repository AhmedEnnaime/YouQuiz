import { createFeatureSelector, createSelector } from '@ngrx/store';
import { TempoState } from './tempo.reducer';
import { TEMPO_FEATURE_KEY } from './tempo.state.module';

export const selectTempoState =
  createFeatureSelector<TempoState>(TEMPO_FEATURE_KEY);

const getAllTempos = (state: TempoState) => state.collection;
const getSelectedTempoID = (state: TempoState) => state.selectedTempoID;
const getErrors = (state: TempoState) => state.errors;
const getLoadingState = (state: TempoState) => state.loading;

const getSelectedTempo = createSelector(
  getAllTempos,
  getSelectedTempoID,
  (tempos, selectedTempoID) =>
    tempos.find((tempo) => tempo.id === selectedTempoID ?? null)
);

export const selectTempos = createSelector(selectTempoState, getAllTempos);

export const selectSelectedTempoID = createSelector(
  selectTempoState,
  getSelectedTempoID
);

export const selectSelectedTempo = createSelector(
  selectTempoState,
  getSelectedTempo
);

export const selectLoadingState = createSelector(
  selectTempoState,
  getLoadingState
);

export const selectErrorState = createSelector(selectTempoState, getErrors);
