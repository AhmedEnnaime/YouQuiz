import { NgModule, isDevMode } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ButtonComponent } from 'src/app/shared/button/button.component';
import { GreetingsComponent } from './components/greetings/greetings.component';
import { HeaderComponent } from './shared/header/header.component';
import { IconComponent } from './shared/icon/icon.component';
import { fas } from '@fortawesome/free-solid-svg-icons';
import {
  FaIconLibrary,
  FontAwesomeModule,
} from '@fortawesome/angular-fontawesome';
import { DashboardComponent } from './components/pages/dashboard/dashboard.component';
import { MainLayoutComponent } from './shared/main-layout/main-layout.component';
import { ProfileComponent } from './components/pages/profile/profile.component';
import { SummaryComponent } from './components/summary/summary.component';
import { SummaryCardComponent } from './components/summary/summary-card/summary-card.component';
import { AssignedQuizzesComponent } from './components/assigned-quizzes/assigned-quizzes.component';
import { AssignedQuizCardComponent } from './components/assigned-quizzes/assigned-quiz-card/assigned-quiz-card.component';
import { HttpClientModule } from '@angular/common/http';
import { UpcomingQuizzesComponent } from './components/upcoming-quizzes/upcoming-quizzes.component';
import { UpcomingQuizCardComponent } from './components/upcoming-quizzes/upcoming-quiz-card/upcoming-quiz-card.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { LabelComponent } from './shared/label/label.component';
import { NotFoundComponent } from './shared/not-found/not-found.component';
import { QuizzesComponent } from './components/pages/quizzes/quizzes.component';
import { QuizCardComponent } from './components/pages/quizzes/quiz-card/quiz-card.component';
import { ModalComponent } from './components/modals/modal/modal.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { LevelsComponent } from './components/levels/levels.component';
import { LevelCardComponent } from './components/levels/level-card/level-card.component';
import { MatIconModule } from '@angular/material/icon';
import { DeleteModalComponent } from './components/modals/delete-modal/delete-modal.component';
import { LevelModalComponent } from './components/modals/level-modal/level-modal.component';
import { StoreModule } from '@ngrx/store';
import { StoreDevtoolsModule } from '@ngrx/store-devtools';
import { EffectsModule } from '@ngrx/effects';
import { QuestionsComponent } from './components/pages/questions/questions.component';
import { QuestionCardComponent } from './components/pages/questions/question-card/question-card.component';
import { ResponsesComponent } from './components/responses/responses.component';
import { QuizSideComponent } from './components/pages/questions/quiz-side/quiz-side.component';
import { ResponseCardComponent } from './components/responses/response-card/response-card.component';
import { QuestionsListComponent } from './components/pages/questions/questions-list/questions-list.component';
import { SubjectsComponent } from './components/subjects/subjects.component';
import { SubjectCardComponent } from './components/subjects/subject-card/subject-card.component';
import { SallonComponent } from './components/pages/sallon/sallon.component';
import { LeftSideBarComponent } from './components/pages/sallon/left-side-bar/left-side-bar.component';
import { CenterComponent } from './components/pages/sallon/center/center.component';
import { ResponsesOptionsComponent } from './components/pages/sallon/center/responses-options/responses-options.component';
import { ResponseOptionCardComponent } from './components/pages/sallon/center/responses-options/response-option-card/response-option-card.component';
import { RightSideComponent } from './components/pages/sallon/right-side/right-side.component';
import { QuizQuestionCardComponent } from './components/pages/sallon/left-side-bar/quiz-question-card/quiz-question-card.component';
import { LevelEffect } from './shared/store/level/level.effect';
import { LevelStateModule } from './shared/store/level/level.state.module';
import { SubjectStateModule } from './shared/store/subject/subject.state.module';
import { SubjectEffect } from './shared/store/subject/subject.effect';
import { QuizEffect } from './shared/store/quiz/quiz.effect';
import { QuizStateModule } from './shared/store/quiz/quiz.state.module';
import { TempoEffect } from './shared/store/tempo/tempo.effect';
import { TempoStateModule } from './shared/store/tempo/tempo.state.module';
import { ValidationStateModule } from './shared/store/validations/validation.state.module';
import { ValidationEffect } from './shared/store/validations/validation.effect';

@NgModule({
  declarations: [
    AppComponent,
    ButtonComponent,
    GreetingsComponent,
    HeaderComponent,
    IconComponent,
    DashboardComponent,
    MainLayoutComponent,
    ProfileComponent,
    SummaryComponent,
    SummaryCardComponent,
    AssignedQuizzesComponent,
    AssignedQuizCardComponent,
    UpcomingQuizzesComponent,
    UpcomingQuizCardComponent,
    CalendarComponent,
    LabelComponent,
    NotFoundComponent,
    QuizzesComponent,
    QuizCardComponent,
    ModalComponent,
    LevelsComponent,
    LevelCardComponent,
    DeleteModalComponent,
    LevelModalComponent,
    QuestionsComponent,
    QuestionCardComponent,
    ResponsesComponent,
    ResponseCardComponent,
    QuizSideComponent,
    QuestionsListComponent,
    SubjectsComponent,
    SubjectCardComponent,
    SallonComponent,
    LeftSideBarComponent,
    CenterComponent,
    ResponsesOptionsComponent,
    ResponseOptionCardComponent,
    RightSideComponent,
    QuizQuestionCardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatDialogModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatRadioModule,
    MatIconModule,
    StoreModule,
    StoreModule.forRoot({}, {}),
    StoreDevtoolsModule.instrument({ maxAge: 25, logOnly: !isDevMode() }),
    EffectsModule.forRoot([
      LevelEffect,
      QuizEffect,
      SubjectEffect,
      TempoEffect,
      ValidationEffect,
    ]),
    LevelStateModule,
    SubjectStateModule,
    QuizStateModule,
    TempoStateModule,
    ValidationStateModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas);
  }
}
