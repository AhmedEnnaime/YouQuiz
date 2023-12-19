import { NgModule } from '@angular/core';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { SubjectReducer } from './subject.reducer';
import { SubjectEffect } from './subject.effect';

export const SUBJECT_FEATURE_KEY = 'SUBJECT';

@NgModule({
  imports: [
    StoreModule.forFeature(SUBJECT_FEATURE_KEY, SubjectReducer),
    EffectsModule.forFeature([SubjectEffect]),
  ],
})
export class SubjectStateModule {}
