import { Component, Input, OnInit } from '@angular/core';
import { getButtonClassName } from 'utils/styling';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css'],
})
export class ButtonComponent implements OnInit {
  @Input() props?: any;
  className: string = '';

  ngOnInit(): void {
    if (this.props?.type) {
      this.className = getButtonClassName(this.props?.type);
    }
  }
}
