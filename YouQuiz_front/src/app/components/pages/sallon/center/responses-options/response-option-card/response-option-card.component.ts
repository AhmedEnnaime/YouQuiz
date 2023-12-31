import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { IValidation } from 'src/app/shared/models/IValidation';
import * as validationPageActions from '../../../../../../shared/store/validations/actions/validation-page.actions';
import { Store } from '@ngrx/store';

@Component({
  selector: 'app-response-option-card',
  templateUrl: './response-option-card.component.html',
  styleUrls: ['./response-option-card.component.css'],
})
export class ResponseOptionCardComponent implements OnInit {
  @Input() validation?: IValidation;
  validationForm!: FormGroup;
  @Output() validationUpdatedForm = new EventEmitter<FormGroup>();
  validationObject?: IValidation;

  constructor(private store: Store, private fb: FormBuilder) {}

  setValidationForm(): void {
    this.validationForm = this.fb.group({
      validation_id: [this.validation?.id],
      response_id: [this.validation?.response?.id],
      response: [this.validation?.response?.response as string],
      points: [this.validation?.points as number],
    });
  }

  updateValidationForm() {
    this.setValidationForm();
    this.validationForm.valueChanges.subscribe(
      (newValues) =>
        (this.validationObject = {
          id: newValues.validation_id,
          response: {
            id: newValues.response_id,
            response: newValues.response,
          },
          points: newValues.points,
        })
    );
  }

  updateValidation(): void {
    console.log(this.validationObject);

    this.store.dispatch(
      validationPageActions.updateValidation({
        validation: this.validationObject as IValidation,
        validationID: this.validation?.id as number,
      })
    );
  }

  ngOnInit(): void {
    this.updateValidationForm();
  }
}
