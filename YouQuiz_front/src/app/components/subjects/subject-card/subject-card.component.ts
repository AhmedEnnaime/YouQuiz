import { Component, Input, OnInit } from '@angular/core';
import { Subject } from 'src/app/shared/models/subject.model';

@Component({
  selector: 'app-subject-card',
  templateUrl: './subject-card.component.html',
  styleUrls: ['./subject-card.component.css'],
})
export class SubjectCardComponent implements OnInit {
  @Input() props?: any;
  subject?: Subject;

  ngOnInit(): void {
    this.subject = {
      ...this.props,
    };
  }
}
