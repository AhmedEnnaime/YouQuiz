import { Component, Input, OnInit } from '@angular/core';
import { getDayFromDate, getMonthNameFromDate } from 'utils/dateTime';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css'],
})
export class CalendarComponent implements OnInit {
  @Input() date?: any;
  month?: string = 'Jan';
  dateInMonth?: number = 1;

  constructor() {}

  ngOnInit(): void {
    if (this.date) {
      this.month = getMonthNameFromDate(this.date);
      this.dateInMonth = getDayFromDate(this.date);
    }
  }
}
