import { Component, Input, OnInit } from '@angular/core';
import { SummaryCardProps } from 'src/app/core/interfaces/ISummaryCardProps';

@Component({
  selector: 'app-summary-card',
  templateUrl: './summary-card.component.html',
  styleUrls: ['./summary-card.component.css'],
})
export class SummaryCardComponent {
  @Input() props?: SummaryCardProps;
}
