import { QuestionType } from 'src/app/core/enums/QuestionType';
import { Level } from './level.model';
import { Subject } from './subject.model';
import { Media } from './media.model';

export class Question {
  public id?: number;
  public questionText: string;
  public questionType: QuestionType;
  public totalScore: number;
  public subject?: Subject;
  public level?: Level;
  public medias?: Media[];

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
