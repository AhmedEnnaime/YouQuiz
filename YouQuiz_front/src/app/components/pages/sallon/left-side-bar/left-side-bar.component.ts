import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Level } from 'src/app/shared/models/level.model';
import { loadLevels } from 'src/app/shared/store/actions/level.action';

@Component({
  selector: 'app-left-side-bar',
  templateUrl: './left-side-bar.component.html',
  styleUrls: ['./left-side-bar.component.css'],
})
export class LeftSideBarComponent implements OnInit {
  levels: Level[] = [];

  constructor(private store: Store<{ levels: { levels: Level[] } }>) {
    store.select('levels').subscribe((levelsState: { levels: Level[] }) => {
      this.levels = levelsState.levels;
    });
  }

  ngOnInit(): void {
    this.store.dispatch(loadLevels({ levels: this.levels }));
  }
}
