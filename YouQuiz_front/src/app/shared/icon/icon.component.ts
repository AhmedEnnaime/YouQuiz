import { Component, Input } from '@angular/core';
import { IconProps } from 'src/app/core/IIconProps';

@Component({
  selector: 'app-icon',
  templateUrl: './icon.component.html',
  styleUrls: ['./icon.component.css'],
})
export class IconComponent {
  @Input() props?: IconProps;

  constructor() {}

  ngOnInit(): void {}
}
