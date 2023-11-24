import { Component, OnInit } from '@angular/core';
import { getTodayGreeting } from 'utils/dateTime';

@Component({
  selector: 'app-greetings',
  templateUrl: './greetings.component.html',
  styleUrls: ['./greetings.component.css'],
})
export class GreetingsComponent implements OnInit {
  dropdownProps: any = {
    dropdownText: 'This week',
    type: 'default',
    dropdownItems: [
      { text: 'This week' },
      { text: 'This month' },
      { text: 'This year' },
    ],
  };
  greeting: String = 'Morning';

  ngOnInit(): void {
    this.greeting = getTodayGreeting();
  }
}
