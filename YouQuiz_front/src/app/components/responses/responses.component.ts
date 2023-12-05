import { Component, Input, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { IValidation } from 'src/app/shared/models/IValidation';
import { loadValidations } from 'src/app/shared/store/actions/validation.action';
import { selectValidations } from 'src/app/shared/store/selectors/validation.selector';

@Component({
  selector: 'app-responses',
  templateUrl: './responses.component.html',
  styleUrls: ['./responses.component.css'],
})
export class ResponsesComponent {
  @Input()
  validations?: IValidation[] = [];

  constructor(
    private store: Store<{ validations: { validations: IValidation[] } }>
  ) {}
}
