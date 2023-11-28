import { Component, Input, OnInit } from '@angular/core';
import { AssignQuiz } from 'src/app/shared/models/assign-quiz.model';
import { getQuizTime } from 'utils/dateTime';

@Component({
  selector: 'app-upcoming-quiz-card',
  templateUrl: './upcoming-quiz-card.component.html',
  styleUrls: ['./upcoming-quiz-card.component.css'],
})
export class UpcomingQuizCardComponent implements OnInit {
  @Input() props?: any;
  upcomingQuiz?: AssignQuiz & { time: string };

  ngOnInit(): void {
    const time = getQuizTime(this.props?.debutDate);
    this.upcomingQuiz = {
      ...this.props,
      time: time + 'h',
    };
  }
}
