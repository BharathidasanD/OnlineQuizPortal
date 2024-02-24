import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { QuizDetailComponent } from './quiz-detail/quiz-detail.component';
import { CreateNewQuizComponent } from './create-new-quiz/create-new-quiz.component';
import { MyQuizComponent } from './my-quiz/my-quiz.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { FacultyPageComponent } from './faculty-page/faculty-page.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { AddFacultyComponent } from './add-faculty/add-faculty.component';
import { UserManagerComponent } from './user-manager/user-manager.component';
import { UserRoleManagerComponent } from './user-role-manager/user-role-manager.component';

const routes: Routes = [
  { path: 'newquiz', component: CreateNewQuizComponent },
  { path: 'home', component: HomeComponent },
  {path:'quizdetail/:quizId' , component:QuizDetailComponent},
  {path:'myquiz/:facId',component:MyQuizComponent},
  {path:'faculty/login', component:LoginComponent},
  {path:'student/login', component:LoginComponent},
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: HomeComponent },
  { path: 'faculty', component: FacultyPageComponent },
  { path: 'admin', component: AdminPageComponent },
  { path: 'addfaculty', component: AddFacultyComponent },
  {path:'usermanager',component:UserManagerComponent},
  {path:'updateuserrole/:userId',component:UserRoleManagerComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  
 }
