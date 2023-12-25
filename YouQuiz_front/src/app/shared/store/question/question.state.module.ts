import { NgModule } from '@angular/core';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { QuestionReducer } from './question.reducer';
import { QuestionEffect } from './question.effect';

export const QUESTION_FEATURE_KEY = 'QUESTION';

@NgModule({
  imports: [
    StoreModule.forFeature(QUESTION_FEATURE_KEY, QuestionReducer),
    EffectsModule.forFeature([QuestionEffect]),
  ],
})
export class QuestionStateModule {}
