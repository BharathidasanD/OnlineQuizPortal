import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { QuizDetailComponent } from './quiz-detail/quiz-detail.component';
import { CreateNewQuizComponent } from './create-new-quiz/create-new-quiz.component';
import { MyQuizComponent } from './my-quiz/my-quiz.component';
import { LoginComponent } from './login/login.component';

const routes: Routes = [
  { path: 'newquiz', component: CreateNewQuizComponent },
  { path: 'home', component: HomeComponent },
  {path:'quizdetail' , component:QuizDetailComponent},
  {path:'myquiz',component:MyQuizComponent},
  {path:'faculty/login', component:LoginComponent},
  {path:'student/login', component:LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  
 }
