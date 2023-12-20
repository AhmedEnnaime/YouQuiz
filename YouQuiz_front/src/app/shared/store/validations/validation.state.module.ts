import { NgModule } from '@angular/core';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { ValidationReducer } from './validation.reducer';
import { ValidationEffect } from './validation.effect';

export const VALIDATION_FEATURE_KEY = 'VALIDATION';

@NgModule({
  imports: [
    StoreModule.forFeature(VALIDATION_FEATURE_KEY, ValidationReducer),
    EffectsModule.forFeature([ValidationEffect]),
  ],
})
export class ValidationStateModule {}
