import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Level } from 'src/app/shared/models/level.model';
import * as LevelActions from '../../../shared/store/actions/level.action';
import { Store } from '@ngrx/store';

@Component({
  selector: 'app-level-modal',
  templateUrl: './level-modal.component.html',
  styleUrls: ['./level-modal.component.css'],
})
export class LevelModalComponent {
  constructor(
    private dialogRef: MatDialogRef<LevelModalComponent>,
    private store: Store
  ) {}

  form = new FormGroup({
    description: new FormControl<string>(''),
    maxScore: new FormControl<number>(0),
    minScore: new FormControl<number>(0),
  });

  addLevel() {
    const newLevel: Level = {
      description: this.form.value.description ?? '',
      maxScore: this.form.value.maxScore ?? 0,
      minScore: this.form.value.minScore ?? 0,
    };

    this.store.dispatch(LevelActions.addLevel({ level: newLevel }));
    this.dialogRef.close();
  }
}
