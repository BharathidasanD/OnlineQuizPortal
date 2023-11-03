import { Quiz } from "./quiz";

export class QuizQuestions{
    constructor(public questionId:string,public question:string,public maximumMark:number,public options:any[],
        public answer:string,public quizId:string){

    }
}