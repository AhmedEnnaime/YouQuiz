import { NgModule } from '@angular/core';
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
import { LevelsReducer } from './shared/store/reducers/level.reducer';
import { LevelEffects } from './shared/store/effects/level.effect';
import { QuizzesReducer } from './shared/store/reducers/quiz.reducer';
import { QuizEffects } from './shared/store/effects/quiz.effect';
import { QuestionsComponent } from './components/pages/questions/questions.component';
import { QuestionCardComponent } from './components/pages/questions/question-card/question-card.component';
import { ResponsesComponent } from './components/responses/responses.component';
import { QuizSideComponent } from './components/pages/questions/quiz-side/quiz-side.component';
import { ResponseCardComponent } from './components/responses/response-card/response-card.component';
import { QuestionsListComponent } from './components/pages/questions/questions-list/questions-list.component';
import { SubjectsComponent } from './components/subjects/subjects.component';
import { SubjectCardComponent } from './components/subjects/subject-card/subject-card.component';
import { SubjectsReducer } from './shared/store/reducers/subject.reducer';
import { SubjectEffects } from './shared/store/effects/subject.effect';
import { TemposReducer } from './shared/store/reducers/tempo.reducer';
import { TempoEffects } from './shared/store/effects/tempo.effect';

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
    StoreModule.forRoot({
      levels: LevelsReducer,
      quizzes: QuizzesReducer,
      subjects: SubjectsReducer,
      tempos: TemposReducer,
    }),
    StoreDevtoolsModule.instrument({
      maxAge: 25,
    }),
    EffectsModule.forRoot([
      LevelEffects,
      QuizEffects,
      SubjectEffects,
      TempoEffects,
    ]),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {
  constructor(library: FaIconLibrary) {
    library.addIconPacks(fas);
  }
}
