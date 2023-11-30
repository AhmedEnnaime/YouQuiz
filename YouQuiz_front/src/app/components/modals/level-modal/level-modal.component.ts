import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { LevelService } from 'src/app/services/level.service';
import { Level } from 'src/app/shared/models/level.model';

@Component({
  selector: 'app-level-modal',
  templateUrl: './level-modal.component.html',
  styleUrls: ['./level-modal.component.css'],
})
export class LevelModalComponent {
  constructor(
    private levelService: LevelService,
    private dialogRef: MatDialogRef<LevelModalComponent>
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

    this.levelService.addLevel(newLevel).subscribe({
      next: (addedLevel) => {
        console.log('Level added:', addedLevel);
        this.dialogRef.close();
      },
      error: (error) => {
        console.error('Error adding level:', error);
      },
    });
  }
}
