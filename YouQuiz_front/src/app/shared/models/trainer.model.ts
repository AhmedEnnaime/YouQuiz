import { Quiz } from './quiz.model';
import { User } from './user.model';

export class Trainer extends User {
  public id?: number;
  public speciality: string;
  public quizzes: Quiz[];

  constructor(
    firstName: string,
    lastName: string,
    birthDate: Date,
    address: string,
    speciality: string,
    quizzes: Quiz[] = [],
    id?: number
  ) {
    super(firstName, lastName, birthDate, address);
    this.id = id;
    this.speciality = speciality;
    this.quizzes = quizzes;
  }

  getNumberOfQuizzes(): number {
    return this.quizzes.length;
  }
}
