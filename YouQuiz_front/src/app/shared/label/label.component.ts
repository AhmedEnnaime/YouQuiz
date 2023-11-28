import { Component, Input, OnInit } from '@angular/core';
import { LabelProps } from 'src/app/core/interfaces/ILabelProps';

@Component({
  selector: 'app-label',
  templateUrl: './label.component.html',
  styleUrls: ['./label.component.css'],
})
export class LabelComponent implements OnInit {
  @Input() props?: LabelProps;
  className: string = 'rounded-full px-2 py-1';

  constructor() {}

  ngOnInit(): void {
    if (this.props?.durationInMinutes) {
      this.className = getClassName(this.props?.durationInMinutes);
    }
  }
}

function getClassName(durationInMinutes: number): string {
  let className = 'rounded-full px-2 py-1';

  switch (true) {
    case durationInMinutes <= 30:
      className += ' bg-blue-100 text-blue-700';
      break;
    case durationInMinutes > 30 && durationInMinutes <= 60:
      className += ' bg-green-100 text-green-700';
      break;
    case durationInMinutes > 60 && durationInMinutes <= 120:
      className += ' bg-red-100 text-red-700';
      break;
    default:
      className += ' bg-gray-100 text-gray-700';
      break;
  }

  return className;
}
