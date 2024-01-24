import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Quiz } from '../model/quiz';

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  public fetchedQuiz!: Quiz;
  url: string = "/assets/quizquestions.json";
  quizById: string = "http://localhost:8080/getquiz";
  quizUrl = "http://localhost:8080/addquiz";
  myQuiz="http://localhost:8080/myquiz";
  constructor(private http: HttpClient) { }

  getQuiz() {
    return this.http.get<Quiz>(this.url);
  }

  getQuizById(quizId:string) {
    return this.http.get<Quiz>(this.quizById+"/"+quizId);
  }

  getQuizByFacultyId(facId:string){
    return this.http.get<Quiz[]>(this.myQuiz+"/"+facId);
  }

  createNewQuiz(newQuiz: Quiz) {
    console.log("recived quiz" + newQuiz.listOfQuestions.length);
console.log("Url-->"+ this.quizUrl);
    console.log("service called")
    return this.http.post<Quiz>(this.quizUrl, newQuiz);
  }

}
