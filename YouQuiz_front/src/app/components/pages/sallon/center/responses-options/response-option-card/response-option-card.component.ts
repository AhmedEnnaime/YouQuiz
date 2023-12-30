import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { IValidation } from 'src/app/shared/models/IValidation';

@Component({
  selector: 'app-response-option-card',
  templateUrl: './response-option-card.component.html',
  styleUrls: ['./response-option-card.component.css'],
})
export class ResponseOptionCardComponent implements OnInit {
  @Input() validation?: IValidation;
  validationForm: FormGroup;

  constructor(fb: FormBuilder) {
    this.validationForm = fb.group({
      question_id: [this.validation?.question?.id, Validators.required],
      response_id: [0, Validators.required],
      response: [this.validation?.response?.response, Validators.required],
      points: [0, Validators.required],
    });
  }

  ngOnInit(): void {
    console.log(this.validation);
  }
}
