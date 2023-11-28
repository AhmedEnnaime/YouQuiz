export class Subject {
  private id?: number;
  private title: string;
  private parent_id: number;
  private parent?: Subject;
  private childs?: Subject[];

  constructor(
    id: number,
    title: string,
    parent_id: number,
    parent: Subject,
    childs: Subject[]
  ) {
    this.id = id;
    this.title = title;
    this.parent_id = parent_id;
    this.parent = parent;
    this.childs = childs;
  }
}
