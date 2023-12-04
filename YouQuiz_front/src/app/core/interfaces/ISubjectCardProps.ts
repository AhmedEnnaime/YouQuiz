import { Subject } from '../../shared/models/subject.model';

export interface SubjectCardProps {
  title: string;
  childs?: Subject[];
  icon?: any;
}
