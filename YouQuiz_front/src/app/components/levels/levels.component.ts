import { Component, OnInit } from '@angular/core';
import { LevelService } from 'src/app/services/level.service';
import { Level } from 'src/app/shared/models/level.model';

@Component({
  selector: 'app-levels',
  templateUrl: './levels.component.html',
  styleUrls: ['./levels.component.css'],
})
export class LevelsComponent implements OnInit {
  levels: Level[] = [];

  constructor(private levelService: LevelService) {}

  ngOnInit(): void {
    this.levelService.getLevels().subscribe((levels) => {
      this.levels = levels;
    });
  }
}
