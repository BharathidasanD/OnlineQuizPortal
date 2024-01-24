import { Component } from '@angular/core';
import { Quiz } from '../model/quiz';
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';
import { QuizService } from '../Services/quiz.service';
import { ActivatedRoute, ParamMap } from '@angular/router';
@Component({
  selector: 'app-quiz-detail',
  templateUrl: './quiz-detail.component.html',
  styleUrls: ['./quiz-detail.component.css']
})
export class QuizDetailComponent {
  public myQuiz!: Quiz;
  public errMsg!: string;
  public editQuiz!: FormGroup;
  currentTab = 1;
  private quizId!: string;
  constructor(private quizService: QuizService, private formBuilder: FormBuilder,
    private activatedRouter:ActivatedRoute) {
    
  }
  ngOnInit() {

    this.editQuiz = this.formBuilder.group({
      quizName: [''],
      facultyId: [''],
      listOfQuestions: this.formBuilder.array([
      ])

    });
    this.activatedRouter.paramMap.subscribe((params: ParamMap) => {
      let quiz_id = params.get('quizId');
      console.log("parsed quizId id:"+ quiz_id);
      this.quizId=quiz_id as string;
    })
   this.quizService.getQuizById(this.quizId).subscribe(
     (response) => {
        console.log("data " + response.quizName);
     this.loadData(response);
   }
  );
    



  }

  loadData(responseData: Quiz) {
    console.log("called" + responseData.listOfQuestions.forEach(a=>console.log(a)));
    this.myQuiz = responseData;
    this.editQuiz.patchValue(this.myQuiz);
    let tempData!: FormGroup;
    for (let question of this.myQuiz.listOfQuestions) {
       tempData = this.formBuilder.group({
        question: [''],
        answer: [''],
        maximumMark: [''],
        options:this.formBuilder.group({
            option1: [''],
            option2: [''],
            option3: [''],
            option4: [''],
            option5: ['']
          })

      });
      this.listOfQuestions.push(tempData);
    }
    this.editQuiz.patchValue(this.myQuiz);

  }


  get listOfQuestions(): FormArray {
    return this.editQuiz.get('listOfQuestions') as FormArray;
  }





  addQuestion() {
    const question =this.formBuilder.group({
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
    let len=this.listOfQuestions.length;
    len++;
    this.currentTab=len;

  }

  
  removeQuestion(questionNo: number) {
    if (this.currentTab > 0) {
      this.listOfQuestions.removeAt(questionNo);
      this.currentTab--;
    }
  }

}
