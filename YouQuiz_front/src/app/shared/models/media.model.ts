import { MediaType } from 'src/app/core/enums/MediaType';
import { Question } from './question.model';

export class Media {
  private id?: number;
  private link: string;
  private type: MediaType;
  private question: Question;

  constructor(id: number, link: string, type: MediaType, question: Question) {
    this.id = id;
    this.link = link;
    this.type = type;
    this.question = question;
  }
}
