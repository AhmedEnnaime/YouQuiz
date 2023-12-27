import { ITempoID } from './ITempoID';
import { Question } from './question.model';
import { Quiz } from './quiz.model';

export interface ITempoQuiz {
  question?: Question;
  quiz?: Quiz | null;
  time: number;
  id?: ITempoID;
  question_id?: number;
  quiz_id?: number;
}
