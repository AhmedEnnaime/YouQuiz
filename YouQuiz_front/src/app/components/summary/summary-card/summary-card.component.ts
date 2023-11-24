import { Component, Input, OnInit } from '@angular/core';
import { SummaryCardProps } from 'src/app/core/ISummaryCardProps';

@Component({
  selector: 'app-summary-card',
  templateUrl: './summary-card.component.html',
  styleUrls: ['./summary-card.component.css'],
})
export class SummaryCardComponent implements OnInit {
  @Input() props?: SummaryCardProps;

  ngOnInit(): void {}
}
