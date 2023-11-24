export class TodayQuiz {
  instruction: string;
  current: number;
  total: number;
  icon: any;
  type: string;

  constructor(
    instruction: string,
    current: number,
    total: number,
    icon: any,
    type: string
  ) {
    this.instruction = instruction;
    this.current = current;
    this.total = total;
    this.icon = icon;
    this.type = type;
  }
}
