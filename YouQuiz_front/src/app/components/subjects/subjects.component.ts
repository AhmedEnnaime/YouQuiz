import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Subject } from 'src/app/shared/models/subject.model';
import { loadSubjects } from 'src/app/shared/store/actions/subject.action';

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.css'],
})
export class SubjectsComponent implements OnInit {
  subjects: Subject[] = [];

  constructor(private store: Store<{ subjects: { subjects: Subject[] } }>) {
    store
      .select('subjects')
      .subscribe((subjectsState: { subjects: Subject[] }) => {
        this.subjects = subjectsState.subjects;
      });
  }

  ngOnInit(): void {
    this.store.dispatch(loadSubjects({ subjects: this.subjects }));
  }
}
