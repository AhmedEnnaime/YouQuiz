import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Level } from 'src/app/shared/models/level.model';
import * as LevelActions from '../../../shared/store/actions/level.action';
import { Store } from '@ngrx/store';

@Component({
  selector: 'app-level-modal',
  templateUrl: './level-modal.component.html',
  styleUrls: ['./level-modal.component.css'],
})
export class LevelModalComponent implements OnInit {
  constructor(
    private dialogRef: MatDialogRef<LevelModalComponent>,
    private store: Store,
    @Inject(MAT_DIALOG_DATA) public data: { level: Level }
  ) {}

  form = new FormGroup({
    description: new FormControl<string>(''),
    maxScore: new FormControl<number>(0),
    minScore: new FormControl<number>(0),
  });

  ngOnInit(): void {
    if (this.data.level !== undefined) {
      this.form.patchValue({
        description: this.data.level.description || '',
        maxScore: this.data.level.maxScore || 0,
        minScore: this.data.level.minScore || 0,
      });
    }
  }

  addLevel() {
    const newLevel: Level = {
      description: this.form.value.description ?? '',
      maxScore: this.form.value.maxScore ?? 0,
      minScore: this.form.value.minScore ?? 0,
    };
    if (this.data.level !== undefined) {
      const id: number = this.data.level.id ?? 0;
      this.store.dispatch(LevelActions.updateLevel({ level: newLevel, id }));
    } else {
      this.store.dispatch(LevelActions.addLevel({ level: newLevel }));
    }

    this.dialogRef.close();
  }
}
