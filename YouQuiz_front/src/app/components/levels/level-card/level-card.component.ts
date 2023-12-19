import { Component, Input, OnInit } from '@angular/core';
import { Level } from 'src/app/shared/models/level.model';
import { DeleteModalComponent } from '../../modals/delete-modal/delete-modal.component';
import { MatDialog } from '@angular/material/dialog';
import { LevelModalComponent } from '../../modals/level-modal/level-modal.component';

@Component({
  selector: 'app-level-card',
  templateUrl: './level-card.component.html',
  styleUrls: ['./level-card.component.css'],
})
export class LevelCardComponent implements OnInit {
  @Input() level?: Level;

  constructor(public dialog: MatDialog) {}
  openDialog() {
    this.dialog.open(DeleteModalComponent, {
      enterAnimationDuration: '400ms',
      exitAnimationDuration: '400ms',
      autoFocus: false,
      data: { levelId: this.level?.id },
    });
  }

  openUpdateDialog() {
    this.dialog.open(LevelModalComponent, {
      enterAnimationDuration: '400ms',
      exitAnimationDuration: '400ms',
      autoFocus: false,
      data: { level: this.level },
    });
  }

  ngOnInit(): void {}
}
