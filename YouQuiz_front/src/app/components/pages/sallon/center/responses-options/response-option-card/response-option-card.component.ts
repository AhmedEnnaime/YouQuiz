import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges,
} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { IValidation } from 'src/app/shared/models/IValidation';

@Component({
  selector: 'app-response-option-card',
  templateUrl: './response-option-card.component.html',
  styleUrls: ['./response-option-card.component.css'],
})
export class ResponseOptionCardComponent implements OnInit {
  @Input() validation?: IValidation;
  validationForm!: FormGroup;
  @Output() validationUpdatedForm = new EventEmitter<FormGroup>();

  constructor(private fb: FormBuilder) {}

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
    this.validationForm.valueChanges.subscribe((newValues) =>
      console.log(newValues)
    );
    this.validationUpdatedForm.emit(this.validationForm);
  }

  ngOnInit(): void {
    this.updateValidationForm();
  }
}
