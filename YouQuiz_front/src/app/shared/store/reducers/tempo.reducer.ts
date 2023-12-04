import { createReducer, on } from '@ngrx/store';
import { ITempoQuiz } from '../../models/ITempoQuiz';
import { loadTempos } from '../actions/tempo.action';

export interface TempoState {
  tempos: ITempoQuiz[];
}

export const initialState: TempoState = {
  tempos: [],
};

export const TemposReducer = createReducer(
  initialState,
  on(loadTempos, (state, { tempos }) => ({ ...state, tempos }))
);
