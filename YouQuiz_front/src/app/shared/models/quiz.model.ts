export class Quiz {
  public id?: number;
  public score: number;
  public showAnswers: boolean;
  public showFinalResults: boolean;
  public chanceNum: number;
  public remark?: string;
  public durationInMinutes: number;

  constructor(
    id: number,
    score: number,
    showAnswers: boolean,
    showFinalResults: boolean,
    chanceNum: number,
    remark: string,
    durationInMinutes: number
  ) {
    this.id = id;
    this.score = score;
    this.showAnswers = showAnswers;
    this.showFinalResults = showFinalResults;
    this.chanceNum = chanceNum;
    this.remark = remark;
    this.durationInMinutes = durationInMinutes;
  }
}
