import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Level } from 'src/app/shared/models/level.model';
import { Subject } from 'src/app/shared/models/subject.model';
import { loadLevels } from 'src/app/shared/store/actions/level.action';
import { loadSubjects } from 'src/app/shared/store/actions/subject.action';

@Component({
  selector: 'app-right-side',
  templateUrl: './right-side.component.html',
  styleUrls: ['./right-side.component.css'],
})
export class RightSideComponent implements OnInit {
  levels: Level[] = [];
  subjects: Subject[] = [];

  constructor(
    private storeLevel: Store<{ levels: { levels: Level[] } }>,
    private storeSubject: Store<{ subjects: { subjects: Subject[] } }>
  ) {
    storeLevel
      .select('levels')
      .subscribe((levelsState: { levels: Level[] }) => {
        this.levels = levelsState.levels;
      });
    storeSubject
      .select('subjects')
      .subscribe((subjectsState: { subjects: Subject[] }) => {
        this.subjects = subjectsState.subjects;
      });
  }

  ngOnInit(): void {
    this.storeLevel.dispatch(loadLevels({ levels: this.levels }));
    this.storeSubject.dispatch(loadSubjects({ subjects: this.subjects }));
    console.log(this.levels);
  }
}
