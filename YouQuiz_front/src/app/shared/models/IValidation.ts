import { Question } from './question.model';
import { Response } from './response.model';

export interface IValidation {
  question: Question;
  response: Response;
  points: number;
}
