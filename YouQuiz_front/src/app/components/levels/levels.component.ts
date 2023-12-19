import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Level } from 'src/app/shared/models/level.model';
import { LevelModalComponent } from '../modals/level-modal/level-modal.component';
import { Store } from '@ngrx/store';
import * as levelPageActions from '../../shared/store/level/actions/level-page.actions';
import { Observable } from 'rxjs';
import { selectLevels } from 'src/app/shared/store/level/level.selector';

@Component({
  selector: 'app-levels',
  templateUrl: './levels.component.html',
  styleUrls: ['./levels.component.css'],
})
export class LevelsComponent implements OnInit {
  levels: Observable<Level[]>;

  constructor(private store: Store, public dialog: MatDialog) {
    this.levels = store.select(selectLevels);
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
    this.store.dispatch(levelPageActions.enter());
  }
}
