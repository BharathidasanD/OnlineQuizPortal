import { QuizQuestions } from "./QuizQuestions";

export class Quiz {
   constructor(public quizId:string,public facultyId:string,public quizName:string,
    public listOfQuestions:QuizQuestions[]){

   }

}
