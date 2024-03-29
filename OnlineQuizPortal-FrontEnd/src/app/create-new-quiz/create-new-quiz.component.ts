import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
import { Quiz } from '../model/quiz';
import { QuizService } from '../Services/quiz.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-create-new-quiz',
  templateUrl: './create-new-quiz.component.html',
  styleUrls: ['./create-new-quiz.component.css']
})
export class CreateNewQuizComponent {
  public myQuiz!: Quiz;
  public errMsg!: string;
  public newQuiz!: FormGroup;
  responseStatus:Object= [];
  currentTab = 1;
  constructor(private quizService: QuizService, private formBuilder: FormBuilder,
    private route:Router) {

  }
  ngOnInit() {

    this.newQuiz = this.formBuilder.group({
      quizName: [''],
      facultyId: [''],
      listOfQuestions: this.formBuilder.array([

      ])

    });
this.addQuestion();


  }
  get listOfQuestions(): FormArray {
    return this.newQuiz.get('listOfQuestions') as FormArray;
  }

  addQuestion() {
    console.log("add question called");
    const question = this.formBuilder.group({
      question: [''],
      answer: [''],
      maximumMark: [''],
      options:
        this.formBuilder.group({
          option1: [''],
          option2: [''],
          option3: [''],
          option4: [''],
          option5: ['']
        })

    });
    this.listOfQuestions.push(question);
    let len = this.listOfQuestions.length;
    len++;
    this.currentTab = len;

  }
  removeQuestion(questionNo: number) {
    if (this.currentTab > 0) {
      this.listOfQuestions.removeAt(questionNo);
      this.currentTab--;
    }
  }

  submit() {
    console.log("Submitted DATA")
    console.log(this.newQuiz.value.listOfQuestions.forEach(
      (a: any) => console.log(a)
    ));
    this.myQuiz=this.newQuiz.value;
    console.log("transformed-->"+this.myQuiz.quizName);
    console.log("facid-->"+this.myQuiz.facultyId);
    this.quizService.createNewQuiz(this.myQuiz).subscribe(
      data=>{
       
        if(data.facultyId !==null && data.facultyId.length>3)
        {
            this.route.navigate(['/myquiz',data.facultyId]);
        }
      },
      error=>console.error(error)
    );
  }
}
