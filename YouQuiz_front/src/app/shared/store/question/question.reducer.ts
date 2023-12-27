import { createReducer, on } from '@ngrx/store';
import { Question } from '../../models/question.model';
import * as questionPageActions from './actions/question-page.actions';
import * as questionApiActions from './actions/question.api.actions';

export interface QuestionState {
  collection: Question[];
  selectedQuestionID: number | null;
  loading: boolean;
  errors: {};
}

export const initialState: QuestionState = {
  collection: [],
  selectedQuestionID: null,
  loading: false,
  errors: {},
};

export const QuestionReducer = createReducer(
  initialState,
  on(questionApiActions.questionAddedSuccessfully, (state, action) => ({
    collection: addQuestion(state.collection, action.addedQuestion),
    selectedQuestionID: null,
    loading: false,
    errors: {},
  })),
  on(questionApiActions.questionUpdatedSuccessfully, (state, action) => ({
    collection: updateQuestion(state.collection, action.updatedQuestion),
    selectedQuestionID: null,
    loading: false,
    errors: {},
  })),
  on(
    questionApiActions.questionAddedFailure,
    questionApiActions.questionUpdatedFailure,
    (state, action) => ({
      ...state,
      loading: false,
      errors: action.errors,
    })
  )
);

const addQuestion = (questions: Question[], newQuestion: Question) => [
  ...questions,
  newQuestion,
];
const updateQuestion = (questions: Question[], updatedQuestion: Question) => {
  console.log(questions);
  return questions.map((question) => {
    console.log('question id ' + question.id);
    console.log('updated question id ' + updatedQuestion.id);
    return question.id == updatedQuestion.id
      ? Object.assign({}, question, updatedQuestion)
      : question;
  });
};
