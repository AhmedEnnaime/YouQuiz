import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LevelService } from 'src/app/services/level.service';

@Component({
  selector: 'app-delete-modal',
  templateUrl: './delete-modal.component.html',
  styleUrls: ['./delete-modal.component.css'],
})
export class DeleteModalComponent {
  constructor(
    private levelService: LevelService,
    private dialogRef: MatDialogRef<DeleteModalComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { levelId: number },
    private router: Router
  ) {}

  onClose() {
    this.dialogRef.close();
  }

  onDelete() {
    this.levelService
      .deleteLevel(this.data.levelId)
      .subscribe((res: string) => {
        console.log(res);
        this.router.navigate(['/']);
      });
  }
}
