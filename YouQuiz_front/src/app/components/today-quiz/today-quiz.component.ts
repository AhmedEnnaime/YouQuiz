import { Component } from '@angular/core';
import {
  faExclamationCircle,
  faChartSimple,
} from '@fortawesome/free-solid-svg-icons';
import { TodayQuiz } from 'src/app/core/TodayQuiz';

const icons = {
  faExclamationCircle,
  faChartSimple,
};

@Component({
  selector: 'app-today-quiz',
  templateUrl: './today-quiz.component.html',
  styleUrls: ['./today-quiz.component.css'],
})
export class TodayQuizComponent {
  icons: any = icons;

  todayQuizzes: TodayQuiz[] = [
    {
      current: 4,
      total: 6,
      instruction: 'JAVA',
      type: 'danger',
      icon: icons.faExclamationCircle,
    },
    {
      current: 2,
      total: 6,
      instruction: 'Javascript',
      type: 'success',
      icon: icons.faChartSimple,
    },
  ];
}
