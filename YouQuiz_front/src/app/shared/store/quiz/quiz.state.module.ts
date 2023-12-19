import { NgModule } from '@angular/core';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';
import { QuizReducer } from './quiz.reducer';
import { QuizEffect } from './quiz.effect';

export const QUIZ_FEATURE_KEY = 'QUIZ';

@NgModule({
  imports: [
    StoreModule.forFeature(QUIZ_FEATURE_KEY, QuizReducer),
    EffectsModule.forFeature([QuizEffect]),
  ],
})
export class QuizStateModule {}
