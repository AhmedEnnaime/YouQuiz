import { createReducer, on } from '@ngrx/store';
import { ITempoQuiz } from '../../models/ITempoQuiz';
import * as tempoPageActions from './actions/tempo-page.actions';
import * as tempoApiActions from './actions/tempo.api.actions';
import { ITempoID } from '../../models/ITempoID';

export interface TempoState {
  collection: ITempoQuiz[];
  selectedTempoID: ITempoID | null;
  loading: boolean;
  errors: {};
}

export const initialState: TempoState = {
  collection: [],
  selectedTempoID: null,
  loading: false,
  errors: {},
};

export const TempoReducer = createReducer(
  initialState,
  on(tempoPageActions.enter, (state, action) => ({
    ...state,
    selectedTempoID: null,
    loading: true,
  })),
  on(tempoPageActions.selectTempo, (state, action) => ({
    ...state,
    selectedTempoID: action.tempoID,
    loading: true,
  })),
  on(tempoPageActions.unselectTempo, (state, action) => ({
    ...state,
    selectedTempoID: null,
  })),
  on(tempoApiActions.temposLoadedSuccessfully, (state, action) => ({
    ...state,
    collection: action.tempos,
  })),
  on(tempoApiActions.tempoAddedSuccessfully, (state, action) => ({
    collection: createTempo(state.collection, action.addedTempo),
    selectedTempoID: null,
    loading: false,
    errors: {},
  })),
  on(tempoApiActions.tempoUpdatedSuccessfully, (state, action) => ({
    collection: updateTempo(state.collection, action.updatedTempo),
    selectedTempoID: null,
    loading: false,
    errors: {},
  })),
  on(tempoApiActions.tempoDeletedSuccessfully, (state, action) => ({
    collection: deleteTempo(state.collection, action.tempoID as ITempoID),
    selectedTempoID: null,
    loading: false,
    errors: {},
  })),
  on(
    tempoApiActions.tempoAddedFailure,
    tempoApiActions.tempoUpdatedFailure,
    tempoApiActions.tempoDeletedFailure,
    tempoApiActions.temposLoadedFailure,
    (state, action) => ({
      ...state,
      selectedTempoID: null,
      loading: false,
      errors: action.errors,
    })
  )
);

const createTempo = (tempos: ITempoQuiz[], newTempo: ITempoQuiz) => [
  ...tempos,
  newTempo,
];
const updateTempo = (tempos: ITempoQuiz[], updatedTempo: ITempoQuiz) =>
  tempos.map((tempo) =>
    tempo.id === updatedTempo.id
      ? Object.assign({}, tempo, updatedTempo)
      : tempo
  );
const deleteTempo = (tempos: ITempoQuiz[], tempoID: ITempoID) =>
  tempos.filter((tempo) => tempo.id != tempoID);
