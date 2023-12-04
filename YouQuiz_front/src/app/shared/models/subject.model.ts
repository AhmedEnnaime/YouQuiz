export class Subject {
  public id?: number;
  public title: string;
  public parent_id?: number;
  public parent?: Subject;
  public childs?: Subject[];

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
