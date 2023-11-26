export class Level {
  private id?: number;
  private description?: string;
  private maxScore: number;
  private minScore: number;

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
