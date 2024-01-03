import { QuestionType } from 'src/app/core/enums/QuestionType';
import { Level } from './level.model';
import { Subject } from './subject.model';
import { Media } from './media.model';

export class Question {
  public id?: number | null;
  public questionText?: string | null;
  public questionType?: QuestionType;
  public totalScore?: number;
  public subject?: Subject;
  public level?: Level;
  public medias?: Media[];
  public level_id?: number;
  public subject_id?: number;

  constructor(
    id: number,
    questionText: string,
    questionType: QuestionType,
    totalScore: number,
    subject?: Subject,
    level?: Level,
    medias?: Media[],
    level_id?: number,
    subject_id?: number
  ) {
    this.id = id;
    this.questionText = questionText;
    this.questionType = questionType;
    this.totalScore = totalScore;
    this.subject = subject;
    this.level = level;
    this.medias = medias;
    this.level_id = level_id;
    this.subject_id = subject_id;
  }
}
