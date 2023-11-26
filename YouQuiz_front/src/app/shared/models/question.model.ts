import { QuestionType } from 'src/app/core/enums/QuestionType';
import { Level } from './level.model';
import { Subject } from './subject.model';
import { Media } from './media.model';

export class Question {
  private id?: number;
  private questionText: string;
  private questionType: QuestionType;
  private totalScore: number;
  private subject: Subject;
  private level: Level;
  private medias: Media[];

  constructor(
    id: number,
    questionText: string,
    questionType: QuestionType,
    totalScore: number,
    subject: Subject,
    level: Level,
    medias: Media[]
  ) {
    this.id = id;
    this.questionText = questionText;
    this.questionType = questionType;
    this.totalScore = totalScore;
    this.subject = subject;
    this.level = level;
    this.medias = medias;
  }
}
