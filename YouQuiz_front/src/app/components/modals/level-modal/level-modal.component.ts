import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Level } from 'src/app/shared/models/level.model';
import { Store } from '@ngrx/store';
import * as levelPageActions from '../../../shared/store/level/actions/level-page.actions';

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
        description: this.data.level.description ?? '',
        maxScore: this.data.level.maxScore || 0,
        minScore: this.data.level.minScore || 0,
      });
    }
  }

  addLevel() {
    const level: Level = {
      description: this.form.value.description ?? '',
      maxScore: this.form.value.maxScore ?? 0,
      minScore: this.form.value.minScore ?? 0,
    };
    if (this.data.level !== undefined) {
      const levelID: number = this.data.level.id ?? 0;
      this.store.dispatch(levelPageActions.updateLevel({ level, levelID }));
    } else {
      this.store.dispatch(levelPageActions.addLevel({ level }));
    }

    this.dialogRef.close();
  }
}
