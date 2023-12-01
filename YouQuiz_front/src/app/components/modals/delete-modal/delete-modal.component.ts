import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import * as LevelActions from '../../../shared/store/actions/level.action';
import { LevelService } from 'src/app/services/level.service';
import { Store } from '@ngrx/store';

@Component({
  selector: 'app-delete-modal',
  templateUrl: './delete-modal.component.html',
  styleUrls: ['./delete-modal.component.css'],
})
export class DeleteModalComponent {
  constructor(
    private dialogRef: MatDialogRef<DeleteModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { levelId: number },
    private store: Store
  ) {}

  onClose() {
    this.dialogRef.close();
  }

  onDelete() {
    this.store.dispatch(LevelActions.removeLevel({ id: this.data.levelId }));
    this.dialogRef.close();
  }
}
