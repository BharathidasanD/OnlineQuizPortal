import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgFor } from '@angular/common';
import { MatGridListModule } from '@angular/material/grid-list';
import { HeaderComponent } from './header/header.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { MatTabsModule } from '@angular/material/tabs';
import { MatListModule } from '@angular/material/list';
import { QuizDetailComponent } from './quiz-detail/quiz-detail.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateNewQuizComponent } from './create-new-quiz/create-new-quiz.component';
import { QuizService } from './Services/quiz.service';
import {MatSidenavModule} from '@angular/material/sidenav';
import { MyQuizComponent } from './my-quiz/my-quiz.component';
import { MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import { LoginComponent } from './login/login.component';
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,

    HomeComponent,

    QuizDetailComponent,

    CreateNewQuizComponent,
     MyQuizComponent,
     LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatGridListModule,
    NgFor, MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    FormsModule,
    ReactiveFormsModule,
    MatTabsModule,
    MatListModule,
    HttpClientModule,
    MatSidenavModule,
    MatTableModule, 
    MatPaginatorModule
    
  ],
  providers: [QuizService],
  bootstrap: [AppComponent]
})
export class AppModule { }
