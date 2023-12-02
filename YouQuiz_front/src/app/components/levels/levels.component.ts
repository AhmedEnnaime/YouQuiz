import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LevelService } from 'src/app/services/level.service';
import { Level } from 'src/app/shared/models/level.model';
import { LevelModalComponent } from '../modals/level-modal/level-modal.component';
import { Store } from '@ngrx/store';
import { loadLevels } from 'src/app/shared/store/actions/level.action';

@Component({
  selector: 'app-levels',
  templateUrl: './levels.component.html',
  styleUrls: ['./levels.component.css'],
})
export class LevelsComponent implements OnInit {
  levels: Level[] = [];

  constructor(
    private store: Store<{ levels: { levels: Level[] } }>,
    public dialog: MatDialog
  ) {
    store.select('levels').subscribe((levelsState: { levels: Level[] }) => {
      this.levels = levelsState.levels;
    });
  }

  openDialog() {
    this.dialog.open(LevelModalComponent, {
      enterAnimationDuration: '400ms',
      exitAnimationDuration: '400ms',
      autoFocus: false,
      data: { level: undefined },
    });
  }

  ngOnInit(): void {
    this.store.dispatch(loadLevels({ levels: this.levels }));
  }
}
