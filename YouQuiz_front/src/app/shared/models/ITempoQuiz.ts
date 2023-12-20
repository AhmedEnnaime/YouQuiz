import { Question } from './question.model';

export interface ITempoQuiz {
  question?: Question;
  time: number;
  question_id?: number;
  quiz_id?: number;
}
