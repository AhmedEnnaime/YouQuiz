import { ITempoID } from './ITempoID';
import { Question } from './question.model';

export interface ITempoQuiz {
  question?: Question;
  time: number;
  id?: ITempoID;
  question_id?: number;
  quiz_id?: number;
}
