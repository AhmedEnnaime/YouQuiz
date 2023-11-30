import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LevelService } from 'src/app/services/level.service';
import { Level } from 'src/app/shared/models/level.model';
import { LevelModalComponent } from '../modals/level-modal/level-modal.component';

@Component({
  selector: 'app-levels',
  templateUrl: './levels.component.html',
  styleUrls: ['./levels.component.css'],
})
export class LevelsComponent implements OnInit {
  levels: Level[] = [];

  constructor(private levelService: LevelService, public dialog: MatDialog) {}

  openDialog() {
    this.dialog.open(LevelModalComponent, {
      enterAnimationDuration: '400ms',
      exitAnimationDuration: '400ms',
      autoFocus: false,
    });
  }

  ngOnInit(): void {
    this.levelService.getLevels().subscribe((levels) => {
      this.levels = levels;
    });
  }
}
