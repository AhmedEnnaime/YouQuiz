import { Component, Input, OnInit } from '@angular/core';
import { Level } from 'src/app/shared/models/level.model';

@Component({
  selector: 'app-level-card',
  templateUrl: './level-card.component.html',
  styleUrls: ['./level-card.component.css'],
})
export class LevelCardComponent implements OnInit {
  @Input() props?: any;
  level?: Level;

  ngOnInit(): void {
    this.level = {
      ...this.props,
    };
  }
}
