export class Level {
  public id?: number;
  public description?: string;
  public maxScore: number;
  public minScore: number;

  constructor(
    id: number,
    description: string,
    maxScore: number,
    minScore: number
  ) {
    this.id = id;
    this.description = description;
    this.maxScore = maxScore;
    this.minScore = minScore;
  }
}
