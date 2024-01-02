import { Trainer } from './trainer.model';

export class Quiz {
  public id?: number;
  public score?: number;
  public showAnswers?: boolean;
  public showFinalResults?: boolean;
  public chanceNum?: number;
  public remark?: string;
  public durationInMinutes?: number;
  public trainer_id?: number;
  public trainer?: Trainer;

  constructor(
    id: number,
    score: number,
    showAnswers: boolean,
    showFinalResults: boolean,
    chanceNum: number,
    remark: string,
    durationInMinutes: number,
    trainer_id: number,
    trainer: Trainer
  ) {
    this.id = id;
    this.score = score;
    this.showAnswers = showAnswers;
    this.showFinalResults = showFinalResults;
    this.chanceNum = chanceNum;
    this.remark = remark;
    this.durationInMinutes = durationInMinutes;
    this.trainer_id = trainer_id;
    this.trainer = trainer;
  }
}
