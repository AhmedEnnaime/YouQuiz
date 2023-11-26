export class Subject {
  private id?: number;
  private title: string;
  private parent: Subject;
  private childs: Subject[];

  constructor(id: number, title: string, parent: Subject, childs: Subject[]) {
    this.id = id;
    this.title = title;
    this.parent = parent;
    this.childs = childs;
  }
}
