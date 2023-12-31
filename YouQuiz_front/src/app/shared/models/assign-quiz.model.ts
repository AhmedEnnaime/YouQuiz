import { Result } from 'src/app/core/enums/Result';
import { Student } from './student.model';
import { Quiz } from './quiz.model';

export class AssignQuiz {
  public id?: number;
  public score: number;
  public played: number;
  public result?: Result;
  public reason: string;
  public debutDate: Date;
  public endDate: Date;
  public student: Student;
  public quiz: Quiz;

  constructor(
    id: number,
    score: number,
    played: number,
    result: Result,
    reason: string,
    debutDate: Date,
    endDate: Date,
    student: Student,
    quiz: Quiz
  ) {
    this.id = id;
    this.score = score;
    this.played = played;
    this.result = result;
    this.reason = reason;
    this.debutDate = debutDate;
    this.endDate = endDate;
    this.student = student;
    this.quiz = quiz;
  }
}
