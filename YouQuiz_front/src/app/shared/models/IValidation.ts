import { Question } from './question.model';
import { Response } from './response.model';

export interface IValidation {
  id?: number;
  question?: Question;
  response?: Response;
  points: number;
  question_id?: number;
  response_id?: number;
}
