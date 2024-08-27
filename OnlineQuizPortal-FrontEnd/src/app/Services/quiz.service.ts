import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Quiz } from '../model/quiz';
import { QuizRequest } from '../model/QuizRequest';
import { QuizRequestStatus } from '../model/QuizRequestStatus';

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  public fetchedQuiz!: Quiz;
  url: string = "/assets/quizquestions.json";
  quizById: string = "/api/quiz/getquiz";
  quizByIdForRegister: string = "/api/quiz/register/";
  quizUrl = "/api/quiz/addquiz";
  myQuiz="/api/quiz/myquiz";
  requestQuiz="/api/quizrequest/";
  requestList="/api/quizrequest/myrequests/"
  constructor(private http: HttpClient) { }

  getQuiz() {
    return this.http.get<Quiz>(this.url);
  }

  getQuizRequestByStudentId(studentId:string){
    return this.http.get<QuizRequest[]>(this.requestQuiz+"status/"+studentId);
  }


  requestForQuiz(quizReq:QuizRequest){

    return this.http.post<QuizRequest>(this.requestQuiz+"request",quizReq);

  }

  getQuizById(quizId:string) {
    return this.http.get<Quiz>(this.quizById+"/"+quizId);
  }

  getQuizByFacultyId(facId:string){
    return this.http.get<Quiz[]>(this.myQuiz+"/"+facId);
  }

  getQuizForRegister(quizId:string){
    return this.http.get<QuizRequest[]>(this.quizByIdForRegister+"/"+quizId);
  }

  getMyQuizRequest(facultyId:string){
    return this.http.get<QuizRequestStatus[]>(this.requestList+"/"+facultyId);
  }
  

  createNewQuiz(newQuiz: Quiz) {
    console.log("recived quiz" + newQuiz.listOfQuestions.length);
console.log("Url-->"+ this.quizUrl);
    console.log("service called")
    return this.http.post<Quiz>(this.quizUrl, newQuiz);
  }

}
