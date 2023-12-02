import { createReducer, on } from '@ngrx/store';
import { Quiz } from '../../models/quiz.model';
import { addQuiz, loadQuizzes } from '../actions/quiz.action';

export interface QuizState {
  quizzes: Quiz[];
}

export const initialState: QuizState = {
  quizzes: [],
};

export const QuizzesReducer = createReducer(
  initialState,
  on(loadQuizzes, (state, { quizzes }) => ({ ...state, quizzes })),
  on(addQuiz, (state, { quiz }) => ({
    ...state,
    quizzes: [...state.quizzes, quiz],
  }))
);
