import { Component, Input, OnInit } from '@angular/core';
import { TodayQuizCardProps } from 'src/app/core/ITodayQuiz';
import {
  getProgressBackground,
  getProgressForeground,
  getProgressText,
} from 'utils/styling';

@Component({
  selector: 'app-today-quiz-card',
  templateUrl: './today-quiz-card.component.html',
  styleUrls: ['./today-quiz-card.component.css'],
})
export class TodayQuizCardComponent implements OnInit {
  @Input() props?: TodayQuizCardProps;
  progressStyle: string = '';
  progressForeground: string = '';
  progressBackground: string = '';
  iconColor: string = '';

  constructor() {}

  ngOnInit(): void {
    if (this.props?.total && this.props?.current) {
      const progress: number = (this.props?.current / this.props?.total) * 100;
      this.progressStyle = `width: ${progress}%`;
    }
    if (this.props?.type) {
      this.progressForeground = getProgressForeground(this.props?.type);
      this.progressBackground = getProgressBackground(this.props?.type);
      this.iconColor = getProgressText(this.props?.type);
    }
  }
}
