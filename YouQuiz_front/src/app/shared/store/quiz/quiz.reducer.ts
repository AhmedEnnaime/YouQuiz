import { createReducer, on } from '@ngrx/store';
import { Quiz } from '../../models/quiz.model';
import * as quizPageActions from './actions/quiz-page.actions';
import * as quizApiActions from './actions/quiz.api.actions';

export interface QuizState {
  collection: Quiz[];
  selectedQuizID: number | null;
  loading: boolean;
  errors: {};
}

export const initialState: QuizState = {
  collection: [],
  selectedQuizID: null,
  loading: false,
  errors: {},
};

export const QuizReducer = createReducer(
  initialState,
  on(quizPageActions.enter, (state, action) => ({
    ...state,
    selectedQuizID: null,
    loading: true,
  })),
  on(quizPageActions.selectQuiz, (state, action) => ({
    ...state,
    selectedQuizID: action.quizID,
    loading: true,
  })),
  on(quizPageActions.unselectQuiz, (state, action) => ({
    ...state,
    selectedQuizID: null,
  })),
  on(quizApiActions.quizzesLoadedSuccessfully, (state, action) => ({
    ...state,
    collection: action.quizzes,
  })),
  on(quizApiActions.quizAddedSuccessfully, (state, action) => ({
    collection: createQuiz(state.collection, action.addedQuiz),
    selectedQuizID: null,
    loading: false,
    errors: {},
  })),
  on(quizApiActions.quizUpdatedSuccessfully, (state, action) => ({
    collection: updateQuiz(state.collection, action.updatedQuiz),
    selectedQuizID: null,
    loading: false,
    errors: {},
  })),
  on(quizApiActions.quizDeletedSuccessfully, (state, action) => ({
    collection: deleteQuiz(state.collection, action.quizID),
    selectedQuizID: null,
    loading: false,
    errors: {},
  })),
  on(
    quizApiActions.quizzesLoadedFailure,
    quizApiActions.quizAddedFailure,
    quizApiActions.quizUpdatedFailure,
    quizApiActions.quizDeletedFailure,
    quizApiActions.quizFoundedFailure,
    (state, action) => ({
      ...state,
      loading: false,
      errors: action.errors,
    })
  ),
  on(quizApiActions.quizFoundedSuccessfully, (state, action) => ({
    ...state,
    collection: [action.quiz],
    loading: false,
  }))
);

const createQuiz = (quizzes: Quiz[], newQuiz: Quiz) => [...quizzes, newQuiz];
const updateQuiz = (quizzes: Quiz[], updatedQuiz: Quiz) =>
  quizzes.map((quiz) =>
    quiz.id === updatedQuiz.id ? Object.assign({}, quiz, updatedQuiz) : quiz
  );
const deleteQuiz = (quizzes: Quiz[], quizID: number) =>
  quizzes.filter((quiz) => quiz.id != quizID);
