export class Quiz {
  private id?: number;
  private score: number;
  private showAnswers: boolean;
  private showFinalResults: boolean;
  private chanceNum: number;
  private remark?: string;
  private durationInMinutes: number;

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
